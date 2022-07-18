package ai.faire.challenge.airport.use_cases;

import ai.faire.challenge.airport.domain.PredictionsService;
import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CancelTripUseCaseImplTest {

  private final TripsRepository tripsRepository = mock(TripsRepository.class);
  private final CancelTripUseCaseImpl registerTripUseCase = new CancelTripUseCaseImpl(tripsRepository);

  @Test
  public void cancelTrip() {
    registerTripUseCase.execute("tripId");

    verify(tripsRepository).delete("tripId");
  }
}
