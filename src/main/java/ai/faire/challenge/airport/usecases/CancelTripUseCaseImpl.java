package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.TripsRepository;
import org.springframework.stereotype.Component;

@Component
public class CancelTripUseCaseImpl implements CancelTripUseCase {
  private final TripsRepository tripsRepository;

  public CancelTripUseCaseImpl(TripsRepository tripsRepository) {
    this.tripsRepository = tripsRepository;
  }

  @Override
  public void execute(String tripId) {
    tripsRepository.delete(tripId);
  }
}
