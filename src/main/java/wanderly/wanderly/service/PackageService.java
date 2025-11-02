package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.TripPackage;
import wanderly.wanderly.repository.PackageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<TripPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<TripPackage> getPackageById(Long id) {
        return packageRepository.findById(id);
    }

    public TripPackage savePackage(TripPackage tripPackage) {
        return packageRepository.save(tripPackage);
    }

    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }
}
