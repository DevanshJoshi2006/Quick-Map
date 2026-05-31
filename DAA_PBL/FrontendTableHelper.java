import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class FrontendTableHelper {
    private DefaultTableModel tableModel;

    public FrontendTableHelper(JTable table) {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Popularity");
        tableModel.addColumn("Latitude");
        tableModel.addColumn("Longitude");
        table.setModel(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(35, 78, 112));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(209, 231, 221));
        table.setGridColor(new Color(220, 225, 230));
    }

    public void showPlaces(List<FutureMapPlace> places) {
        tableModel.setRowCount(0);

        for (FutureMapPlace place : places) {
            String ratingText = place.getCategory() == PlaceCategory.TEMPLE
                    ? "Not needed"
                    : String.valueOf(place.getRating());

            tableModel.addRow(new Object[]{
                    place.getId(),
                    place.getName(),
                    place.getCategory(),
                    ratingText,
                    place.getPopularity(),
                    place.getLocation().getLatitude(),
                    place.getLocation().getLongitude()
            });
        }
    }

    public int getPlaceIdFromSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            return -1;
        }

        return Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
    }
}
