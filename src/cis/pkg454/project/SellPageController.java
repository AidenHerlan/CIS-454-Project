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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class SellPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Button addItemButton;
    @FXML
    private Button modifyItemButton;
    @FXML
    private Button deleteItemButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void toAddForm(ActionEvent event)  throws Exception {
        // Load sell page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent addItemPageParent = FXMLLoader.load(getClass().getResource("AddItemPage.fxml"));
        Scene addItemPageScene = new Scene(addItemPageParent);
        
        window.setScene(addItemPageScene);
        window.show();
    }

    @FXML
    private void toModifyPage(ActionEvent event) throws Exception {
        // Load sell page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent modifyItemPageParent = FXMLLoader.load(getClass().getResource("ModifyItemPage.fxml"));
        Scene modifyItemPageScene = new Scene(modifyItemPageParent);
        
        window.setScene(modifyItemPageScene);
        window.show();
    }

    @FXML
    private void toDeletePage(ActionEvent event) throws Exception {
        // Load sell page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent deleteItemPageParent = FXMLLoader.load(getClass().getResource("DeleteItemPage.fxml"));
        Scene deleteItemPageScene = new Scene(deleteItemPageParent);
        
        window.setScene(deleteItemPageScene);
        window.show();
    }
    
}
