package wanderly.wanderly.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderly.wanderly.entity.Flight;
import wanderly.wanderly.repository.FlightRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Get flight by id
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Create flight (auto calculate duration)
    public Flight createFlight(Flight flight) {
        calculateDuration(flight);
        return flightRepository.save(flight);
    }

    // Update flight
    public Flight updateFlight(Long id, Flight flightDetails) {
        return flightRepository.findById(id).map(flight -> {
            flight.setAirline(flightDetails.getAirline());
            flight.setFromCity(flightDetails.getFromCity());
            flight.setToCity(flightDetails.getToCity());
            flight.setDepartureTime(flightDetails.getDepartureTime());
            flight.setArrivalTime(flightDetails.getArrivalTime());
            flight.setPrice(flightDetails.getPrice());
            flight.setImage(flightDetails.getImage());
            calculateDuration(flight); // auto-update duration
            return flightRepository.save(flight);
        }).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    // Delete flight
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    // Helper: auto calculate duration
    private void calculateDuration(Flight flight) {
        if (flight.getDepartureTime() != null && flight.getArrivalTime() != null) {
            Duration duration = Duration.between(flight.getDepartureTime(), flight.getArrivalTime());
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            flight.setDuration(hours + "h " + minutes + "m");
        }
    }
}
