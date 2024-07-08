package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.CrudDAO;
import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO extends SuperDAO {

    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException;
    public String getCurrentID() throws SQLException, ClassNotFoundException ;

}
