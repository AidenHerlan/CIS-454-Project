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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class AddItemPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Button submitButton;
    @FXML
    private TextField nameField;
    @FXML
    private Label priceErr;
    @FXML
    private Label isbnErr;
    @FXML
    private TextField authorField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField isbnField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up validation hints for each field
        priceField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // If the input is not in the right form, add label notifying user
            if (!priceField.getCharacters().toString().matches("[0-9].[0-9][0-9]")) { 
                priceErr.setText("* Provide a price of the form d.cc ex. 1.50");
                priceErr.setWrapText(true);
            }
            // Else, remove any existing label from the grid
            else {
                priceErr.setText("");
            }
        });
        isbnField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            // If the input is not in the right form, add label notifying user
            if (isbnField.getCharacters().length() != 13) { 
                isbnErr.setText("* Must be 13 numbers");
                isbnErr.setWrapText(true);
            }
            // Else, remove any existing label from the grid
            else {
                isbnErr.setText("");
            }
        });
    }    

    @FXML
    private void toSellPage(MouseEvent event) throws Exception {
        // Load sell page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent SellPageParent = FXMLLoader.load(getClass().getResource("SellPage.fxml"));
        Scene SellPageScene = new Scene(SellPageParent);
        
        window.setScene(SellPageScene);
        window.show();
    }

    @FXML
    private void addItem(ActionEvent event) {
        // Make sure that all fields have valid input, if not, show an error message
        if (isbnErr.getText().length() != 0 || 
                priceErr.getText().length() != 0 ||
                nameField.getCharacters().length() == 0 ||
                authorField.getCharacters().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input valid information in all fields");
            alert.showAndWait();
            return;
        }
        
        // Get the info and send it to backend
        String textbookName = nameField.getCharacters().toString();
        String author = authorField.getCharacters().toString();
        double price = Double.parseDouble(priceField.getCharacters().toString());
        String isbn = isbnField.getCharacters().toString();
        int id = 0;  // get from backend
        String seller = CIS454Project.currentUser.getName();
        
        String query = "insert into Textbook values ("+textbookName+", "+price+", "+author+", "+isbn+id+seller+")";
        
    }
    
}
