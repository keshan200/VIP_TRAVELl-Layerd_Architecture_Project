package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.ReservationBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.CustomerDAO;
import org.example.viptravel.dao.custom.ReservationDAO;
import org.example.viptravel.dao.custom.ReservationDetailsDAO;
import org.example.viptravel.db.DBconnection;
import org.example.viptravel.dto.BookingDetailsDTO;
import org.example.viptravel.dto.BookingFormDTO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.BookingForm;
import org.example.viptravel.entity.Customer;
import org.example.viptravel.entity.Reservation;
import org.example.viptravel.dto.CustomerDTO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ReservationDetailsDAO reservationDetailsDAO = (ReservationDetailsDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.ReservationDETAILS);


    CustomerDAO  customerDAO = (CustomerDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean add(ReservationDTO entity) throws SQLException, ClassNotFoundException {
        return reservationDAO.save(new Reservation(entity.getReserstionID(),entity.getNIC(),entity.getReservationDate()));
    }



    public boolean SaveDetails(BookingDetailsDTO b) throws SQLException, ClassNotFoundException {
        return reservationDetailsDAO.SaveDetails(new BookingDetails(b.getRegNo(),b.getReservationID(),b.getStartDate(),b.getEndDate(),b.getDaysCount(),b.getTotalCost()));
    }

    @Override
    public boolean save(List<BookingDetailsDTO> BookingList) throws SQLException, ClassNotFoundException {
        for (BookingDetailsDTO bookings : BookingList) {
            boolean isSaved = SaveDetails(bookings);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return reservationDAO.getCurrentID();
    }


    @Override
    public CustomerDTO getCustomerNameByNIC(String nic) throws SQLException, ClassNotFoundException {
    //  return reservationDetailsDAO.getCustomerNameByNIC(new Customer(nic));
        return null;
    }

    @Override
    public List<BookingDetailsDTO> getBookingDetailsByNIC(String NIC) throws SQLException, ClassNotFoundException {

        List<BookingDetails> entity =reservationDetailsDAO.getBookingDetailsByNIC(NIC);
        List<BookingDetailsDTO> dto = new ArrayList<>();
                    for (BookingDetails b : entity) {
                        dto.add(new BookingDetailsDTO(b.getRegNo(), b.getReservationID(),b.getStartDate(),b.getEndDate(),b.getDaysCount(),b.getTotalCost()));
                    }

                    return dto;
                  }


    @Override
    public List<BookingDetailsDTO> getAllBookingDetails() throws SQLException, ClassNotFoundException {
       List<BookingDetails> entity =reservationDetailsDAO.getAllBookingDetails();
        List<BookingDetailsDTO> dto = new ArrayList<>();
        for (BookingDetails b : entity) {
            dto.add(new BookingDetailsDTO(b.getRegNo(),b.getReservationID(),b.getStartDate(),b.getEndDate(),b.getDaysCount(),b.getTotalCost()));

        }
        return dto;
    }


    @Override
   public boolean placeBooking(BookingForm b) throws SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isBookingSaved = reservationDAO.save(b.getReservation());
            if (isBookingSaved) {

                boolean isBookingDetailSaved = reservationDetailsDAO.save(b.getBookingList());
                if (isBookingDetailSaved) {
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}


