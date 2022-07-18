package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.PurposePrediction;
import ai.faire.challenge.airport.domain.Trip;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InMemoryTripsRepositoryTest {

  private final InMemoryTripsRepository repository = new InMemoryTripsRepository();

  @Test
  void foundOnDepartureDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-14");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  void notFoundOnDepartureDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("AMS", "2022-07-14");

    assertThat(trips.size()).isEqualTo(0);
  }

  @Test
  void foundOnReturnDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("AMS", "2022-07-18");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  void notFoundOnReturnDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-18");

    assertThat(trips.size()).isEqualTo(0);
  }

  @Test
  void filterByAirport() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));
    repository.create(new Trip("id2", "AMS", "LIN", "2022-07-14", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-18");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  void filterByDate() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));
    repository.create(new Trip("id2", "LIN", "AMS", "2022-07-15", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-14");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  void noMatch() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", aPrediction()));
    repository.create(new Trip("id2", "LIN", "AMS", "2022-07-15", "2022-07-18", aPrediction()));

    List<Trip> trips = repository.filter("ABI", "2022-07-13");

    assertThat(trips.size()).isEqualTo(0);
  }

  private PurposePrediction aPrediction() {
    return new PurposePrediction("LEISURE", new BigDecimal("0.97"));
  }
}
