package org.example.viptravel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.custom.LoginBO;
import org.example.viptravel.entity.Login;


import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void btnLogin(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String userID = txtUser.getText();
        String password = txtPassword.getText();

        if (userID.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter User ID and Password!").show();
            return;
        }

        checkCredential(userID, password);
    }

    public void checkCredential(String userID, String password) throws SQLException, IOException, ClassNotFoundException {
        Login login = loginBO.checkCredential(userID, password);

        if (login != null) {
          //  new Alert(Alert.AlertType.CONFIRMATION, "Login successful!").show();
            String employeeName = loginBO.navigateDashboard(userID);
            navigateDashboard(employeeName);
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry! User ID or Password is incorrect!").show();
        }
    }

    public void navigateDashboard(String employeeName) throws IOException {
        FXMLLoader dashLoader = new FXMLLoader(getClass().getResource("/org.example.viptravel/Dashboard.fxml"));
        Parent load = dashLoader.load();

        DashboardController controller = dashLoader.getController();
        controller.setUserName(employeeName); // Set the user name in the dashboard

        Stage dashboardStage = new Stage();
        dashboardStage.setScene(new Scene(load));
        dashboardStage.show();

        // Close the login window
        Stage loginStage = (Stage) txtUser.getScene().getWindow();
        loginStage.close();
    }

    public void btnRegister(ActionEvent event) throws IOException {
        FXMLLoader regLoader = new FXMLLoader(getClass().getResource("/org.example.viptravel/RegFormMain.fxml"));
        Parent regLoad = regLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(regLoad));
        stage.setTitle("Register Form");
        stage.show();

        // Close the current window
        Stage window = (Stage) txtUser.getScene().getWindow();
        window.close();
    }
}
