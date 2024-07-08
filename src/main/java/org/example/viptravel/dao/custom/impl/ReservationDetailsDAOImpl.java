package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.ReservationDetailsDAO;
import org.example.viptravel.dto.BookingDetailsDTO;
import org.example.viptravel.dto.CustomerDTO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsDAOImpl implements ReservationDetailsDAO {

    @Override
    public boolean SaveDetails(BookingDetails b) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO bookingDetails (RegNo,reservationID,fullCost,startDate,endDate,Days) VALUES (?, ?, ?, ?, ?, ?)",b.getRegNo(),b.getReservationID(),b.getTotalCost(),b.getStartDate(),b.getEndDate(),b.getDaysCount());
    }

    @Override
    public boolean save(List<BookingDetails> BookingList) throws SQLException, ClassNotFoundException {
        for (BookingDetails bookings : BookingList) {
            boolean isSaved = SaveDetails(bookings);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }


    @Override
    public Customer getCustomerNameByNIC(String nic) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtil.execute("SELECT  name FROM customer WHERE NIC = ?",nic);

        if (resultSet.next()) {
            String customerNIC = nic;
            String name = resultSet.getString("name");

            Customer customerModle = new Customer(customerNIC,name);
            return customerModle;

        }
        return null;


    }

    @Override
    public List<BookingDetails> getBookingDetailsByNIC(String NIC) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  bd.regNo, bd.reservationID, bd.fullCost, bd.startDate, bd.endDate, bd.Days FROM bookingDetails bd JOIN reservation r ON bd.reservationID = r.reservationID WHERE r.NIC = ?",NIC);
        List<BookingDetails> bookingDetailsList = new ArrayList<>();

        while (resultSet.next()) {
            String regNo = resultSet.getString("regNo");
            String reservationID = resultSet.getString("reservationID");
            LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
            LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
            int daysCount = resultSet.getInt("Days");
            double totalCost = resultSet.getDouble("fullCost");


            BookingDetails b = new BookingDetails(reservationID,regNo,startDate,endDate,daysCount,totalCost);
            bookingDetailsList.add(b);
        }

        return bookingDetailsList;
    }

    @Override
    public List<BookingDetails> getAllBookingDetails() throws SQLException, ClassNotFoundException {

        List<BookingDetails> bookingDetailsList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT regNo, reservationID, fullCost, startDate, endDate, Days FROM bookingDetails");

        while (resultSet.next()) {
            String reservationID = resultSet.getString("reservationID");
            String regNo = resultSet.getString("regNo");
            double fullCost = resultSet.getDouble("fullCost");
            LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
            LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
            int days = resultSet.getInt("Days");

            BookingDetails bookingDetails = new BookingDetails(regNo, reservationID,startDate, endDate, days,fullCost);
            bookingDetailsList.add(bookingDetails);


        }
        return bookingDetailsList;
    }
}
