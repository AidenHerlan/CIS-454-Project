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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class ShoppingCartPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Button checkOutButton;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> authorCol;
    @FXML
    private TableColumn<?, ?> isbnCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private TableColumn<?, ?> deleteCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toMainPage(MouseEvent event) throws Exception{
        // Load main page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent accountInfoPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene accountInfoPageScene = new Scene(accountInfoPageParent);
        
        window.setScene(accountInfoPageScene);
        window.show();
    }

    @FXML
    private void toCheckoutPage(ActionEvent event) throws Exception {
        // Load checkout page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent accountInfoPageParent = FXMLLoader.load(getClass().getResource("CheckoutPage.fxml"));
        Scene accountInfoPageScene = new Scene(accountInfoPageParent);
        
        window.setScene(accountInfoPageScene);
        window.show();
    }
    
}
