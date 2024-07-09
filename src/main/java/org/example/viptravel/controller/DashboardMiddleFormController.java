package org.example.viptravel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.custom.DashBoardMiddleBO;
import org.example.viptravel.dao.custom.DashBoardMidleDAO;
import org.example.viptravel.dto.DashBoardDTO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.DashBoard;
import org.example.viptravel.view.tdm.DashboardTableTM;


import java.sql.SQLException;
import java.util.List;

public class DashboardMiddleFormController {

    @FXML
    private TableColumn<DashboardTableTM, Double> colPrice;

    @FXML
    private TableColumn<DashboardTableTM,String> colAvailble;

    @FXML
    private TableColumn<DashboardTableTM, Integer> colCount;

    @FXML
    private TableView<DashboardTableTM> tblVehicleAvailable;


    @FXML
    private Label lblBookingCount;

    @FXML
    private Label lblCar;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblEmploye;

    @FXML
    private Label lblSuv;

    @FXML
    private Label lblVan;

    @FXML
    private PieChart ProfitChart;



    DashBoardMiddleBO dashBoardMiddleBO = (DashBoardMiddleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DashBoardMiddle);

    public void initialize(){

        setCellValues();
        loadAllvehicles();

        try {
            setCustomerCount();
            setEmployeeCount();
            setCarCount();
            setVanCount();
            setSuvCount();
            setReservationCount();
            populatePieChart();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    public void setCellValues(){
        colAvailble.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("totalAvailable"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("costPerDay"));

    }



    private void loadAllvehicles() {
     /*   ObservableList<DashboardTableTM> obLst = FXCollections.observableArrayList();

        try {
            List<DashboardTableTM> vehicleList = dashBoardMiddleBO.getVehicleStatistics();
            obLst.addAll(vehicleList);
            tblVehicleAvailable.setItems(obLst);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/

    }

    public void setCustomerCount() throws SQLException {
        try {
           // DashBoardMidleDAO dashboardRepo = new DashBoardMidleDAO();

            DashBoard dashboardModel = new DashBoard();
            int customerCount = dashBoardMiddleBO.getCustomerCount(dashboardModel);
            lblCustomer.setText(String.valueOf(customerCount));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void setEmployeeCount() throws SQLException {
        try {

            DashBoard dashBoardModle = new DashBoard();
            int EmployeeCount = dashBoardMiddleBO.getEmployeeCount(dashBoardModle);
            lblEmploye.setText(String.valueOf(EmployeeCount));

        }catch (SQLException i){
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public  void setCarCount(){
        try {
      //      DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoard dashBoardModle = new DashBoard();
            int CarCount = dashBoardMiddleBO.getcarCount(dashBoardModle);
            lblCar.setText(String.valueOf(CarCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setVanCount() {

        try {
         //   DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoard dashBoardModle = new DashBoard();
            int VanCount= dashBoardMiddleBO.getvanCount(dashBoardModle);

            lblVan.setText(String.valueOf(VanCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void setSuvCount(){

        try {
           // DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoard dashBoardModle = new DashBoard();
            int SuvCount = dashBoardMiddleBO.getsuvCount(dashBoardModle);

            lblSuv.setText(String.valueOf(SuvCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public  void setReservationCount(){

        try {
          //  DashboardRepo dashboardRepo = new DashboardRepo();
            DashBoard dashBoardModle = new DashBoard();
            int bookingCount = dashBoardMiddleBO.getbookingCount(String.valueOf(dashBoardModle));

            lblBookingCount.setText(String.valueOf(bookingCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void populatePieChart() {
       /* try {
            List<BookingDetails> sales = dashBoardMiddleBO.getAllSalesByPaymentStatus();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (BookingDetails sale : sales) {
                pieChartData.add(new PieChart.Data(sale.getPaymentStatus(), sale.getCount()));
            }

            ProfitChart.setData(pieChartData);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }*/
    }



    }



