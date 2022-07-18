package ai.faire.challenge.airport.domain;

import java.math.BigDecimal;

public class Trip {
  private final String id;
  private final String originAirportCode;
  private final String destinationAirportCode;
  private final String departureDate;
  private final String returnDate;
  private final PurposePrediction purposePrediction;

  public Trip(String id, String originAirportCode, String destinationAirportCode, String departureDate, String returnDate, PurposePrediction purposePrediction) {
    this.id = id;
    this.originAirportCode = originAirportCode;
    this.destinationAirportCode = destinationAirportCode;
    this.departureDate = departureDate;
    this.returnDate = returnDate;
    this.purposePrediction = purposePrediction;
  }

  public String getId() {
    return id;
  }

  public boolean isFor(String purpose) {
    return purposePrediction.purpose().equals(purpose);
  }

  public BigDecimal probability() {
    return purposePrediction.probability();
  }

  public boolean matchDeparture(String airport, String date) {
    return originAirportCode.equals(airport) && departureDate.equals(date);
  }

  public boolean matchReturn(String airport, String date) {
    return destinationAirportCode.equals(airport) && returnDate.equals(date);
  }
}
