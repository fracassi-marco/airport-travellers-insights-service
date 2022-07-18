package ai.faire.challenge.airport.domain;

import java.util.List;

public interface TripsRepository {
  void create(Trip trip);
  List<Trip> filter(String airport, String date);
  void delete(String tripId);
}
