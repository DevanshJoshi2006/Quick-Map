public class MapLinkGenerator {
    public String createMapLink(FutureMapPlace place) {
        LocationPoint location = place.getLocation();
        return "https://www.google.com/maps/search/?api=1&query="
                + location.getLatitude() + "," + location.getLongitude();
    }
}
