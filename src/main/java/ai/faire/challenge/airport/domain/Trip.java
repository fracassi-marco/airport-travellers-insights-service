package ai.faire.challenge.airport.domain;

import java.math.BigDecimal;
import java.util.Objects;

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
    return purposePrediction.getPurpose().equals(purpose);
  }

  public BigDecimal probability() {
    return purposePrediction.getProbability();
  }

  public boolean matchDeparture(String airport, String date) {
    return originAirportCode.equals(airport) && departureDate.equals(date);
  }

  public boolean matchReturn(String airport, String date) {
    return destinationAirportCode.equals(airport) && returnDate.equals(date);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Trip trip = (Trip) o;

    if (!Objects.equals(id, trip.id))
      return false;
    if (!Objects.equals(originAirportCode, trip.originAirportCode))
      return false;
    if (!Objects.equals(destinationAirportCode, trip.destinationAirportCode))
      return false;
    if (!Objects.equals(departureDate, trip.departureDate)) return false;
    if (!Objects.equals(returnDate, trip.returnDate)) return false;
    return Objects.equals(purposePrediction, trip.purposePrediction);
  }

  @Override
  public int hashCode() {
    int result = originAirportCode != null ? originAirportCode.hashCode() : 0;
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + (destinationAirportCode != null ? destinationAirportCode.hashCode() : 0);
    result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
    result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
    result = 31 * result + (purposePrediction != null ? purposePrediction.hashCode() : 0);
    return result;
  }
}
