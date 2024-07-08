package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.CrudDAO;
import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.dto.BookingDetailsDTO;
import org.example.viptravel.dto.CustomerDTO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDetailsDAO extends SuperDAO {


    public  boolean SaveDetails(BookingDetails b) throws SQLException, ClassNotFoundException;

    public  boolean save(List<BookingDetails> BookingList) throws SQLException, ClassNotFoundException;

    public Customer getCustomerNameByNIC(String nic) throws SQLException, ClassNotFoundException;

    public  List<BookingDetails> getBookingDetailsByNIC(String NIC) throws SQLException, ClassNotFoundException;

    public  List<BookingDetails> getAllBookingDetails() throws SQLException, ClassNotFoundException;

}
