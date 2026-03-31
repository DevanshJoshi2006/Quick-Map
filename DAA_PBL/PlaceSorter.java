import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlaceSorter {
    public List<MapPlace> sortByRating(List<MapPlace> places) {
        List<MapPlace> sortedPlaces = new ArrayList<>(places);
        sortedPlaces.sort(Comparator.comparingDouble((MapPlace place) ->
                place.getRating() < 0 ? Double.MIN_VALUE : place.getRating()).reversed());
        return sortedPlaces;
    }

    public List<MapPlace> sortByDistance(List<MapPlace> places) {
        List<MapPlace> sortedPlaces = new ArrayList<>(places);
        sortedPlaces.sort(Comparator.comparingDouble(place ->
                place.getDistance() < 0 ? Double.MAX_VALUE : place.getDistance()));
        return sortedPlaces;
    }

    public List<MapPlace> sortByPopularity(List<MapPlace> places) {
        List<MapPlace> sortedPlaces = new ArrayList<>(places);
        sortedPlaces.sort(Comparator.comparingInt(MapPlace::getPopularity).reversed());
        return sortedPlaces;
    }
}
