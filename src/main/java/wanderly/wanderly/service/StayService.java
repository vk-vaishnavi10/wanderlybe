package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import wanderly.wanderly.entity.Stay;
import wanderly.wanderly.repository.StayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StayService {
    private final StayRepository stayRepository;

    public StayService(StayRepository stayRepository) {
        this.stayRepository = stayRepository;
    }

    public List<Stay> getAllStays() {
        return stayRepository.findAll();
    }

    public Optional<Stay> getStayById(Long id) {
        return stayRepository.findById(id);
    }

    public Stay saveStay(Stay stay) {
        return stayRepository.save(stay);
    }

    public void deleteStay(Long id) {
        stayRepository.deleteById(id);
    }
}
