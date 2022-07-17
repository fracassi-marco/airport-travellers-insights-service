package ai.faire.challenge.airport.infrastructure.http;

import ai.faire.challenge.airport.use_cases.RegisterTripUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "trips", produces = "application/json")
public class TripsController {

  private final RegisterTripUseCase registerTripUseCase;

  public TripsController(RegisterTripUseCase registerTripUseCase) {
    this.registerTripUseCase = registerTripUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterTripRequest registerTrip(@RequestBody RegisterTripRequest request) {
    registerTripUseCase.execute(request.getOriginAirportCode(), request.getDestinationAirportCode(), request.getDepartureDate(), request.getReturnDate());
    return request;
  }
}
