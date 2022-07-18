package ai.faire.challenge.airport.domain;

public interface PredictionsService {
  PurposePrediction tripPurpose(String originAirportCode,
                                String destinationAirportCode,
                                String departureDate,
                                String returnDate);
}
