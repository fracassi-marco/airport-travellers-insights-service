package ai.faire.challenge.airport;

import ai.faire.challenge.airport.infrastructure.controllers.GetAirportInsightsRequest;
import ai.faire.challenge.airport.infrastructure.controllers.GetAirportInsightsResponse;
import ai.faire.challenge.airport.infrastructure.controllers.RegisterTripRequest;
import ai.faire.challenge.airport.infrastructure.controllers.RegisterTripResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("integrationTest")
class AcceptanceTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int randomServerPort;

  private URI baseUri;

  @BeforeEach
  public void setUp() {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance().scheme("http")
      .host("localhost")
      .port(randomServerPort)
      .path("/");

    baseUri = uriBuilder.build().toUri();
  }

  @Test
  void registerAndCancelAirportsAndGetInsights() throws Exception {
    registerTrip("LIN", "AMS", "2022-07-14", "2022-07-18");
    waitToAvoidTooManyRequests();
    String tripId = registerTrip("BRU", "LIN", "2022-07-12", "2022-07-14");

    assertThat(getAirportInsights("LIN", "2022-07-14").getTotalTravellers()).isEqualTo(2);

    cancelTrip(tripId);

    assertThat(getAirportInsights("LIN", "2022-07-14").getTotalTravellers()).isEqualTo(1);
  }

  private void waitToAvoidTooManyRequests() throws InterruptedException {
    Thread.sleep(1500);
  }

  private void cancelTrip(String tripId) {
    ResponseEntity<Void> response = restTemplate.exchange(baseUri + "/trips/" + tripId, DELETE, null, Void.class);
    assertThat(response.getStatusCode()).isEqualTo(OK);
  }

  private GetAirportInsightsResponse getAirportInsights(String airportCode, String date) {
    var request = new GetAirportInsightsRequest();
    request.setAirportCode(airportCode);
    request.setDate(date);

    var response = restTemplate.postForEntity(baseUri + "/airport-insights", request, GetAirportInsightsResponse.class);
    assertThat(response.getStatusCode()).isEqualTo(OK);
    return response.getBody();
  }

  private String registerTrip(String originAirport, String destAirport, String departureDate, String returnDate) {
    var request = new RegisterTripRequest();
    request.setOriginAirportCode(originAirport);
    request.setDestinationAirportCode(destAirport);
    request.setDepartureDate(departureDate);
    request.setReturnDate(returnDate);

    var response = restTemplate.postForEntity(baseUri + "/trips", request, RegisterTripResponse.class);
    assertThat(response.getStatusCode()).isEqualTo(CREATED);

    return response.getBody().getId();
  }
}
