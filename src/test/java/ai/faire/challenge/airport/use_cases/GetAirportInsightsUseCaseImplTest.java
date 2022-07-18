package ai.faire.challenge.airport.use_cases;

import ai.faire.challenge.airport.domain.AirportInsights;
import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAirportInsightsUseCaseImplTest {

  private final TripsRepository tripsRepository = mock(TripsRepository.class);
  private final GetAirportInsightsUseCaseImpl useCase = new GetAirportInsightsUseCaseImpl(tripsRepository);

  @Test
  void buildInsights() {
    when(tripsRepository.filter("LIN", "2022-07-14")).thenReturn(List.of(
      new Trip("LIN", "AMS", "2022-07-14", "2022-07-18", new PurposePrediction("LEISURE", new BigDecimal("0.97"))),
      new Trip("BRU", "LIN", "2022-07-12", "2022-07-14", new PurposePrediction("BUSINESS", new BigDecimal("0.95")))
    ));

    AirportInsights result = useCase.execute("LIN", "2022-07-14");

    assertThat(result).isEqualTo(new AirportInsights(2, 1, 1, new BigDecimal("0.97"), new BigDecimal("0.95")));
  }

  @Test
  void buildInsightsWithoutLeisureTravellers() {
    when(tripsRepository.filter("LIN", "2022-07-14")).thenReturn(List.of(
      new Trip("BRU", "LIN", "2022-07-12", "2022-07-14", new PurposePrediction("BUSINESS", new BigDecimal("0.95")))
    ));

    AirportInsights result = useCase.execute("LIN", "2022-07-14");

    assertThat(result).isEqualTo(new AirportInsights(1, 0, 1, null, new BigDecimal("0.95")));
  }
}
