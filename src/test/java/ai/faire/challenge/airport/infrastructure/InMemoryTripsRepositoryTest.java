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
  public void foundOnDepartureDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-14");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  public void notFoundOnDepartureDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("AMS", "2022-07-14");

    assertThat(trips.size()).isEqualTo(0);
  }

  @Test
  public void foundOnReturnDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("AMS", "2022-07-18");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  public void notFoundOnReturnDate() {
    repository.create(new Trip("id", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-18");

    assertThat(trips.size()).isEqualTo(0);
  }

  @Test
  public void filterByAirport() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));
    repository.create(new Trip("id2", "AMS", "LIN", "2022-07-14", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-18");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  public void filterByDate() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));
    repository.create(new Trip("id2", "LIN", "AMS", "2022-07-15", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("LIN", "2022-07-14");

    assertThat(trips.size()).isEqualTo(1);
  }

  @Test
  public void noMatch() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));
    repository.create(new Trip("id2", "LIN", "AMS", "2022-07-15", "2022-07-18", prediction()));

    List<Trip> trips = repository.filter("ABI", "2022-07-13");

    assertThat(trips.size()).isEqualTo(0);
  }

  @Test
  public void deleteTrip() {
    repository.create(new Trip("id1", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));
    repository.create(new Trip("id2", "LIN", "AMS", "2022-07-14", "2022-07-18", prediction()));

    repository.delete("id1");

    List<Trip> trips = repository.filter("LIN", "2022-07-14");
    assertThat(trips.size()).isEqualTo(1);
  }

  private PurposePrediction prediction() {
    return new PurposePrediction("LEISURE", new BigDecimal("0.97"));
  }
}
