import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickMapFrontend extends JFrame {
    private FuturePlaceRepository repository;
    private NearbyPlaceFinder nearbyPlaceFinder;
    private RecommendationService recommendationService;
    private MapLinkGenerator mapLinkGenerator;
    private SimplePlaceDatabase database;
    private FrontendTableHelper tableHelper;

    private JPanel topAreaPanel;
    private JTable placeTable;
    private JTextField searchField;
    private JComboBox<String> categoryBox;

    private Color pageColor = new Color(244, 247, 250);
    private Color primaryColor = new Color(35, 78, 112);
    private Color successColor = new Color(46, 125, 95);
    private Color warningColor = new Color(196, 129, 45);

    public QuickMapFrontend() {
        repository = new FuturePlaceRepository();
        nearbyPlaceFinder = new NearbyPlaceFinder();
        recommendationService = new RecommendationService();
        mapLinkGenerator = new MapLinkGenerator();
        database = new SimplePlaceDatabase("future_places.txt");
        loadSavedPlaces();

        setTitle("Quick Map Frontend");
        setSize(980, 600);
        setMinimumSize(new java.awt.Dimension(850, 520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(pageColor);

        createTitlePanel();
        createTopPanel();
        createTable();
        createButtonPanel();

        tableHelper.showPlaces(repository.getAllPlaces());
        setLocationRelativeTo(null);
    }

    private void createTitlePanel() {
        topAreaPanel = new JPanel(new BorderLayout());
        topAreaPanel.setBackground(pageColor);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(primaryColor);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel titleLabel = new JLabel("Quick Map");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitleLabel = new JLabel("Search, filter, add, recommend, and open places on map");
        subtitleLabel.setForeground(new Color(220, 235, 245));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(subtitleLabel, BorderLayout.SOUTH);

        topAreaPanel.add(titlePanel, BorderLayout.NORTH);
        add(topAreaPanel, BorderLayout.NORTH);
    }

    private void createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        topPanel.setBackground(pageColor);
        topPanel.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));

        searchField = new JTextField();
        categoryBox = new JComboBox<>();
        categoryBox.addItem("ALL");

        for (PlaceCategory category : PlaceCategory.values()) {
            categoryBox.addItem(category.toString());
        }

        styleInput(searchField);
        styleComboBox(categoryBox);

        topPanel.add(createLabel("Search place by name:"));
        topPanel.add(searchField);
        topPanel.add(createLabel("Select category:"));
        topPanel.add(categoryBox);

        topAreaPanel.add(topPanel, BorderLayout.SOUTH);
    }

    private void createTable() {
        placeTable = new JTable();
        tableHelper = new FrontendTableHelper(placeTable);

        JScrollPane scrollPane = new JScrollPane(placeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 24));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(pageColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(12, 24, 20, 24));

        JButton showAllButton = new JButton("Show All");
        JButton searchButton = new JButton("Search");
        JButton filterButton = new JButton("Filter");
        JButton addButton = new JButton("Add Place");
        JButton nearbyButton = new JButton("Nearby");
        JButton recommendButton = new JButton("Recommend");
        JButton mapButton = new JButton("Open Map");
        JButton saveButton = new JButton("Save");
        JButton readButton = new JButton("Read File");

        styleButton(showAllButton, primaryColor);
        styleButton(searchButton, primaryColor);
        styleButton(filterButton, primaryColor);
        styleButton(addButton, successColor);
        styleButton(nearbyButton, primaryColor);
        styleButton(recommendButton, warningColor);
        styleButton(mapButton, successColor);
        styleButton(saveButton, primaryColor);
        styleButton(readButton, primaryColor);

        showAllButton.addActionListener(event -> showAllPlaces());
        searchButton.addActionListener(event -> searchPlace());
        filterButton.addActionListener(event -> filterByCategory());
        addButton.addActionListener(event -> addNewPlace());
        nearbyButton.addActionListener(event -> showNearbyPlaces());
        recommendButton.addActionListener(event -> showRecommendedPlaces());
        mapButton.addActionListener(event -> openSelectedPlaceOnMap());
        saveButton.addActionListener(event -> savePlaces());
        readButton.addActionListener(event -> showSavedFileData());

        buttonPanel.add(showAllButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(filterButton);
        buttonPanel.add(addButton);
        buttonPanel.add(nearbyButton);
        buttonPanel.add(recommendButton);
        buttonPanel.add(mapButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(50, 60, 70));
        return label;
    }

    private void styleInput(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(240, 36));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 200, 210)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setPreferredSize(new Dimension(190, 36));
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    }

    private void showAllPlaces() {
        tableHelper.showPlaces(repository.getAllPlaces());
    }

    private void searchPlace() {
        String searchName = searchField.getText().trim();
        PlaceCategory selectedCategory = getSelectedCategory();
        List<FutureMapPlace> result = new ArrayList<>();

        for (FutureMapPlace place : repository.getAllPlaces()) {
            boolean nameMatched = searchName.isEmpty()
                    || place.getName().toLowerCase().contains(searchName.toLowerCase());
            boolean categoryMatched = selectedCategory == null
                    || place.getCategory() == selectedCategory;

            if (nameMatched && categoryMatched) {
                result.add(place);
            }
        }

        tableHelper.showPlaces(result);
    }

    private void filterByCategory() {
        PlaceCategory selectedCategory = getSelectedCategory();
        List<FutureMapPlace> result = new ArrayList<>();

        for (FutureMapPlace place : repository.getAllPlaces()) {
            if (selectedCategory == null || place.getCategory() == selectedCategory) {
                result.add(place);
            }
        }

        tableHelper.showPlaces(result);
    }

    private PlaceCategory getSelectedCategory() {
        String selectedText = categoryBox.getSelectedItem().toString();

        if ("ALL".equals(selectedText)) {
            return null;
        }

        return PlaceCategory.valueOf(selectedText);
    }

    private void addNewPlace() {
        try {
            String name = JOptionPane.showInputDialog(this, "Enter place name:");

            if (name == null || name.trim().isEmpty()) {
                showMessage("Place name is required.");
                return;
            }

            PlaceCategory category = (PlaceCategory) JOptionPane.showInputDialog(
                    this,
                    "Select category:",
                    "Add Place",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    PlaceCategory.values(),
                    PlaceCategory.CAFE);

            if (category == null) {
                showMessage("Category is required.");
                return;
            }

            double rating = -1;

            if (category != PlaceCategory.TEMPLE) {
                String ratingText = JOptionPane.showInputDialog(this, "Enter rating:");
                rating = Double.parseDouble(ratingText);
            }

            String popularityText = JOptionPane.showInputDialog(this, "Enter popularity:");
            int popularity = Integer.parseInt(popularityText);

            String latitudeText = JOptionPane.showInputDialog(this, "Enter latitude:");
            double latitude = Double.parseDouble(latitudeText);

            String longitudeText = JOptionPane.showInputDialog(this, "Enter longitude:");
            double longitude = Double.parseDouble(longitudeText);

            FutureMapPlace newPlace = new FutureMapPlace(
                    repository.getNextId(),
                    name.trim(),
                    category,
                    rating,
                    popularity,
                    new LocationPoint(latitude, longitude));

            repository.addPlace(newPlace);
            database.savePlaces(repository.getAllPlaces());
            tableHelper.showPlaces(repository.getAllPlaces());
            showMessage("New place added and saved.");
        } catch (Exception exception) {
            showMessage("Invalid input.");
        }
    }

    private void showNearbyPlaces() {
        LocationPoint userLocation = new LocationPoint(23.2600, 77.4100);
        List<FutureMapPlace> nearbyPlaces = nearbyPlaceFinder.findNearbyPlaces(
                repository.getAllPlaces(),
                userLocation,
                2.0);

        tableHelper.showPlaces(nearbyPlaces);
    }

    private void showRecommendedPlaces() {
        List<FutureMapPlace> recommendedPlaces = recommendationService.getRecommendedPlaces(repository.getAllPlaces());
        tableHelper.showPlaces(recommendedPlaces);
    }

    private void openSelectedPlaceOnMap() {
        FutureMapPlace selectedPlace = findSelectedPlace();

        if (selectedPlace == null) {
            showMessage("Please select a place from table.");
            return;
        }

        String mapLink = mapLinkGenerator.createMapLink(selectedPlace);

        try {
            Desktop.getDesktop().browse(new URI(mapLink));
        } catch (Exception exception) {
            showMessage(mapLink);
        }
    }

    private FutureMapPlace findSelectedPlace() {
        int selectedPlaceId = tableHelper.getPlaceIdFromSelectedRow(placeTable);

        for (FutureMapPlace place : repository.getAllPlaces()) {
            if (place.getId() == selectedPlaceId) {
                return place;
            }
        }

        return null;
    }

    private void savePlaces() {
        database.savePlaces(repository.getAllPlaces());
        showMessage("Places saved in future_places.txt");
    }

    private void loadSavedPlaces() {
        List<FutureMapPlace> savedPlaces = database.loadPlaces();

        if (!savedPlaces.isEmpty()) {
            repository.replacePlaces(savedPlaces);
        }
    }

    private void showSavedFileData() {
        try {
            File file = new File("future_places.txt");
            Scanner scanner = new Scanner(file);
            String fileData = "";

            while (scanner.hasNextLine()) {
                fileData = fileData + scanner.nextLine() + "\n";
            }

            scanner.close();
            showMessage(fileData);
        } catch (IOException exception) {
            showMessage("No saved file found.");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        QuickMapFrontend frontend = new QuickMapFrontend();
        frontend.setVisible(true);
    }
}
