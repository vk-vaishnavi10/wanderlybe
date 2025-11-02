package wanderly.wanderly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderly.wanderly.entity.PackagePayment;
import wanderly.wanderly.service.PackagePaymentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/package-payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PackagePaymentController {

    private final PackagePaymentService service;

    public PackagePaymentController(PackagePaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PackagePayment> createPayment(@RequestBody PackagePayment payment) {
        payment.setStatus("SUCCESS");
        payment.setPaidAt(LocalDateTime.now());
        return ResponseEntity.ok(service.savePayment(payment));
    }

    @GetMapping
    public ResponseEntity<List<PackagePayment>> getAllPayments() {
        return ResponseEntity.ok(service.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackagePayment> getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
