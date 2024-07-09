package org.example.viptravel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class DashboardController {


    @FXML
    private AnchorPane AncMain;

    @FXML
    private Label lblName;
    @FXML
    private Label lblDate;




    public void initialize() {
        setDate();
        setMIDDLE();

    }


    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/CustomerForm.fxml"));
        Parent load = loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);


    }


    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        FXMLLoader logOutloader = new FXMLLoader(getClass().getResource("/org.example.viptravel/LoginForm.fxml"));
        Parent logoutload = logOutloader.load();

        Stage logstage = new Stage();
        logstage.setScene(new Scene(logoutload));
        logstage.show();

        Stage window = (Stage)lblName.getScene().getWindow();
        window.close();

    }


    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {

       FXMLLoader VehicleLoader = new FXMLLoader(getClass().getResource("/org.example.viptravel/ReservationForm.fxml"));
        Parent vehicleLoad = VehicleLoader.load();


        AncMain.getChildren().clear();
        AncMain.getChildren().add(vehicleLoad);

    }


    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {

        FXMLLoader VehicleLoader = new FXMLLoader(getClass().getResource("/org.example.viptravel/VehicleForm.fxml"));
        Parent vehicleLoad = VehicleLoader.load();


        AncMain.getChildren().clear();
        AncMain.getChildren().add(vehicleLoad);


    }





    @FXML
    void btnDashboard(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/DashBoardMiddleForm.fxml"));
        Parent load = loader.load();

     //   DashboardMiddleFormController controller = loader.getController();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);


    }

public  void setMIDDLE() {

   try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/DashBoardMiddleForm.fxml"));
        Parent load =loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}


    public void setUserName(String name) {
        lblName.setText("Welcome"+" "+ name);

    }

    public  void setDate(){
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }


    @FXML
    void btnReturn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/ReturnForm.fxml"));
        Parent load = loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);

    }

    public void btnSettingOnAction(ActionEvent event) throws IOException {

       FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/EmployeeDdetailForm.fxml"));
        Parent load = loader.load();

        AncMain.getChildren().clear();
        AncMain.getChildren().add(load);
    }
}


