package org.example.viptravel.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.custom.ReservationBO;
import org.example.viptravel.bo.custom.ReturnBO;
import org.example.viptravel.dto.CustomerDTO;
import org.example.viptravel.dto.ReturnDTO;
import org.example.viptravel.dto.ReturnDetailsDTO;
import org.example.viptravel.dto.RetutnFormDTO;
import org.example.viptravel.entity.Customer;
import org.example.viptravel.entity.Return;
import org.example.viptravel.entity.ReturnDetails;
import org.example.viptravel.entity.RetutnForm;
import org.example.viptravel.view.tdm.ReturnTM;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class ReturnFormController {

    @FXML
    private DatePicker ReturnDate;

    @FXML
    private JFXComboBox<String> cmbDamge;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private TableColumn<?, ?> colDamage;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colRegNo;

    @FXML
    private TableColumn<?, ?> colRetDate;

    @FXML
    private TableColumn<?, ?> colReturnID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colac;

    @FXML
    private TableView<ReturnTM> tblReturn;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRegNO;

    @FXML
    private TextField txtReturn;

    @FXML
    private TextArea txtdesc;

    @FXML
    private TextField txtvehicle;

    ObservableList<ReturnTM>retList = FXCollections.observableArrayList();

 ReturnBO returnBO = (ReturnBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ReturnDETAILS);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ReservationDETAILS);




    public  void initialize(){
        setCmbStatus();
        setCmbDamage();
        getCurrentID();
        setCellVFactory();
        loadAllReservations();
        clearFields();




    }
    @FXML
    void clearFields() {

        txtNIC.setText("");
        cmbDamge.setValue("");
        cmbStatus.setValue("");
        ReturnDate.setValue(null);
        txtName.setText("");
        txtRegNO.setText("");
        txtdesc.setText("");
        txtvehicle.setText("");
    }


    @FXML
    void nicOnAction(ActionEvent event) {

        String NIC = txtNIC.getText();
        try {
            CustomerDTO customerNameByNIC = reservationBO.getCustomerNameByNIC(NIC);
            if (customerNameByNIC != null) {
                txtName.setText(customerNameByNIC.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            String nic = txtNIC.getText();
            if (!nic.isEmpty()) {
                loadReturnsForCustomer(nic);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



    private void loadReturnsForCustomer(String nic) throws SQLException, ClassNotFoundException {
        List<ReturnTM> returnList = new ArrayList<>();
        List<ReturnDetailsDTO> returnDetailsList = returnBO.getReturnsToCartByNIC(nic);

        for (ReturnDetailsDTO returnDetails : returnDetailsList) {
            ReturnTM returnTM = new ReturnTM(
                    returnDetails.getReturnID(),
                    returnDetails.getStatus(),
                    returnDetails.getReturnDate(),
                    returnDetails.getNIC(),
                    returnDetails.getRegNo(),
                    returnDetails.getDamages(),
                    returnDetails.getDesc(),
                    new JFXButton("❌")
            );
            returnList.add(returnTM);
        }
        tblReturn.setItems(FXCollections.observableArrayList(returnList));
    }




    private void setCellVFactory() {
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("returnID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRetDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colDamage.setCellValueFactory(new PropertyValueFactory<>("damages"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colac.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }





    @FXML
    void btnAdd(ActionEvent event) {

        String retID = txtReturn.getText();
        String status = cmbStatus.getValue();
        LocalDate date = ReturnDate.getValue();
        String nic = txtNIC.getText();
        String damage = cmbDamge.getValue();
        String desc = txtdesc.getText();

        if (status == null || date == null || nic.isEmpty() || damage == null || desc.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the required fields.").show();
            return;
        }

        List<ReturnDetails>RetunList = new ArrayList<>();

       /* for (ReturnTM tm : tblReturn.getItems()) {
            ReturnDetailsModle R = new ReturnDetailsModle(

                    tm.getReturnID(),
                    tm.getRegNo()

            );*/
        for (ReturnTM tm : tblReturn.getItems()) {
            ReturnDetails r = new ReturnDetails(tm.getReturnID(), tm.getRegNo());
            RetunList.add(r);
        }

           // RetunList.add(R);



       Return returnModle = new Return(retID,status,date,nic,damage,desc);
        RetutnForm retutnFormModle = new RetutnForm(returnModle, RetunList);



          try {
             String NIC = txtNIC.getText();
              boolean isOk = returnBO.SetReturn(retutnFormModle);
              if (isOk) {
                  retList.clear();

                  new Alert(Alert.AlertType.CONFIRMATION,"Return Sucsess").show();
                  loadAllReservations();
                  getCurrentID();
                  clearFields();
                 txtNIC.setDisable(false);


              }else {
                  new Alert(Alert.AlertType.ERROR,"Can 't Return ").show();
                  System.out.println(retList);
                  clearFields();
                  loadAllReservations();
                  txtNIC.setDisable(false);

              }

          } catch (SQLException e) {
              new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
          }

    }

    @FXML
    void btnConfirm(ActionEvent event) {

        String returnID = txtReturn.getText();
        String status = cmbStatus.getValue();
        LocalDate date = ReturnDate.getValue();
        String NIC = txtNIC.getText();
        String regNo = txtRegNO.getText();
        String damages = cmbDamge.getValue();
        String desc = txtdesc.getText();



        JFXButton remove = new JFXButton("❌");
        remove.setCursor(Cursor.HAND);
        remove.setStyle("-fx-text-fill: red;");
        remove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                int selectedIndex = tblReturn.getSelectionModel().getSelectedIndex();
                retList.remove(selectedIndex);

                if (retList.isEmpty()) {
                    txtNIC.setDisable(false);
                }

            }
        });



      if (!retList.isEmpty()) {
          for(int i =0;i< retList.size();i++){
              if (regNo.equals(colRegNo.getCellData(i))) {
                  ReturnTM tm = retList.get(i);
                  tblReturn.refresh();
                  return;
              }

          }
        }



        txtNIC.setDisable(true);
        ReturnTM tm = new ReturnTM(returnID,status,date,NIC,regNo,damages,desc,remove);
        System.out.println(tm);
        retList.add(tm);
        tblReturn.setItems(retList);

    }





    @FXML
    void btnPayment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example.viptravel/PaymentForm.fxml"));
        Parent load = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();

    }

    @FXML
    void setReturnDate(ActionEvent event) {
        String value = String.valueOf(ReturnDate.getValue());

    }



    public  void  setCmbDamage(){

        ObservableList<String> list = FXCollections.observableArrayList("Yes","No","Hard Damage");
        cmbDamge.setItems(list);

    }



    public  void  setCmbStatus(){

        ObservableList<String> list = FXCollections.observableArrayList("Complete","InComplete");
        cmbStatus.setItems(list);

    }



    public String generateNextVehicleID(String curentID){
        if (curentID != null) {
            String[] split = curentID.split("RT");
            int idNum = Integer.parseInt(split[1]);
            return "RT" + String.format("%02d", ++idNum);
        }
        return "RT01";
    }

    public  void getCurrentID(){
        try {
            String curentID = returnBO.getCurrentId();
            String nextRetunrID = generateNextVehicleID(curentID);
            txtReturn.setText(nextRetunrID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void loadAllReservations() {
        List<ReturnTM> RetList = FXCollections.observableArrayList();

        try {
            List<ReturnDetailsDTO> AllReturns = returnBO.getAllReturns();


            for (ReturnDetailsDTO ret : AllReturns) {
                String ReturnID = ret.getReturnID();
                String Status = ret.getStatus();
                LocalDate rDate = ret.getReturnDate();
                String NIC = ret.getNIC();
                String regNo = ret.getRegNo();
                String damages = ret.getDamages();
                String desc = ret.getDesc();


                ReturnTM rett = new ReturnTM(ReturnID,Status,rDate,NIC,regNo,damages,desc,new JFXButton("❌"));
                RetList.add(rett);
            }

            tblReturn.setItems(FXCollections.observableArrayList(RetList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReportPrint(ActionEvent event) {



    }

}



