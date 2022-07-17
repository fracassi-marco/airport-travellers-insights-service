package ai.faire.challenge.airport.domain;

import java.util.Objects;

public class PurposePrediction {
  private final String purpose;
  private final double probability;

  public PurposePrediction(String purpose, double probability) {
    this.purpose = purpose;
    this.probability = probability;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PurposePrediction that = (PurposePrediction) o;

    if (Double.compare(that.probability, probability) != 0) return false;
    return Objects.equals(purpose, that.purpose);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = purpose != null ? purpose.hashCode() : 0;
    temp = Double.doubleToLongBits(probability);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
