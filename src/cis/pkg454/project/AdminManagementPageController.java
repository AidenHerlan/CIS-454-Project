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
public class AdminManagementPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private Button modifyPostButton;
    @FXML
    private Button deletePostButton;

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
        
        Parent MainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene MainPageScene = new Scene(MainPageParent);
        
        window.setScene(MainPageScene);
        window.show();
    }

    @FXML
    private void toModifyPage(ActionEvent event) throws Exception {
        // Load modify page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent ModifyPostPageParent = FXMLLoader.load(getClass().getResource("ModifyPostPage.fxml"));
        Scene ModifyPostPageScene = new Scene(ModifyPostPageParent);
        
        window.setScene(ModifyPostPageScene);
        window.show();
    }

    @FXML
    private void toDeletePage(ActionEvent event) throws Exception {
        // Load delete page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent DeletePostPageParent = FXMLLoader.load(getClass().getResource("DeletePostPage.fxml"));
        Scene DeletePostPageScene = new Scene(DeletePostPageParent);
        
        window.setScene(DeletePostPageScene);
        window.show();
    }
    
}
