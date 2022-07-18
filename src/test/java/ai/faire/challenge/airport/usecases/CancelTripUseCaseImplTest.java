package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.TripsRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CancelTripUseCaseImplTest {

  private final TripsRepository tripsRepository = mock(TripsRepository.class);
  private final CancelTripUseCaseImpl registerTripUseCase = new CancelTripUseCaseImpl(tripsRepository);

  @Test
  public void cancelTrip() {
    registerTripUseCase.execute("tripId");

    verify(tripsRepository).delete("tripId");
  }
}
