import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecommendationService {
    public List<FutureMapPlace> getRecommendedPlaces(List<FutureMapPlace> places) {
        List<FutureMapPlace> recommendedPlaces = new ArrayList<>(places);

        recommendedPlaces.sort(Comparator.comparingDouble(this::getRecommendationScore).reversed());

        return recommendedPlaces;
    }

    private double getRecommendationScore(FutureMapPlace place) {
        double ratingScore = place.getRating() < 0 ? 0 : place.getRating() * 20;
        double popularityScore = place.getPopularity() / 10.0;

        return ratingScore + popularityScore;
    }
}
