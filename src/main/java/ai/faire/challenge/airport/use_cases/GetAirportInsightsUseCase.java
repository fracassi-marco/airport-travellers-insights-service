package ai.faire.challenge.airport.use_cases;

import ai.faire.challenge.airport.domain.AirportInsights;

public interface GetAirportInsightsUseCase {
  AirportInsights execute(String airportCode, String date);
}
