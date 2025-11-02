package wanderly.wanderly.controller;

import wanderly.wanderly.service.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    // -------------------- SEND OTP -------------------- //
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> body) {
        String phone = body.get("phone");

        if (phone == null || !phone.matches("\\d{10}")) {
            return ResponseEntity.badRequest().body(Map.of("error", "❌ Invalid phone number (must be 10 digits)"));
        }

        try {
            Map<String, Object> resp = otpService.sendOtp(phone);
            return ResponseEntity.ok(resp);
        } 
        catch (IllegalStateException throttle) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Map.of("error", throttle.getMessage()));
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "🚨 Failed to send OTP. Check logs."));
        }
    }

    // -------------------- VERIFY OTP -------------------- //
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {
        String phone = body.get("phone");
        String otp = body.get("otp");

        if (phone == null || otp == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "⚠️ Both 'phone' and 'otp' are required."));
        }

        boolean verified = otpService.verify(phone, otp);

        if (verified) {
            return ResponseEntity.ok(Map.of("message", "✅ OTP verified successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "❌ Invalid or expired OTP. Please try again."));
        }
    }
}


