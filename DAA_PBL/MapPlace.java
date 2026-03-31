public class MapPlace {
    private int id;
    private String name;
    private PlaceCategory category;
    private double rating;
    private double distance;
    private int popularity;

    public MapPlace(int id, String name, PlaceCategory category, double rating, double distance, int popularity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.distance = distance;
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlaceCategory getCategory() {
        return category;
    }

    public double getRating() {
        return rating;
    }

    public double getDistance() {
        return distance;
    }

    public int getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        String distanceText = distance < 0 ? "Not available" : distance + " km";
        String ratingText = category == PlaceCategory.TEMPLE ? "" : " | Rating: " + rating;

        return id + " | " + name + " | " + category
                + ratingText
                + " | Distance: " + distanceText
                + " | Popularity: " + popularity;
    }
}
