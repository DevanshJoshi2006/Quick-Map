import java.util.ArrayList;
import java.util.List;

public class PlaceRepository {
    private List<MapPlace> places;

    public PlaceRepository() {
        places = new ArrayList<>();
        loadSampleData();
    }

    private void loadSampleData() {
        places.add(new MapPlace(1, "Blue Cafe", PlaceCategory.CAFE, 4.5, 2.1, 120));
        places.add(new MapPlace(2, "Spice Garden", PlaceCategory.RESTAURANT, 4.7, 3.4, 220));
        places.add(new MapPlace(3, "Green Park", PlaceCategory.PARK, 4.2, 1.5, 180));
        places.add(new MapPlace(4, "City Hotel", PlaceCategory.HOTEL, 4.0, 4.8, 140));
        places.add(new MapPlace(5, "Lake Picnic Spot", PlaceCategory.PICNIC_SPOT, 4.6, 6.2, 160));
        places.add(new MapPlace(6, "Sunset Point", PlaceCategory.HIDDEN_PLACE, 4.8, 7.5, 130));
        places.add(new MapPlace(7, "Shiv Temple", PlaceCategory.TEMPLE, -1, 2.9, 200));
        places.add(new MapPlace(8, "City Mall", PlaceCategory.SHOPPING, 4.3, 3.8, 260));
        places.add(new MapPlace(9, "Metro Hospital", PlaceCategory.HOSPITAL, 4.1, 2.4, 170));
        places.add(new MapPlace(10, "Fast Fuel Station", PlaceCategory.PETROL_PUMP, 3.9, 1.9, 150));
        places.add(new MapPlace(11, "Heritage Fort", PlaceCategory.TOURIST_PLACE, 4.9, 8.1, 300));
    }

    public List<MapPlace> getAllPlaces() {
        return new ArrayList<>(places);
    }

    public void addPlace(MapPlace place) {
        places.add(place);
    }

    public int getNextId() {
        return places.size() + 1;
    }
}
