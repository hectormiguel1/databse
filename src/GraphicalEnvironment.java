import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class GraphicalEnvironment extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlLocation = getClass().getResource("mainGraphica.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        Pane root = (Pane) fxmlLoader.load();

        Scene scene = new Scene(root);
    }
}
