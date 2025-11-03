package wanderly.wanderly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.FlightBooking;
import wanderly.wanderly.service.FlightBookingService;

import java.util.List;

@RestController
@RequestMapping("/api/flight-bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightBookingController {

    @Autowired
    private FlightBookingService bookingService;

    @PostMapping
    public FlightBooking createBooking(@RequestBody FlightBooking booking) {
        System.out.println("🎫 Raw incoming booking JSON:");
        System.out.println("➡ Full Name: " + booking.getFullName());
        System.out.println("➡ Email: " + booking.getEmail());
        System.out.println("➡ Passengers: " + booking.getPassengers());
        System.out.println("➡ Flight: " + booking.getFlightName());
        System.out.println("➡ Route: " + booking.getRoute());
        System.out.println("➡ Travel Date: " + booking.getTravelDate());
        System.out.println("➡ Amount (raw): " + booking.getAmount());

        try {
            // ✅ Handle cases where amount might come as string like "₹4,500"
            String amountStr = String.valueOf(booking.getAmount());
            if (amountStr.contains("₹") || amountStr.contains(",") || amountStr.contains(" ")) {
                amountStr = amountStr.replaceAll("[^0-9.]", "");
                double parsedAmount = Double.parseDouble(amountStr);
                booking.setAmount(parsedAmount);
                System.out.println("💰 Cleaned Amount Parsed: " + parsedAmount);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error parsing amount: " + e.getMessage());
            throw new RuntimeException("Invalid amount format received from frontend");
        }

        FlightBooking savedBooking = bookingService.saveBooking(booking);
        System.out.println("✅ Booking saved successfully with ID: " + savedBooking.getId());
        return savedBooking;
    }

    @GetMapping
    public List<FlightBooking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public FlightBooking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        System.out.println("🗑️ Deleted booking with ID: " + id);
    }
}
