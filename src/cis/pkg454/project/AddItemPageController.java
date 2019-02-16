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
import javafx.scene.control.Button;
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
    private HBox nameBox;
    @FXML
    private HBox authorBox;
    @FXML
    private HBox priceBox;
    @FXML
    private HBox isbnBox;
    @FXML
    private Button submitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }
    
}
