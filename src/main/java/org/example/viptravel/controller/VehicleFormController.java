package org.example.viptravel.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.custom.VehicleBO;
import org.example.viptravel.dto.VehicleDTO;
import org.example.viptravel.entity.Vehicle;
import org.example.viptravel.view.tdm.VehicleTM;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class VehicleFormController {



    @FXML
    private TableColumn<?,?> colAvailability;

    @FXML
    private TableColumn<?,?> colCost;

    @FXML
    private TableColumn<?,?> colFuelType;

    @FXML
    private TableColumn<?,?> colVehicleName;

    @FXML
    private TableColumn<?,?> colVehicleType;

    @FXML
    private TableColumn<?,?> colYear;

    @FXML
    private TableColumn<?,?> colregNo;

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    private JFXComboBox<String> cmbAvailability;

    @FXML
    private JFXComboBox<String> cmbFuelType;

    @FXML
    private JFXComboBox<String> cmbVehicleType;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRegNo;

    @FXML
    private TextField txtVehicleID;

    @FXML
    private TextField txtVehicleName;

    @FXML
    private TextField txtYear;

    @FXML
    private AnchorPane AncVehicle;

     VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);



    public void initialize()  {

        setCmbVehicleType();
        setCmbAvailability();
        setCmbFuelType();
        clearFields();
        getCurrentID();
       setCellValues();
       loadAllvehicles();
       showSelectedVehicleDetails();

    }


    private void showSelectedVehicleDetails() {
        VehicleTM selectedVehicle = tblVehicle.getSelectionModel().getSelectedItem();
        tblVehicle.setOnMouseClicked(event -> showSelectedVehicleDetails());
        if (selectedVehicle != null) {
            txtVehicleID.setText(selectedVehicle.getVehicleID());
            txtRegNo.setText(selectedVehicle.getRegNo());
            txtYear.setText(selectedVehicle.getYear());
            txtVehicleName.setText(selectedVehicle.getVehicleName());
            cmbFuelType.setValue(selectedVehicle.getFuelType());
            cmbVehicleType.setValue(selectedVehicle.getVehicleType());
            txtPrice.setText(String.valueOf(selectedVehicle.getCost()));
            cmbAvailability.setValue(selectedVehicle.getAvailability());

        }
    }



   private  void setCellValues(){

        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        colVehicleName.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colregNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
    }


    private void loadAllvehicles() {
        ObservableList<VehicleTM> obList = FXCollections.observableArrayList();

        try {
            List<VehicleDTO> VehicleList = vehicleBO.getAllVehicle();
           for (VehicleDTO vehiclemodle : VehicleList) {

             VehicleTM tm = new VehicleTM(
                     vehiclemodle.getVehicleID(),
                     vehiclemodle.getRegNo(),
                     vehiclemodle.getYear(),
                     vehiclemodle.getVehicleName(),
                     vehiclemodle.getFuelType(),
                     vehiclemodle.getVehicleType(),
                     vehiclemodle.getCost(),
                     vehiclemodle.getAvailability()
             );

                obList.add(tm);
            }
            //System.out.println(obList);
           tblVehicle.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

     @FXML
    public void txtSearch(ActionEvent event) throws SQLException {
         String vehicleID = txtVehicleID.getText();

         try {
             Vehicle vehicle = vehicleBO.searchVehicle(vehicleID);

             if (vehicle != null) {
                 txtRegNo.setText(vehicle.getRegNo());
                 txtYear.setText(vehicle.getYear());
                 txtVehicleName.setText(vehicle.getVehicleName());
                 cmbFuelType.setValue(vehicle.getFuelType());
                 cmbVehicleType.setValue(vehicle.getVehicleType());
                 txtPrice.setText(String.valueOf(vehicle.getCost()));
                 cmbAvailability.setValue(vehicle.getAvailability());
             } else {
                 new Alert(Alert.AlertType.INFORMATION, "Vehicle not found!").show();
                 clearFields();
             }
         } catch (SQLException e) {
             new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
     }

    @FXML
    void btnClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtVehicleID.setText("");
        txtRegNo.setText("");
        txtYear.setText("");
        txtVehicleName.setText("");
        cmbFuelType.setValue("");
        cmbVehicleType.setValue("");
        txtPrice.setText("");
       cmbAvailability.setValue("");
    }

    @FXML
    void btnDelete(ActionEvent event) {

        String vehicleID = txtVehicleID.getText();
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this vehicle?").showAndWait();
            try {
                if (vehicleBO.deleteVehicle(vehicleID)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Deleted Successfully").show();
                    clearFields();
                    loadAllvehicles();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    void btnSave(ActionEvent event) {

        String vehicleID = txtVehicleID.getText();
        String RegNo = txtRegNo.getText();
        String year = txtYear.getText();
        String vehicleName = txtVehicleName.getText();
        String fuelType = cmbFuelType.getValue();
        String vehicleType = cmbVehicleType.getValue();
        double cost = Double.parseDouble(txtPrice.getText());
        String availability = cmbAvailability.getValue();


        VehicleDTO vehicle = new VehicleDTO(vehicleID,RegNo,year,vehicleName,fuelType,vehicleType,cost,availability);

        try {
            boolean isSaved = vehicleBO.addVehicle(vehicle);
            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Registered!").show();
                clearFields();
                loadAllvehicles();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {

        String vehicleID = txtVehicleID.getText();
        double cost = Double.parseDouble(txtPrice.getText());
        String availability = cmbAvailability.getValue();

        VehicleDTO vehicleModle = new VehicleDTO(vehicleID,cost,availability);
        System.out.println("Vehicle"+vehicleModle);

        boolean isUpdated =vehicleBO.updateVehicle(vehicleModle);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
            clearFields();
            loadAllvehicles();
        }else {
            new Alert(Alert.AlertType.ERROR, "Can't Update").show();
        }

    }

    public String generateNextVehicleID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("V");
            int idNum = Integer.parseInt(split[1]);
            return "V" + String.format("%03d", ++idNum);
        }
        return "V001";

    }

   public  void getCurrentID(){
       try {
           String curentID = vehicleBO.getCurrentVehicleID();

          String nextvehicleID = generateNextVehicleID(curentID);
          txtVehicleID.setText(nextvehicleID);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }



    public void setCmbVehicleType(){

        ObservableList<String> list = FXCollections.observableArrayList("Car", "Van", "Suv", "Limo");
        cmbVehicleType.setItems(list);
    }


    public  void  setCmbFuelType(){

        ObservableList<String> list = FXCollections.observableArrayList("Petrol", "Diesel", "Electric", "Hybrid");
        cmbFuelType.setItems(list);

    }


    public  void setCmbAvailability(){

        ObservableList<String> list = FXCollections.observableArrayList("Available", "Not Available");
        cmbAvailability.setItems(list);

    }


    @FXML
    void btnVehicleINS(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/InsuranceFrom.fxml"));
        Parent load = loader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();

       /* AncVehicle.getChildren().clear();
        AncVehicle.getChildren().add(load);*/

    }


}
