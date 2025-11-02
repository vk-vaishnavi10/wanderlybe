package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;

@Service
public class OtpService {

    private final Random random = new Random();
    private final Map<String, OtpRecord> store = new ConcurrentHashMap<>();

    // -------------------- SEND OTP (MOCK MODE) -------------------- //
    public Map<String, Object> sendOtp(String phone) {
        String otp = String.format("%06d", random.nextInt(1_000_000));
        long now = Instant.now().getEpochSecond();

        System.out.println("📱 MOCK OTP System Active (Fast2SMS disabled)");
        System.out.println("✅ OTP for phone " + phone + " is: " + otp);

        store.put(phone, new OtpRecord(otp, now));

        // Return mock OTP in response for frontend display
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "📱 OTP sent successfully (mock mode)");
        resp.put("otp", otp);
        return resp;
    }

    // -------------------- VERIFY OTP -------------------- //
    public boolean verify(String phone, String otp) {
        OtpRecord record = store.get(phone);
        if (record == null) return false;

        long now = Instant.now().getEpochSecond();
        if (now - record.getSentAt() > 180) { // 3 min expiry
            store.remove(phone);
            System.out.println("⚠️ OTP expired for " + phone);
            return false;
        }

        boolean isValid = record.getOtp().equals(otp);
        if (isValid) {
            store.remove(phone);
            System.out.println("✅ OTP verified successfully for " + phone);
        } else {
            System.out.println("❌ Invalid OTP entered for " + phone);
        }
        return isValid;
    }

    // -------------------- RECORD CLASS -------------------- //
    private static class OtpRecord {
        private final String otp;
        private final long sentAt;

        OtpRecord(String otp, long sentAt) {
            this.otp = otp;
            this.sentAt = sentAt;
        }

        public String getOtp() { return otp; }
        public long getSentAt() { return sentAt; }
    }
}

