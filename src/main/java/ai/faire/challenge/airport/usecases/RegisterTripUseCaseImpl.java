package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.PredictionsService;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterTripUseCaseImpl implements RegisterTripUseCase {
  private final TripsRepository tripsRepository;
  private final PredictionsService predictionsService;

  public RegisterTripUseCaseImpl(TripsRepository tripsRepository, PredictionsService predictionsService) {
    this.tripsRepository = tripsRepository;
    this.predictionsService = predictionsService;
  }

  @Override
  public Trip execute(String originAirport, String destinationAirport, String departureDate, String returnDate) {
    PurposePrediction purposePrediction = predictionsService.tripPurpose(originAirport, destinationAirport,
      departureDate, returnDate);
    Trip trip = new Trip(uuid(), originAirport, destinationAirport, departureDate, returnDate, purposePrediction);
    tripsRepository.create(trip);

    return trip;
  }

  private String uuid() {
    return UUID.randomUUID().toString();
  }
}
