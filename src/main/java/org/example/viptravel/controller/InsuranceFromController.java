package org.example.viptravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.custom.InsuranceBO;
import org.example.viptravel.dto.InsuranceDTO;
import org.example.viptravel.entity.Insurance;


import java.sql.SQLException;
import java.time.LocalDate;

public class InsuranceFromController {

    @FXML
    private ComboBox<String> cmbInsType;

    @FXML
    private DatePicker dateIns;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtInsID;

    @FXML
    private TextField txtRegNo;

    InsuranceBO insuranceBO = (InsuranceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSURANCE);


    public  void initialize(){
        setCmbAvailability();
        clear();
        getCurrentID();


    }



    @FXML
    void DateOnAction(ActionEvent event) {

        String value = String.valueOf(dateIns.getValue());
      //  String format = value.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
      //  String dateString = dateIns.getValue().format(format);

  //txtInsID.setText(format);

    }

    public  void setCmbAvailability(){

        ObservableList<String> list = FXCollections.observableArrayList("FUll","Comperasive");
       cmbInsType.setItems(list);

    }


    @FXML
    void btnAdd(ActionEvent event)  {

        String insID = txtInsID.getText();
        String CompanyName = txtCompanyName.getText();
        String insType = cmbInsType.getValue();
        String date = String.valueOf(dateIns.getValue());
        String RegNo = txtRegNo.getText();

        InsuranceDTO insuranceModle = new InsuranceDTO(insID,CompanyName,insType,date,RegNo);

       try {
           boolean isSaved = insuranceBO.addInsurance(insuranceModle);
           if (isSaved ) {
               new Alert(Alert.AlertType.CONFIRMATION,"wade Hari").show();
               clear();
           }

       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           clear();
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }

    }

    @FXML
    void btnClear(ActionEvent event) {
        clear();
    }

    public void clear(){
        txtInsID.setText("");
        txtCompanyName.setText("");
        cmbInsType.setValue("");
        dateIns.setValue(null);
        txtRegNo.setText("");
        txtInsID.setText("");
    }



    @FXML
    void btnUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {

        String companyName = txtCompanyName.getText();
        String type = cmbInsType.getValue();
        String date = String.valueOf(dateIns.getValue());
        String insuranceID = txtInsID.getText();

        InsuranceDTO IN =new InsuranceDTO(insuranceID,companyName,type,date);
        boolean isUpdate = insuranceBO.updateInsurance(IN);


        System.out.println("insu"+IN);


        if (insuranceID.isEmpty() || companyName.isEmpty() || type == null || date == null) {
            new Alert(Alert.AlertType.ERROR, "Please Fill All the Fields Before Updating!").show();
            return;
        }

        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Sucsessfully!!").show();
            clear();
        }else{
            new Alert(Alert.AlertType.ERROR, "Failed to Update Insurance Details").show();
            clear();

        }
    }


    public String generateNextVehicleID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("IN");
            int idNum = Integer.parseInt(split[1]);
            return "IN" + String.format("%02d", ++idNum);
        }
        return "IN01";

    }

    public  void getCurrentID() {
        try {
            String curentID = insuranceBO.getCurrentInsuranceID();

            String nextInsuID = generateNextVehicleID(curentID);
            txtInsID.setText(nextInsuID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void regSearch(ActionEvent event) throws SQLException, ClassNotFoundException {
        String regNo = txtRegNo.getText();


        Insurance search =insuranceBO.searchInsurance(regNo);

        if(search != null){
            txtInsID.setText(search.getInsuranceID());
            txtCompanyName.setText(search.getCompanyName());
            cmbInsType.setValue(search.getType());
            dateIns.setValue(LocalDate.parse(search.getEndDate()));
            txtRegNo.setText(search.getRegNO());
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Insurance not found!").show();
            clear();
        }

    }
}
