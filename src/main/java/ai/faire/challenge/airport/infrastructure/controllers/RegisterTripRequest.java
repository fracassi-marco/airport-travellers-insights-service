package ai.faire.challenge.airport.infrastructure.controllers;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RegisterTripRequest that = (RegisterTripRequest) o;

    if (!Objects.equals(originAirportCode, that.originAirportCode))
      return false;
    if (!Objects.equals(destinationAirportCode, that.destinationAirportCode))
      return false;
    if (!Objects.equals(departureDate, that.departureDate)) return false;
    return Objects.equals(returnDate, that.returnDate);
  }

  @Override
  public int hashCode() {
    int result = originAirportCode != null ? originAirportCode.hashCode() : 0;
    result = 31 * result + (destinationAirportCode != null ? destinationAirportCode.hashCode() : 0);
    result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
    result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
    return result;
  }
}
