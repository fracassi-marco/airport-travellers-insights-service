package ai.faire.challenge.airport.infrastructure.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
public class TripsControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int randomServerPort;

  private URI baseUri;

  @BeforeEach
  void setUp() {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance().scheme("http")
      .host("localhost")
      .port(randomServerPort)
      .path("/");

    baseUri = uriBuilder.build().toUri();
  }

  @Test
  void registerATrip() {
    var request = new RegisterTripRequest();
    request.setOriginAirportCode("LIN");
    request.setDestinationAirportCode("AMS");
    request.setDepartureDate("2022-07-14");
    request.setReturnDate("2022-07-18");

    var response = restTemplate.postForEntity(baseUri + "/trips", request, RegisterTripRequest.class);

    assertThat(response.getStatusCode()).isEqualTo(CREATED);
    assertThat(response.getBody()).isEqualTo(request);
  }
}
