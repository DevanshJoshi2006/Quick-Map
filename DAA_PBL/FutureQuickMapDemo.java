import java.util.List;
import java.util.Scanner;

public class FutureQuickMapDemo {
    public static void main(String[] args) {
        FuturePlaceRepository repository = new FuturePlaceRepository();
        NearbyPlaceFinder nearbyPlaceFinder = new NearbyPlaceFinder();
        RecommendationService recommendationService = new RecommendationService();
        MapLinkGenerator mapLinkGenerator = new MapLinkGenerator();
        SimplePlaceDatabase database = new SimplePlaceDatabase("future_places.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("QUICK MAP FUTURE MODULES DEMO");
        System.out.println("1. Show nearby places");
        System.out.println("2. Show Google Maps link of all places");
        System.out.println("3. Show recommended places");
        System.out.println("4. Save places in file");
        System.out.println("5. Show saved file data");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();
        System.out.println();

        if ("1".equals(choice)) {
            LocationPoint userLocation = new LocationPoint(23.2600, 77.4100);
            List<FutureMapPlace> nearbyPlaces = nearbyPlaceFinder.findNearbyPlaces(repository.getAllPlaces(), userLocation, 2.0);
            printPlaces(nearbyPlaces);
        } else if ("2".equals(choice)) {
            for (FutureMapPlace place : repository.getAllPlaces()) {
                System.out.println(place.getName() + ": " + mapLinkGenerator.createMapLink(place));
            }
        } else if ("3".equals(choice)) {
            printPlaces(recommendationService.getRecommendedPlaces(repository.getAllPlaces()));
        } else if ("4".equals(choice)) {
            database.savePlaces(repository.getAllPlaces());
        } else if ("5".equals(choice)) {
            database.showSavedPlaces();
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    private static void printPlaces(List<FutureMapPlace> places) {
        for (FutureMapPlace place : places) {
            System.out.println(place);
        }
    }
}
