package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryTripsRepository implements TripsRepository {
  @Override
  public void create(Trip trip) {

  }

  @Override
  public List<Trip> filter(String airport, String date) {
    return null;
  }
}
