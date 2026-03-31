import java.util.List;
import java.util.Scanner;

public class QuickMap {
    public static void main(String[] args) {
        PlaceService service = new PlaceService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("QUICK MAP PROJECT");
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("1. See places");
        System.out.println("2. Add a new place");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();
        System.out.println();

        if ("1".equals(choice)) {
            showViewingOptions(service, scanner);
        } else if ("2".equals(choice)) {
            addNewPlace(service, scanner);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    private static void printPlaces(List<MapPlace> places) {
        for (MapPlace place : places) {
            System.out.println(place);
        }
        System.out.println();
    }

    private static void showViewingOptions(PlaceService service, Scanner scanner) {
        System.out.println("What do you want to see?");
        System.out.println("1. All places");
        System.out.println("2. Search place by name");
        System.out.println("3. Filter by category");
        System.out.println("4. Sort by rating");
        System.out.println("5. Sort by distance");
        System.out.println("6. Sort by popularity");
        System.out.print("Enter your choice: ");

        String viewChoice = scanner.nextLine().trim();
        System.out.println();

        switch (viewChoice) {
            case "1":
                System.out.println("All Places:");
                printPlaces(service.getAllPlaces());
                break;
            case "2":
                System.out.print("Enter place name: ");
                String placeName = scanner.nextLine();
                MapPlace foundPlace = service.searchPlace(placeName);
                System.out.println();
                System.out.println("Search Result:");
                if (foundPlace != null) {
                    System.out.println(foundPlace);
                } else {
                    System.out.println("Place not found.");
                }
                break;
            case "3":
                System.out.print("Enter category (CAFE, RESTAURANT, PARK, HOTEL, PICNIC_SPOT, HIDDEN_PLACE, TEMPLE, SHOPPING, HOSPITAL, PETROL_PUMP, TOURIST_PLACE): ");
                try {
                    PlaceCategory category = PlaceCategory.valueOf(scanner.nextLine().trim().toUpperCase());
                    System.out.println();
                    System.out.println("Filter By Category:");
                    printPlaces(service.getPlacesByCategory(category));
                } catch (IllegalArgumentException exception) {
                    System.out.println();
                    System.out.println("Invalid category.");
                }
                break;
            case "4":
                System.out.println("Sort By Rating:");
                printPlaces(service.getPlacesSortedByRating());
                break;
            case "5":
                System.out.println("Sort By Distance:");
                printPlaces(service.getPlacesSortedByDistance());
                break;
            case "6":
                System.out.println("Sort By Popularity:");
                printPlaces(service.getPlacesSortedByPopularity());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addNewPlace(PlaceService service, Scanner scanner) {
        try {
            System.out.print("Enter place name: ");
            String name = scanner.nextLine();

            System.out.print("Enter category (CAFE, RESTAURANT, PARK, HOTEL, PICNIC_SPOT, HIDDEN_PLACE, TEMPLE, SHOPPING, HOSPITAL, PETROL_PUMP, TOURIST_PLACE): ");
            PlaceCategory category = PlaceCategory.valueOf(scanner.nextLine().trim().toUpperCase());

            double rating = -1;
            if (category != PlaceCategory.TEMPLE) {
                System.out.print("Enter rating: ");
                rating = Double.parseDouble(scanner.nextLine().trim());
            }

            System.out.print("Enter popularity: ");
            int popularity = Integer.parseInt(scanner.nextLine().trim());

            System.out.println();
            System.out.println("New Place Added:");
            System.out.println(service.addPlace(name, category, rating, popularity));
        } catch (IllegalArgumentException exception) {
            System.out.println();
            System.out.println("Invalid input.");
        }
    }
}
