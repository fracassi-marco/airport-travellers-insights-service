package ai.faire.challenge.airport.infrastructure.controllers;

import ai.faire.challenge.airport.domain.AirportInsights;
import ai.faire.challenge.airport.use_cases.GetAirportInsightsUseCase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class AirportInsightsControllerTest {

  private final GetAirportInsightsUseCase useCase = mock(GetAirportInsightsUseCase.class);
  private final AirportInsightsController controller = new AirportInsightsController(useCase);

  @Test
  void buildResponse() {
    var request = new GetAirportInsightsRequest();
    request.setAirportCode("LIN");
    request.setDate("2022-07-18");

    when(useCase.execute("LIN", "2022-07-18")).thenReturn(new AirportInsights(1, 1, 0, new BigDecimal("0.75"), null));

    GetAirportInsightsResponse response = controller.getAirportInsights(request);

    assertThat(response.getAirportCode()).isEqualTo("LIN");
    assertThat(response.getDate()).isEqualTo("2022-07-18");
    assertThat(response.getTotalTravellers()).isEqualTo(1);
    assertThat(response.getLeisurePurposeTravellers()).isEqualTo(1);
    assertThat(response.getBusinessPurposeTravellers()).isEqualTo(0);
    assertThat(response.getLeisurePurposeProbability()).isEqualTo("0.75");
    assertThat(response.getBusinessPurposeProbability()).isNull();
  }
}
