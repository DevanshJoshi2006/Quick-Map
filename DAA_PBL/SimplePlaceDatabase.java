import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimplePlaceDatabase {
    private String fileName;

    public SimplePlaceDatabase(String fileName) {
        this.fileName = fileName;
    }

    public void savePlaces(List<FutureMapPlace> places) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for (FutureMapPlace place : places) {
                writer.write(place.getId() + ","
                        + place.getName() + ","
                        + place.getCategory() + ","
                        + place.getRating() + ","
                        + place.getPopularity() + ","
                        + place.getLocation().getLatitude() + ","
                        + place.getLocation().getLongitude() + "\n");
            }

            writer.close();
            System.out.println("Places saved in file: " + fileName);
        } catch (IOException exception) {
            System.out.println("Unable to save places.");
        }
    }

    public void showSavedPlaces() {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
        } catch (IOException exception) {
            System.out.println("No saved file found.");
        }
    }

    public List<FutureMapPlace> loadPlaces() {
        List<FutureMapPlace> places = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length == 7) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    PlaceCategory category = PlaceCategory.valueOf(data[2]);
                    double rating = Double.parseDouble(data[3]);
                    int popularity = Integer.parseInt(data[4]);
                    double latitude = Double.parseDouble(data[5]);
                    double longitude = Double.parseDouble(data[6]);

                    places.add(new FutureMapPlace(
                            id,
                            name,
                            category,
                            rating,
                            popularity,
                            new LocationPoint(latitude, longitude)));
                }
            }

            scanner.close();
        } catch (Exception exception) {
            return places;
        }

        return places;
    }
}
