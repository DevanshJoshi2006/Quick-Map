public class FutureMapPlace {
    private int id;
    private String name;
    private PlaceCategory category;
    private double rating;
    private int popularity;
    private LocationPoint location;

    public FutureMapPlace(int id, String name, PlaceCategory category, double rating, int popularity, LocationPoint location) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.popularity = popularity;
        this.location = location;
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

    public int getPopularity() {
        return popularity;
    }

    public LocationPoint getLocation() {
        return location;
    }

    @Override
    public String toString() {
        String ratingText = category == PlaceCategory.TEMPLE ? "Rating: Not needed" : "Rating: " + rating;
        return id + " | " + name + " | " + category + " | " + ratingText
                + " | Popularity: " + popularity + " | Location: " + location;
    }
}
