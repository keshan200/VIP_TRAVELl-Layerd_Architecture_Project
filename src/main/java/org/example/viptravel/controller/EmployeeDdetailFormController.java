package org.example.viptravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.custom.EmpDtlsBO;
import org.example.viptravel.dto.EmpDtlsDTO;
import org.example.viptravel.entity.EmpDtls;
import org.example.viptravel.view.tdm.EmpTm;


import java.sql.SQLException;
import java.util.List;

public class EmployeeDdetailFormController {

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtuserID;

    @FXML
    private TextField tp;


    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtemail;



    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableColumn<?, ?> coltel;



    @FXML
    private TableView<EmpTm> tblEMp;


    EmpDtlsBO empDtlsBO = (EmpDtlsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER);


    public void initialize() {

      loadAllemp();
      setCellValues();
    }



    @FXML
    void NicOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            String NIC = txtNIC.getText();


            EmpDtls emp = empDtlsBO.searchEmpDtls(NIC);

            if (emp != null) {
                txtNIC.setText(emp.getNIC());
                txtName.setText(emp.getName());
                txtAdress.setText(emp.getAddress());
                tp.setText(String.valueOf(emp.getTp()));
                txtuserID.setText(emp.getUserID());
                txtPassword.setText(String.valueOf(emp.getPassword()));
                txtemail.setText(emp.getEmail());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
                clearFields();
            }
        }

    private void clearFields() {
        txtNIC.setText("");
        txtName.setText("");
        txtAdress.setText("");
        tp.setText("");
        txtuserID.setText("");
        txtPassword.setText("");
        txtemail.setText("");
    }


    private  void setCellValues(){

        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tp"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }


    private void loadAllemp() {
        ObservableList<EmpTm> obList = FXCollections.observableArrayList();

        try {
            List<EmpDtlsDTO> emplist =empDtlsBO.getAllEmpDtls();
            for (EmpDtlsDTO emp : emplist) {

                EmpTm tm = new EmpTm(
                        emp.getNIC(),
                        emp.getName(),
                        emp.getAddress(),
                        emp.getTp(),
                        emp.getUserID(),
                        emp.getEmail()

                );

                obList.add(tm);
            }
            //System.out.println(obList);
            tblEMp.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnRegister(ActionEvent event) throws SQLException {


        String NIC = txtNIC.getText();
        String name = txtName.getText();
        String address = txtAdress.getText();
        int Tp = Integer.parseInt(tp.getText());
        String userID = txtuserID.getText();
        String pass = txtPassword.getText();
        String emai = txtemail.getText();


        EmpDtlsDTO empDtlsModle = new EmpDtlsDTO(NIC,name,address,Tp,userID,pass,emai);

        try {
            boolean isSaved =empDtlsBO.addEmpDtls(empDtlsModle);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();loadAllemp();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {

        String Nic =txtNIC.getText();
        String adrs = txtAdress.getText();
        int Tp = Integer.parseInt(tp.getText());
        String uID = txtuserID.getText();
        String pas = txtPassword.getText();
        String email = txtemail.getText();

        EmpDtlsDTO empDtlsModle = new EmpDtlsDTO(Nic,adrs,Tp,uID,pas,email);

        boolean isUpdated = empDtlsBO.updateEmpDtls(empDtlsModle);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
            clearFields();
            loadAllemp();
        }else {
            new Alert(Alert.AlertType.ERROR, "Can't Update").show();
        }

    }

    @FXML
    void btnDelete(ActionEvent event) {
        String Nic = txtNIC.getText();
        if(Nic.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Enter NIC Before Delete!!").show();
            return;
        }

        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Employee?").showAndWait();
        try {
            if (empDtlsBO.deleteEmpDtls(Nic)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted Successfully").show();
                clearFields();
               loadAllemp();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }





}



