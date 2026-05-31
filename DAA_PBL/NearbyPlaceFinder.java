import java.util.ArrayList;
import java.util.List;

public class NearbyPlaceFinder {
    private DistanceCalculator distanceCalculator;

    public NearbyPlaceFinder() {
        distanceCalculator = new DistanceCalculator();
    }

    public List<FutureMapPlace> findNearbyPlaces(List<FutureMapPlace> places, LocationPoint userLocation, double maximumDistance) {
        List<FutureMapPlace> nearbyPlaces = new ArrayList<>();

        for (FutureMapPlace place : places) {
            double distance = distanceCalculator.calculateDistance(userLocation, place.getLocation());

            if (distance <= maximumDistance) {
                nearbyPlaces.add(place);
            }
        }

        return nearbyPlaces;
    }
}
