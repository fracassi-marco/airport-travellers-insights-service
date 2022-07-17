package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.PurposePrediction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AmadeusPredictionsServiceTest {
  //key r0TCN2h1I52etmG9G2g0nAGAHDpJJuwl
  //secrets hm5zJUkIQAuqUDyR

  private final AmadeusPredictionsService predictionsService = new AmadeusPredictionsService();

  @Test
  void tripPurposePrediction() {
    PurposePrediction purposePrediction = predictionsService.tripPurpose("NYC", "MAD", "2022-08-01", "2022-08-12");

    assertThat(purposePrediction).isEqualTo(new PurposePrediction("LEISURE", new BigDecimal("0.9975949")));
  }
}
