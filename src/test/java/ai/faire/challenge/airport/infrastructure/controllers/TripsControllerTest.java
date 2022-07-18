package ai.faire.challenge.airport.infrastructure.controllers;

import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.use_cases.RegisterTripUseCase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

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

    when(registerTripUseCase.execute("LIN", "AMS", "2022-07-14", "2022-07-18")).thenReturn(
      new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction())
    );

    var response = tripsController.registerTrip(request);

    assertThat(response.getId()).isEqualTo("id");
  }

  private PurposePrediction aPrediction() {
    return new PurposePrediction("LEISURE", new BigDecimal("0.97"));
  }
}
