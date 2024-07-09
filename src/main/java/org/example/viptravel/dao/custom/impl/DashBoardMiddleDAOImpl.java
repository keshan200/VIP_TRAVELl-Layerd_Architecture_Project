package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.DashBoardMidleDAO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.DashBoard;
import org.example.viptravel.view.tdm.DashboardTableTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashBoardMiddleDAOImpl implements DashBoardMidleDAO {


    @Override
    public int getCustomerCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS customer_count FROM customer");

        if(resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;    }

    @Override
    public int getEmployeeCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT count(*) as employee_count from employee");

        if (resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;
    }

    @Override
    public int getcarCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS carCount FROM vehicle WHERE vehicleType = 'Car' ");

        if(resultSet.next()){
            return  resultSet.getInt("carCount");
        }
        return  0;
    }

    @Override
    public int getvanCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS vanCount FROM vehicle WHERE vehicleType = 'Van'");

        if (resultSet .next()) {
            return resultSet.getInt("vanCount");

        }
        return  0;
    }

    @Override
    public int getsuvCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS suvCount FROM vehicle WHERE vehicleType ='Suv'");

        if (resultSet.next()) {
            return resultSet.getInt("suvCount");

        }
        return 0;
    }

    @Override
    public int getbookingCount(String reservations) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS totalReservations FROM reservation");

        if (resultSet.next()) {

            return resultSet.getInt("totalReservations");
        }
        return 0;
    }

    @Override
    public List<DashboardTableTM> getVehicleStatistics() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT vehicleName, COUNT(*) AS totalAvailable, AVG(cost) AS costPerDay FROM vehicle WHERE availability = 'Available' GROUP BY vehicleName");


        List<DashboardTableTM> vehicleStatisticsList = new ArrayList<>();

        while (resultSet.next()) {
            String vehicleName = resultSet.getString("vehicleName");
            int totalAvailable = resultSet.getInt("totalAvailable");
            double costPerDay = resultSet.getDouble("costPerDay");
            vehicleStatisticsList.add(new DashboardTableTM(vehicleName, totalAvailable, costPerDay));
        }
        return vehicleStatisticsList;
    }

    @Override
    public List<BookingDetails> getAllSalesByPaymentStatus() throws SQLException {
        List<BookingDetails> sales = new ArrayList<>();

        try {
            ResultSet resultSet = SQLUtil.execute("SELECT status, COUNT(*) AS Count FROM payment GROUP BY status");
            while (resultSet.next()) {
                String paymentStatus = resultSet.getString("status");
                int count = resultSet.getInt("Count");
                sales.add(new BookingDetails(paymentStatus, count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sales;
    }
}
