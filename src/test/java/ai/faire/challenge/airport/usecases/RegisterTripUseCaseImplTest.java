package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.PredictionsService;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class RegisterTripUseCaseImplTest {

  private final TripsRepository tripsRepository = mock(TripsRepository.class);
  private final PredictionsService predictionsService = mock(PredictionsService.class);
  private final RegisterTripUseCaseImpl registerTripUseCase = new RegisterTripUseCaseImpl(
    tripsRepository, predictionsService);

  @Test
  void createTrip() {
    PurposePrediction prediction = new PurposePrediction("LEISURE", new BigDecimal("0.9984415"));
    when(predictionsService.tripPurpose("LIN", "AMS", "2022-07-14", "2022-07-18")).thenReturn(prediction);

    registerTripUseCase.execute("LIN", "AMS", "2022-07-14", "2022-07-18");

    ArgumentCaptor<Trip> captor = ArgumentCaptor.forClass(Trip.class);
    verify(tripsRepository).create(captor.capture());
    assertThat(captor.getValue().isFor("LEISURE")).isTrue();
  }
}
