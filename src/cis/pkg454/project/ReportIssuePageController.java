/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class ReportIssuePageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Label titleText;
    @FXML
    private ToggleGroup reportType;
    @FXML
    private Label idText;
    @FXML
    private TextArea reportText;
    @FXML
    private Button submitReportButton;
    @FXML
    private TextField idField;
    @FXML
    private Label idErr;
    @FXML
    private RadioButton postReportRB;
    @FXML
    private RadioButton userReportRB;
    @FXML
    private RadioButton otherReportRB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Select the top most radio button as default
        postReportRB.setSelected(true);
        
        // Set up validation hints for each field
        idField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // If the input is invalid, create and add label notifying user
            if (!idField.getCharacters().toString().matches("[0-9]+") && !otherReportRB.isSelected()) { 
                idErr.setText("* ID is required for user and post reports");
                idErr.setWrapText(true);
            }
            // Else, remove any existing label from the grid
            else {
                idErr.setText("");
            }
        });
    }    

    @FXML
    private void toMainPage(MouseEvent event) throws Exception {
        // Load main page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        
        window.setScene(mainPageScene);
        window.show();
    }

    @FXML
    private void sendReport(ActionEvent event) {
        // If input is invalid, show an error message
        if ((!idErr.getText().equals("") && !otherReportRB.isSelected()) || reportText.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input valid information in all fields");
            alert.showAndWait();
            return;
        }
        
        // Otherwise, send the report to the back end
    }
    
}
