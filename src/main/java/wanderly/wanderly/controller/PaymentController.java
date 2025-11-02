package wanderly.wanderly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.Payment;
import wanderly.wanderly.repository.PaymentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepo;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        payment.setStatus("SUCCESS"); // ensure success set
        return paymentRepo.save(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}

