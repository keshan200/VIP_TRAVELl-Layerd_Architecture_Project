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
import org.example.viptravel.bo.custom.CustomerBO;
import org.example.viptravel.dto.CustomerDTO;
import org.example.viptravel.view.tdm.CustomerTM;


import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {


    @FXML
    private TableColumn<?,?> ColAdrs;

    @FXML
    private TableColumn<?,?> ColTelno;

    @FXML
    private TableColumn<?,?> colCusID;

    @FXML
    private TableColumn<?,?> colNIC;

    @FXML
    private TableColumn<?,?> colName;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddrs;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTelNO;

  CustomerBO cusBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Customer);


    public  void initialize(){
       clearFields();
       setcellValues();
       loadAllCustomers();
       ShowSelectedCustomerDetails();
       getCurrentID();

   }




private void ShowSelectedCustomerDetails(){

   CustomerTM details = tblCustomer.getSelectionModel().getSelectedItem();
    tblCustomer.setOnMouseClicked(event -> ShowSelectedCustomerDetails());
    if (details != null) {
        txtCustomerID.setText(details .getCustomerID());
        txtNIC.setText(details .getNIC());
        txtName.setText(details .getName());
        txtTelNO.setText(String.valueOf(details .getTelNO()));
        txtAddrs.setText(details .getAddress());
    }
}


   private  void loadAllCustomers(){
      // ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
       tblCustomer.getItems().clear();
       try {
           List<CustomerDTO> cusList = cusBO.getAllCustomers();
           for (CustomerDTO cusModle : cusList){

               tblCustomer.getItems().add(new CustomerTM(cusModle.getCustomerID(),cusModle.getNIC(),cusModle.getName(),cusModle.getTelNO(),cusModle.getAddress()));

             //  obList.add(TM);
              // tblCustomer.setItems(obList);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }


    public String generateNextCustomerID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("C");
            int idNum = Integer.parseInt(split[1]);
            return "C" + String.format("%03d", ++idNum);
        }
        return "C001";

    }

    
    public  void getCurrentID(){
        try {
         //   String curentID = CustomerRepo.getCurrentId();
                String  curentID = cusBO.getCurrentCustomerID();
            String nextCusID= generateNextCustomerID(curentID);
            txtCustomerID.setText(nextCusID);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




private  void setcellValues(){

    colCusID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    ColTelno.setCellValueFactory(new PropertyValueFactory<>("telNO"));
    ColAdrs.setCellValueFactory(new PropertyValueFactory<>("address"));
}



    @FXML
    void btnAddd(ActionEvent event) {

        String customerID = txtCustomerID.getText();
        String NIC = txtNIC.getText();
        String name = txtName.getText();
        int telNo = Integer.parseInt(txtTelNO.getText());
        String adrs = txtAddrs.getText();

        CustomerDTO customer = new CustomerDTO(customerID, NIC, name, telNo, adrs);
        try {
                boolean isSaved = cusBO.addCustomers(customer);
                System.out.println(customer);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!!").show();
                    clearFields();
                    loadAllCustomers();
                }

            } catch(SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





    @FXML
    void btnClear(ActionEvent event) {
       clearFields();



    }


    public  void clearFields(){
        txtCustomerID.setText("");
        txtNIC.setText("");
        txtName.setText("");
        txtTelNO.setText("");
        txtAddrs.setText("");

    }


    @FXML
    void btnDelete(ActionEvent event) {

        String cuID = txtCustomerID.getText();
        if (!cuID.isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want Delete ? ").showAndWait();
        }
        try {
                if (cusBO.deleteCustomers(cuID)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted ").show();
                    clearFields();
                    loadAllCustomers();
         }
            } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {

        String NIC = txtNIC.getText();
        String name = txtName.getText();
        int telNO = Integer.parseInt(txtTelNO.getText());
        String adrs = txtAddrs.getText();

        CustomerDTO cusmodle = new CustomerDTO (NIC,name,telNO,adrs);


            boolean isUpdate =cusBO.updateCustomers(cusmodle);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Sucsesssfully!").show();
                clearFields();
                loadAllCustomers();
            } else {
                new Alert(Alert.AlertType.ERROR, "Can't Update").show();
            }



        }






   // @FXML
   @FXML
   void NicOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

     /*  String nic = txtNIC.getText();

       CustomerDTO cusmodel = cusBO.searchCustomer(nic);

        if (cusmodel != null) {
            txtCustomerID.setText(cusmodel.getCustomerID());
            txtName.setText(cusmodel.getName());
            txtTelNO.setText(String.valueOf(cusmodel.getTelNO()));
            txtAddrs.setText(cusmodel.getAddress());

        }else{
            new Alert(Alert.AlertType.INFORMATION, "Customer not found!").show();
            clearFields();

        }*/
    }



    public void telNoAction(javafx.scene.input.KeyEvent keyEvent) {

    }




}
