package org.example.viptravel.dao.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.DashBoard;
import org.example.viptravel.view.tdm.DashboardTableTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DashBoardMidleDAO extends SuperDAO {


    public int getCustomerCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException;

    public int getEmployeeCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException;

    public  int getcarCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException;

    public  int getvanCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException;

    public  int getsuvCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException;

    public  int getbookingCount(String reservations) throws SQLException, ClassNotFoundException;

    public  List<DashboardTableTM> getVehicleStatistics() throws SQLException, ClassNotFoundException;

    public  List<BookingDetails> getAllSalesByPaymentStatus() throws SQLException;
}
