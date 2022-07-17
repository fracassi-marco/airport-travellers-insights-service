package ai.faire.challenge.airport.use_cases;

public interface RegisterTripUseCase {
  void execute(String originAirportCode, String destinationAirportCode, String departureDate, String returnDate);
}
