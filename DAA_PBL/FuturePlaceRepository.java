import java.util.ArrayList;
import java.util.List;

public class FuturePlaceRepository {
    private List<FutureMapPlace> places;

    public FuturePlaceRepository() {
        places = new ArrayList<>();
        loadSampleData();
    }

    private void loadSampleData() {
        places.add(new FutureMapPlace(1, "Blue Cafe", PlaceCategory.CAFE, 4.5, 120, new LocationPoint(23.2599, 77.4126)));
        places.add(new FutureMapPlace(2, "Spice Garden", PlaceCategory.RESTAURANT, 4.7, 220, new LocationPoint(23.2630, 77.4190)));
        places.add(new FutureMapPlace(3, "Green Park", PlaceCategory.PARK, 4.2, 180, new LocationPoint(23.2510, 77.4010)));
        places.add(new FutureMapPlace(4, "Shiv Temple", PlaceCategory.TEMPLE, -1, 200, new LocationPoint(23.2670, 77.4105)));
        places.add(new FutureMapPlace(5, "Heritage Fort", PlaceCategory.TOURIST_PLACE, 4.9, 300, new LocationPoint(23.2450, 77.3900)));
    }

    public List<FutureMapPlace> getAllPlaces() {
        return new ArrayList<>(places);
    }

    public void addPlace(FutureMapPlace place) {
        places.add(place);
    }

    public void replacePlaces(List<FutureMapPlace> newPlaces) {
        places = new ArrayList<>(newPlaces);
    }

    public int getNextId() {
        return places.size() + 1;
    }
}
