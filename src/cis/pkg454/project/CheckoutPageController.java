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
import javafx.scene.control.RadioButton;
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
public class CheckoutPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Label titleText;
    @FXML
    private Label subtotalText;
    @FXML
    private Label totalPriceText;
    @FXML
    private TextField nameField;
    @FXML
    private Label shippingAddressField;
    @FXML
    private RadioButton checkRB;
    @FXML
    private RadioButton cardRB;
    @FXML
    private Button continueButton;
    @FXML
    private ToggleGroup paymentMethod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toShoppingCartPage(MouseEvent event) throws Exception {
        // Load shopping cart page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent accountInfoPageParent = FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
        Scene accountInfoPageScene = new Scene(accountInfoPageParent);
        
        window.setScene(accountInfoPageScene);
        window.show();
    }

    @FXML
    private void purchasePrompt(ActionEvent event) {
    }
    
}
