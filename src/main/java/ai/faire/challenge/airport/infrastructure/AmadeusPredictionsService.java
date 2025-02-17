package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.PredictionsService;
import ai.faire.challenge.airport.domain.PurposePrediction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@Component
public class AmadeusPredictionsService implements PredictionsService {
  private final AmadeusConfig amadeusConfig;

  public AmadeusPredictionsService(AmadeusConfig amadeusConfig) {
    this.amadeusConfig = amadeusConfig;
  }

  @Override
  public PurposePrediction tripPurpose(String originAirportCode,
                                       String destinationAirportCode,
                                       String departureDate,
                                       String returnDate) {
    String url =  amadeusConfig.getEndpoint() + "/v1/travel/predictions/trip-purpose?"
      + "originLocationCode=" + originAirportCode
      + "&destinationLocationCode=" + destinationAirportCode
      + "&departureDate=" + departureDate
      + "&returnDate=" + returnDate
      + "&searchDate=" + "2020-01-01";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(getAccessToken());

    HttpEntity<Void> entity = new HttpEntity<>(headers);

    Map<String, Map<String, String>> response = new RestTemplate().exchange(url, GET, entity, Map.class).getBody();
    Map<String, String> data = response.get("data");

    return new PurposePrediction(data.get("result"), new BigDecimal(data.get("probability")));
  }

  private String getAccessToken() {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", "client_credentials");
    map.add("client_id", amadeusConfig.getClientId());
    map.add("client_secret", amadeusConfig.getClientSecret());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

    String url = amadeusConfig.getEndpoint() + "/v1/security/oauth2/token";
    Map<String, String> response = new RestTemplate().postForEntity(url, entity, Map.class).getBody();
    return response.get("access_token");
  }
}
