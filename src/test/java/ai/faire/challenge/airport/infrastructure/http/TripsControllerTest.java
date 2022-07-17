package ai.faire.challenge.airport.infrastructure.http;

import ai.faire.challenge.airport.use_cases.RegisterTripUseCase;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TripsControllerTest {

  private final RegisterTripUseCase registerTripUseCase = mock(RegisterTripUseCase.class);
  private final TripsController tripsController = new TripsController(registerTripUseCase);

  @Test
  void shouldCallUseCase() {
    var request = new RegisterTripRequest();
    request.setOriginAirportCode("LIN");
    request.setDestinationAirportCode("AMS");
    request.setDepartureDate("2022-07-14");
    request.setReturnDate("2022-07-18");

    tripsController.registerTrip(request);

    verify(registerTripUseCase).execute("LIN", "AMS", "2022-07-14", "2022-07-18");
  }
}
