package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.AirportInsights;

public interface GetAirportInsightsUseCase {
  AirportInsights execute(String airportCode, String date);
}
