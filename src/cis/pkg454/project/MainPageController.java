/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;

import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OliviaFlynn
 */
public class MainPageController implements Initializable {

    @FXML
    private Label greetingText;
    @FXML
    private Button accountInfoBtn;
    @FXML
    private Button shoppingCartBtn;
    @FXML
    private Button sellBtn;
    @FXML
    private Button reportBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchBtn;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> authorCol;
    @FXML
    private TableColumn<?, ?> isbnCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private Button reviewButton;
    @FXML
    private Button adminManagementButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toAccountInfoPage(ActionEvent event) throws Exception {
        // Load account info page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent accountInfoPageParent = FXMLLoader.load(getClass().getResource("AccountInfoPage.fxml"));
        Scene accountInfoPageScene = new Scene(accountInfoPageParent);
        
        window.setScene(accountInfoPageScene);
        window.show();
    }

    @FXML
    private void toShoppingCartPage(ActionEvent event) throws Exception {
        // Load account info page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent shoppingCartPageParent = FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
        Scene shoppingCartPageScene = new Scene(shoppingCartPageParent);
        
        window.setScene(shoppingCartPageScene);
        window.show();
    }

    @FXML
    private void toSellPage(ActionEvent event) throws Exception{
        // Load sell page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent sellPageParent = FXMLLoader.load(getClass().getResource("SellPage.fxml"));
        Scene sellPageScene = new Scene(sellPageParent);
        
        window.setScene(sellPageScene);
        window.show();
    }

    @FXML
    private void toReportPage(ActionEvent event) throws Exception {
        // Load report issue page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent reportIssuePageParent = FXMLLoader.load(getClass().getResource("ReportIssuePage.fxml"));
        Scene reportIssuePageScene = new Scene(reportIssuePageParent);
        
        window.setScene(reportIssuePageScene);
        window.show();
    }

    @FXML
    private void logOutPrompt(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        String s = "Log out?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            // logic for logout
            
            // Load report issue page fxml file and set to scene in order to navigate
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            window.setScene(loginPageScene);
            window.show();
        }
    }

    @FXML
    private void toReviewPage(ActionEvent event) throws Exception {
        // Load report issue page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent reviewReportsPageParent = FXMLLoader.load(getClass().getResource("ReviewReportsPage.fxml"));
        Scene reviewReportsPageScene = new Scene(reviewReportsPageParent);
        
        window.setScene(reviewReportsPageScene);
        window.show();
    }

    @FXML
    private void toAdminManagement(ActionEvent event) throws Exception {
        // Load admin management tools page fxml file and set to scene in order to navigate
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Parent adminManagementPageParent = FXMLLoader.load(getClass().getResource("AdminManagementPage.fxml"));
        Scene adminManagementPageScene = new Scene(adminManagementPageParent);
        
        window.setScene(adminManagementPageScene);
        window.show();
    }
    
}
