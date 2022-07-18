package ai.faire.challenge.airport.infrastructure.controllers;

public class GetAirportInsightsRequest {

  private String airportCode;
  private String date;

  public String getAirportCode() {
    return airportCode;
  }

  public void setAirportCode(String airportCode) {
    this.airportCode = airportCode;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
