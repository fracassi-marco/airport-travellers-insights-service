package ai.faire.challenge.airport.use_cases;

import ai.faire.challenge.airport.domain.PredictionsService;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class RegisterTripUseCaseImplTest {

  private final TripsRepository tripsRepository = mock(TripsRepository.class);
  private final PredictionsService predictionsService = mock(PredictionsService.class);
  private final RegisterTripUseCaseImpl registerTripUseCase = new RegisterTripUseCaseImpl(tripsRepository, predictionsService);

  @Test
  void createTrip() {
    PurposePrediction prediction = new PurposePrediction("LEISURE", new BigDecimal(0.9984415));
    when(predictionsService.tripPurpose("LIN", "AMS", "2022-07-14", "2022-07-18")).thenReturn(prediction);

    registerTripUseCase.execute("LIN", "AMS", "2022-07-14", "2022-07-18");

    verify(tripsRepository).create(new Trip("LIN", "AMS", "2022-07-14", "2022-07-18", prediction));
  }
}
