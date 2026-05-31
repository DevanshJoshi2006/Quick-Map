public class DistanceCalculator {
    public double calculateDistance(LocationPoint firstLocation, LocationPoint secondLocation) {
        double latitudeDifference = firstLocation.getLatitude() - secondLocation.getLatitude();
        double longitudeDifference = firstLocation.getLongitude() - secondLocation.getLongitude();

        double simpleDistance = Math.sqrt(
                latitudeDifference * latitudeDifference
                        + longitudeDifference * longitudeDifference);

        return simpleDistance * 111;
    }
}
