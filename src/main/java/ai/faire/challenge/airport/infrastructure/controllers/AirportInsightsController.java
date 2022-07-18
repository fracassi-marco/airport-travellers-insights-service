package ai.faire.challenge.airport.infrastructure.controllers;

import ai.faire.challenge.airport.domain.AirportInsights;
import ai.faire.challenge.airport.usecases.GetAirportInsightsUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "airport-insights", produces = "application/json")
public class AirportInsightsController {
  private final GetAirportInsightsUseCase getAirportInsightsUseCase;

  public AirportInsightsController(GetAirportInsightsUseCase getAirportInsightsUseCase) {
    this.getAirportInsightsUseCase = getAirportInsightsUseCase;
  }

  @PostMapping
  public GetAirportInsightsResponse getAirportInsights(@RequestBody GetAirportInsightsRequest request) {
    AirportInsights airportInsights = getAirportInsightsUseCase.execute(request.getAirportCode(), request.getDate());
    GetAirportInsightsResponse response = new GetAirportInsightsResponse();
    response.setAirportCode(request.getAirportCode());
    response.setDate(request.getDate());
    response.setTotalTravellers(airportInsights.getTotalTravellers());
    response.setLeisurePurposeTravellers(airportInsights.getLeisurePurposeTravellers());
    response.setLeisurePurposeProbability(safe(airportInsights.getLeisurePurposeProbability()));
    response.setBusinessPurposeTravellers(airportInsights.getBusinessPurposeTravellers());
    response.setBusinessPurposeProbability(safe(airportInsights.getBusinessPurposeProbability()));
    return response;
  }

  private String safe(BigDecimal probability) {
    if (probability == null) {
      return null;
    }
    return probability.toString();
  }
}
