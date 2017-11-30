import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class GraphicalEnvironment extends Application {


    private Pane root;

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlLocation = getClass().getResource("mainGraphical.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        root = fxmlLoader.load();
        stage.setTitle("Database Management");
        stage.setResizable(true);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void lunch() {
        try {
            start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
