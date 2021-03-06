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
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class DeletePostPageController implements Initializable {

    @FXML
    private ImageView backArrow;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> authorCol;
    @FXML
    private TableColumn<?, ?> isbnCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deleteItems(ActionEvent event) {
    }

    @FXML
    private void toManagePage(MouseEvent event) throws Exception {
        // Load admin management tools page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent adminManagementPageParent = FXMLLoader.load(getClass().getResource("AdminManagementPage.fxml"));
        Scene adminManagementPageScene = new Scene(adminManagementPageParent);
        
        window.setScene(adminManagementPageScene);
        window.show();
    }
    
}
