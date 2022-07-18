package ai.faire.challenge.airport.usecases;

import ai.faire.challenge.airport.domain.AirportInsights;
import ai.faire.challenge.airport.domain.Trip;
import ai.faire.challenge.airport.domain.TripsRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static ai.faire.challenge.airport.util.StatisticUtil.probabilityOfIndependentEvents;
import static java.util.stream.Collectors.toList;

@Component
public class GetAirportInsightsUseCaseImpl implements GetAirportInsightsUseCase {

  private final TripsRepository tripsRepository;

  public GetAirportInsightsUseCaseImpl(TripsRepository tripsRepository) {
    this.tripsRepository = tripsRepository;
  }

  @Override
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
    if (trips.isEmpty()) {
      return null;
    }

    List<BigDecimal> probabilities = trips.stream().map(Trip::probability).collect(toList());
    return probabilityOfIndependentEvents(probabilities);
  }

  private List<Trip> tripsFor(List<Trip> trips, String purpose) {
    return trips.stream().filter(it -> it.isFor(purpose)).collect(toList());
  }
}
