package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTripsRepository implements TripsRepository {
  @Override
  public void create(Trip trip) {

  }
}
