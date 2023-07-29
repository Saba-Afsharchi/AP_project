import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game_Table extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TableView<Component> tableView = new TableView<>();

        TableColumn<Component, String> componentColumn = new TableColumn<>("Component");
        componentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Component, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableView.getColumns().addAll(componentColumn, pointsColumn);


        ObservableList<Component> components = FXCollections.observableArrayList(
                new Component("saba afsharchi", 10),
                new Component("neekoo afsharchi", 5)

        );


        tableView.setItems(components);

        VBox root = new VBox(tableView);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Components Points");
        primaryStage.show();
    }

    public static class Component {
        private String name;
        private int points;

        public Component(String name, int points) {
            this.name = name ;
            this.points = points;
        }

        public String getName() {
            return name ;
        }

        public int getPoints() {

            return points;
        }
    }
}
