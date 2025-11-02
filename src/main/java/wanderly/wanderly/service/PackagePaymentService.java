package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.PackagePayment;
import wanderly.wanderly.repository.PackagePaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PackagePaymentService {
    private final PackagePaymentRepository repository;

    public PackagePaymentService(PackagePaymentRepository repository) {
        this.repository = repository;
    }

    public List<PackagePayment> getAllPayments() {
        return repository.findAll();
    }

    public Optional<PackagePayment> getPaymentById(Long id) {
        return repository.findById(id);
    }

    public PackagePayment savePayment(PackagePayment payment) {
        return repository.save(payment);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
