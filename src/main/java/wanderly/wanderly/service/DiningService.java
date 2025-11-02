package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.Dining;
import wanderly.wanderly.repository.DiningRepository;
import java.util.List;

@Service
public class DiningService {

    private final DiningRepository repo;

    public DiningService(DiningRepository repo) {
        this.repo = repo;
    }

    public Dining saveDining(Dining dining) {
        return repo.save(dining);
    }

    public List<Dining> getAllDining() {
        return repo.findAll();
    }

    public Dining getDiningById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
