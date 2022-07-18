package ai.faire.challenge.airport.use_cases;

import ai.faire.challenge.airport.domain.AirportInsights;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;

import java.math.BigDecimal;
import java.util.List;

import static ai.faire.challenge.airport.util.StatisticUtil.probabilityOfIndependentEvents;
import static java.util.stream.Collectors.toList;

public class GetAirportInsightsUseCaseImpl {

  private final TripsRepository tripsRepository;

  public GetAirportInsightsUseCaseImpl(TripsRepository tripsRepository) {
    this.tripsRepository = tripsRepository;
  }

  public AirportInsights execute(String airportCode, String date) {
    List<Trip> trips = tripsRepository.filter(airportCode, date);
    List<Trip> leisureTrips = tripsFor(trips, "LEISURE");
    List<Trip> businessTrips = tripsFor(trips, "BUSINESS");

    return new AirportInsights(trips.size(),
      leisureTrips.size(),
      businessTrips.size(),
      probability(leisureTrips),
      probability(businessTrips));
  }

  private BigDecimal probability(List<Trip> trips) {
    if(trips.isEmpty())
      return null;

    List<BigDecimal> probabilities = trips.stream().map(Trip::probability).collect(toList());
    return probabilityOfIndependentEvents(probabilities);
  }

  private List<Trip> tripsFor(List<Trip> trips, String LEISURE) {
    return trips.stream().filter(it -> it.isFor(LEISURE)).collect(toList());
  }
}
