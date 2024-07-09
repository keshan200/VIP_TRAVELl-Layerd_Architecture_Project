package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.ReturnDAO;
import org.example.viptravel.entity.Return;
import org.example.viptravel.entity.ReturnDetails;
import org.example.viptravel.entity.RetutnForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReturnDAOImpl implements ReturnDAO {
    @Override
    public boolean save(Return retModle) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO return_(returnID, status, returnDate, NIC, damages, description) VALUES (?,?,?,?,?,?)",retModle.getReturnID(),retModle.getStatus(),retModle.getReturnDate(),retModle.getNIC(),retModle.getDamages(),retModle.getStatus());
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT returnID FROM return_ ORDER BY returnID DESC LIMIT 1");

        if (resultSet.next()) {
            String returnID = resultSet.getString(1);
            return returnID;
        }
        return  null;
    }
}


