package ai.faire.challenge.airport.infrastructure;

import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class InMemoryTripsRepository implements TripsRepository {

  private List<Trip> trips = new ArrayList<>();

  @Override
  public void create(Trip trip) {
    trips.add(trip);
  }

  @Override
  public List<Trip> filter(String airport, String date) {
    return trips.stream()
      .filter(it -> it.matchDeparture(airport, date) || it.matchReturn(airport, date))
      .collect(toList());
  }

  @Override
  public void delete(String tripId) {
    trips.removeIf(it -> it.getId().equals(tripId));
  }
}
