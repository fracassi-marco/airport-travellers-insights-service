package ai.faire.challenge.airport.infrastructure.controllers;

public class GetAirportInsightsResponse {
  private String airportCode;
  private String date;
  private int totalTravellers;
  private int leisurePurposeTravellers;
  private int businessPurposeTravellers;
  private String leisurePurposeProbability;
  private String businessPurposeProbability;

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

  public int getTotalTravellers() {
    return totalTravellers;
  }

  public void setTotalTravellers(int totalTravellers) {
    this.totalTravellers = totalTravellers;
  }

  public int getLeisurePurposeTravellers() {
    return leisurePurposeTravellers;
  }

  public void setLeisurePurposeTravellers(int leisurePurposeTravellers) {
    this.leisurePurposeTravellers = leisurePurposeTravellers;
  }

  public int getBusinessPurposeTravellers() {
    return businessPurposeTravellers;
  }

  public void setBusinessPurposeTravellers(int businessPurposeTravellers) {
    this.businessPurposeTravellers = businessPurposeTravellers;
  }

  public String getLeisurePurposeProbability() {
    return leisurePurposeProbability;
  }

  public void setLeisurePurposeProbability(String leisurePurposeProbability) {
    this.leisurePurposeProbability = leisurePurposeProbability;
  }

  public String getBusinessPurposeProbability() {
    return businessPurposeProbability;
  }

  public void setBusinessPurposeProbability(String businessPurposeProbability) {
    this.businessPurposeProbability = businessPurposeProbability;
  }
}
