package ai.faire.challenge.airport.infrastructure.controllers;

public class RegisterTripRequest {

  private String originAirportCode;
  private String destinationAirportCode;
  private String departureDate;
  private String returnDate;

  public String getOriginAirportCode() {
    return originAirportCode;
  }

  public void setOriginAirportCode(String originAirportCode) {
    this.originAirportCode = originAirportCode;
  }

  public String getDestinationAirportCode() {
    return destinationAirportCode;
  }

  public void setDestinationAirportCode(String destinationAirportCode) {
    this.destinationAirportCode = destinationAirportCode;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = departureDate;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
  }
}
