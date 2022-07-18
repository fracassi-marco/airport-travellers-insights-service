package ai.faire.challenge.airport.infrastructure.controllers;

import ai.faire.challenge.airport.domain.AirportInsights;
import ai.faire.challenge.airport.usecases.GetAirportInsightsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("integrationTest")
public class AirportInsightsControllerTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int randomServerPort;

  @MockBean
  private GetAirportInsightsUseCase useCase;

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
  public void getAirportInsights() {
    var request = new GetAirportInsightsRequest();
    request.setAirportCode("LIN");
    request.setDate("2022-09-18");

    when(useCase.execute("LIN", "2022-09-18")).thenReturn(new AirportInsights(1, 1, 0, new BigDecimal("0.99"), null));

    var response = restTemplate.postForEntity(baseUri + "/airport-insights", request, GetAirportInsightsResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(OK);
    assertThat(response.getBody()).isNotNull();
  }
}
