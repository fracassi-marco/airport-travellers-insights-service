package ai.faire.challenge.airport.infrastructure.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "trips",produces = "application/json")
public class TripsController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterTripRequest registerTrip(@RequestBody RegisterTripRequest request) {
    return request;
  }
}
