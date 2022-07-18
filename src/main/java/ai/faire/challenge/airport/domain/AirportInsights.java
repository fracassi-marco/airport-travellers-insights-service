package ai.faire.challenge.airport.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class AirportInsights {
  private final int totalTravellers;
  private final int leisurePurposeTravellers;
  private final int businessPurposeTravellers;
  private final BigDecimal leisurePurposeProbability;
  private final BigDecimal businessPurposeProbability;

  public AirportInsights(int totalTravellers,
                         int leisurePurposeTravellers,
                         int businessPurposeTravellers,
                         BigDecimal leisurePurposeProbability,
                         BigDecimal businessPurposeProbability) {
    this.totalTravellers = totalTravellers;
    this.leisurePurposeTravellers = leisurePurposeTravellers;
    this.businessPurposeTravellers = businessPurposeTravellers;
    this.leisurePurposeProbability = leisurePurposeProbability;
    this.businessPurposeProbability = businessPurposeProbability;
  }

  public int getTotalTravellers() {
    return totalTravellers;
  }

  public int getLeisurePurposeTravellers() {
    return leisurePurposeTravellers;
  }

  public int getBusinessPurposeTravellers() {
    return businessPurposeTravellers;
  }

  public BigDecimal getLeisurePurposeProbability() {
    return leisurePurposeProbability;
  }

  public BigDecimal getBusinessPurposeProbability() {
    return businessPurposeProbability;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) { return true; }
    if (obj == null || getClass() != obj.getClass()) { return false; }

    AirportInsights that = (AirportInsights) obj;

    if (totalTravellers != that.totalTravellers) {
      return false;
    }
    if (leisurePurposeTravellers != that.leisurePurposeTravellers) {
      return false;
    }
    if (businessPurposeTravellers != that.businessPurposeTravellers) {
      return false;
    }
    if (!Objects.equals(leisurePurposeProbability, that.leisurePurposeProbability)) {
      return false;
    }
    return Objects.equals(businessPurposeProbability, that.businessPurposeProbability);
  }

  @Override
  public int hashCode() {
    int result = totalTravellers;
    result = 31 * result + leisurePurposeTravellers;
    result = 31 * result + businessPurposeTravellers;
    result = 31 * result + (leisurePurposeProbability != null ? leisurePurposeProbability.hashCode() : 0);
    result = 31 * result + (businessPurposeProbability != null ? businessPurposeProbability.hashCode() : 0);
    return result;
  }
}
