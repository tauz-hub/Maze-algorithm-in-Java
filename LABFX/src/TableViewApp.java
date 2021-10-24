import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TableViewApp extends Application{
    public static void main(String[] args) {
        launch(TableViewApp.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(
//                getClass().getResource("tableview.fxml"));
    	GridPane root = new GridPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("TableView App");
        stage.show();
    }
}