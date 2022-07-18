package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.PurposePrediction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AmadeusPredictionsServiceTest {

  private final AmadeusPredictionsService predictionsService = new AmadeusPredictionsService(testConfig());

  @Test
  void tripPurposePrediction() {
    PurposePrediction purposePrediction = predictionsService.tripPurpose("NYC", "MAD", "2022-08-01", "2022-08-12");

    assertThat(purposePrediction.purpose()).isEqualTo("LEISURE");
    assertThat(purposePrediction.probability()).isEqualTo(new BigDecimal("0.99790406"));
  }

  private AmadeusConfig testConfig() {
    AmadeusConfig amadeusConfig = new AmadeusConfig();
    amadeusConfig.setClientId("r0TCN2h1I52etmG9G2g0nAGAHDpJJuwl");
    amadeusConfig.setClientSecret("hm5zJUkIQAuqUDyR");
    amadeusConfig.setEndpoint("https://test.api.amadeus.com");
    return amadeusConfig;
  }
}
