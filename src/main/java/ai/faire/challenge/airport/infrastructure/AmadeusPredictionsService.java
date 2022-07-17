package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.PredictionsService;
import ai.faire.challenge.airport.domain.PurposePrediction;
import org.springframework.stereotype.Component;

@Component
public class AmadeusPredictionsService implements PredictionsService {
  @Override
  public PurposePrediction tripPurpose(String originAirportCode, String destinationAirportCode, String departureDate, String returnDate) {
    return null;
  }
}
