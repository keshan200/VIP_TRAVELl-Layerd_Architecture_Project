package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dto.*;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.BookingForm;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {




    public boolean add(ReservationDTO entity) throws SQLException, ClassNotFoundException;

    public String getCurrentID() throws SQLException, ClassNotFoundException ;

    public  boolean placeBooking(BookingForm b) throws SQLException;

    public CustomerDTO getCustomerNameByNIC(String nic) throws SQLException, ClassNotFoundException;

    public List<BookingDetailsDTO> getBookingDetailsByNIC(String NIC) throws SQLException, ClassNotFoundException;

    public  List<BookingDetailsDTO> getAllBookingDetails() throws SQLException, ClassNotFoundException;


    public  boolean SaveDetails(BookingDetailsDTO b) throws SQLException, ClassNotFoundException;

    public  boolean save(List<BookingDetailsDTO> BookingList) throws SQLException, ClassNotFoundException;

}
