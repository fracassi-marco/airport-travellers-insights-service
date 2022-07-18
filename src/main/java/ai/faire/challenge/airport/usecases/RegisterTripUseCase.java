package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.Trip;

public interface RegisterTripUseCase {
  Trip execute(String originAirportCode, String destinationAirportCode, String departureDate, String returnDate);
}
