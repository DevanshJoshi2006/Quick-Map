import java.util.ArrayList;
import java.util.List;

public class PlaceService {
    private PlaceRepository repository;
    private PlaceNameSearch nameSearch;
    private PlaceSorter sorter;

    public PlaceService() {
        repository = new PlaceRepository();
        nameSearch = new PlaceNameSearch();
        sorter = new PlaceSorter();
    }

    public List<MapPlace> getAllPlaces() {
        return repository.getAllPlaces();
    }

    public MapPlace searchPlace(String name) {
        return nameSearch.searchByName(repository.getAllPlaces(), name);
    }

    public List<MapPlace> getPlacesByCategory(PlaceCategory category) {
        List<MapPlace> result = new ArrayList<>();

        for (MapPlace place : repository.getAllPlaces()) {
            if (place.getCategory() == category) {
                result.add(place);
            }
        }

        return result;
    }

    public List<MapPlace> getPlacesSortedByRating() {
        return sorter.sortByRating(repository.getAllPlaces());
    }

    public List<MapPlace> getPlacesSortedByDistance() {
        return sorter.sortByDistance(repository.getAllPlaces());
    }

    public List<MapPlace> getPlacesSortedByPopularity() {
        return sorter.sortByPopularity(repository.getAllPlaces());
    }

    public MapPlace addPlace(String name, PlaceCategory category, double rating, int popularity) {
        MapPlace newPlace = new MapPlace(repository.getNextId(), name, category, rating, -1, popularity);
        repository.addPlace(newPlace);
        return newPlace;
    }
}
