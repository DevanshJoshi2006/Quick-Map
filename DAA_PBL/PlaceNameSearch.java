import java.util.List;

public class PlaceNameSearch {
    public MapPlace searchByName(List<MapPlace> places, String targetName) {
        for (MapPlace place : places) {
            if (place.getName().equalsIgnoreCase(targetName)) {
                return place;
            }
        }
        return null;
    }
}
