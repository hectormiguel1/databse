import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class GraphicalController {

    @FXML
    MenuBar menuBar = new MenuBar();

    @FXML
    GridPane addUserForm = new GridPane();

    @FXML
    Button addUserForm_AddUserButton = new Button();

    @FXML
    Button addUserForm_CancelButton = new Button();

    @FXML
    TextField addUserForm_FirstNameField = new TextField();

    @FXML
    TextField addUserForm_LastNameField = new TextField();

    @FXML
    Menu dbTools = new Menu();

    private boolean dbConnected = false;

    @FXML
    public void initiate() {
        if (!dbConnected)
            dbTools.setDisable(true);
        else
            dbTools.setDisable(false);
    }


    @FXML
    public void setOnAddUser() {
        Stage currentStage = (Stage) menuBar.getScene().getWindow();
        URL location = getClass().getResource("addUserForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            currentStage.setTitle("Add new User");
            currentStage.setScene(new Scene(fxmlLoader.load(), 320, 320));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setOnRemoveUser() {
    }

    @FXML
    public void setOnLogOut() {

    }

    @FXML
    public void setOnConnectDB() {
        if (DabaseConnection.connectDataBase()) {
            JOptionPane.showMessageDialog(null, "Connection Successful", "Connection Successful", JOptionPane.INFORMATION_MESSAGE);
            dbConnected = true;
        } else {
            JOptionPane.showMessageDialog(null, "Connection Not Successful", "Error Connecting to Database", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void setOnCancelClicked() {
        Stage currentStage = (Stage) addUserForm_CancelButton.getScene().getWindow();
        URL location = getClass().getResource("mainGraphical.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        try {
            currentStage.setScene(new Scene(loader.load()));
            currentStage.setTitle("DataBase Management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setOnAddUserClicked() {
        if (addUserForm_FirstNameField.getText().isEmpty() | addUserForm_LastNameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please make sure to enter User's Name and/or User's Last Name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String firstName = addUserForm_FirstNameField.getText();
            String lastName = addUserForm_LastNameField.getText();

            User newUser = new User(firstName, lastName);

            while (!(DabaseConnection.insertIntoDatabase(newUser))) {
                JOptionPane.showMessageDialog(null, "Error Inserting User into Database, trying again with different ID",
                        "Error Inserting User", JOptionPane.ERROR_MESSAGE);
                newUser.newUserID();
            }
            JOptionPane.showMessageDialog(null, "User Added to Database",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            Stage currentStage = (Stage) addUserForm_CancelButton.getScene().getWindow();

            URL location = getClass().getResource("mainGraphical.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            try {
                currentStage.setScene(new Scene(loader.load()));
                currentStage.setTitle("DataBase Management");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
