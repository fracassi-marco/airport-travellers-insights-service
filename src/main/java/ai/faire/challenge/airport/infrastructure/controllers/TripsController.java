package ai.faire.challenge.airport.infrastructure.controllers;

import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.use_cases.CancelTripUseCase;
import ai.faire.challenge.airport.use_cases.RegisterTripUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trips", produces = "application/json")
public class TripsController {

  private final RegisterTripUseCase registerTripUseCase;
  private final CancelTripUseCase cancelTripUseCase;

  public TripsController(RegisterTripUseCase registerTripUseCase, CancelTripUseCase cancelTripUseCase) {
    this.registerTripUseCase = registerTripUseCase;
    this.cancelTripUseCase = cancelTripUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterTripResponse registerTrip(@RequestBody RegisterTripRequest request) {
    Trip trip = registerTripUseCase.execute(request.getOriginAirportCode(), request.getDestinationAirportCode(), request.getDepartureDate(), request.getReturnDate());
    RegisterTripResponse response = new RegisterTripResponse();
    response.setId(trip.getId());
    response.setOriginAirportCode(request.getOriginAirportCode());
    response.setDestinationAirportCode(request.getDestinationAirportCode());
    response.setDepartureDate(request.getDepartureDate());
    response.setReturnDate(request.getReturnDate());
    return response;
  }

  @DeleteMapping("/{id}")
  public void cancelTrip(@PathVariable("id") String tripId) {
    cancelTripUseCase.execute(tripId);
  }
}
