package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.ReservationDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {


    @Override
    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation (reservationID, NIC, reservationDate)values (?,?,?)",entity.getReserstionID(),entity.getNIC(),entity.getReservationDate());
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT reservationID FROM reservation ORDER BY reservationID DESC LIMIT 1");
        if (resultSet.next()) {
            String ResID = resultSet.getString(1);
            return ResID;
        }
        return null;

    }


}
