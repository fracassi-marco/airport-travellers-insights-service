package ai.faire.challenge.airport.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class PurposePrediction {
  private final String purpose;
  private final BigDecimal probability;

  public PurposePrediction(String purpose, BigDecimal probability) {
    this.purpose = purpose;
    this.probability = probability;
  }

  public String getPurpose() {
    return purpose;
  }

  public BigDecimal getProbability() {
    return probability;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PurposePrediction that = (PurposePrediction) o;

    if (!Objects.equals(purpose, that.purpose)) return false;
    return Objects.equals(probability, that.probability);
  }

  @Override
  public int hashCode() {
    int result = purpose != null ? purpose.hashCode() : 0;
    result = 31 * result + (probability != null ? probability.hashCode() : 0);
    return result;
  }
}
