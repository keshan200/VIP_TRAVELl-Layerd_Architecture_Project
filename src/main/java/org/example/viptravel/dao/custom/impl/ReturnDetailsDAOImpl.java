package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.dao.custom.ReturnDetailsDAO;
import org.example.viptravel.db.DBconnection;
import org.example.viptravel.entity.ReturnDetails;
import org.example.viptravel.entity.RetutnForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturnDetailsDAOImpl implements ReturnDetailsDAO {
    @Override
    public List<ReturnDetails> getAllReturns() throws SQLException, ClassNotFoundException {
        List<ReturnDetails> returnDetailsList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT r.returnID, r.status, r.returnDate, r.NIC, r.damages, r.description, rd.regNo FROM return_ r JOIN returndetails rd ON r.returnID = rd.returnID");

        while (resultSet.next()) {

            String returnID = resultSet.getString("returnID");
            String status = resultSet.getString("status");
            LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
            String NIC = resultSet.getString("NIC");
            String damages = resultSet.getString("damages");
            String description = resultSet.getString("description");
            String regNo = resultSet.getString("regNo");

            ReturnDetails er = new ReturnDetails(returnID, status, returnDate, NIC,regNo, damages, description);
            returnDetailsList.add(er);

        }
        return returnDetailsList;
    }

    @Override
    public List<ReturnDetails> getReturnsToCartByNIC(String nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT rd.returnID, r.status, r.returnDate, r.NIC, rd.regNo, r.damages, r.description FROM returnDetails rd JOIN return_ r ON rd.returnID = r.returnID JOIN customer c ON r.customerID = c.customerID WHERE c.NIC = ?",nic);

        List<ReturnDetails> returnList = new ArrayList<>();
        while (resultSet.next()) {
            String returnID = resultSet.getString("returnID");
            String status = resultSet.getString("status");
            LocalDate returnDate = resultSet.getDate("returnDate").toLocalDate();
            String customerNIC = resultSet.getString("NIC");
            String regNo = resultSet.getString("regNo");
            String damages = resultSet.getString("damages");
            String description = resultSet.getString("description");

            ReturnDetails returnDetailsModel = new ReturnDetails(returnID, status, returnDate, customerNIC, regNo, damages, description);
            returnList.add(returnDetailsModel);
        }
        return returnList;
    }

    @Override
    public boolean save(List<ReturnDetails> returnList) throws SQLException, ClassNotFoundException {
        for (ReturnDetails returnDetail : returnList) {
            boolean isSaved = saveDetails(returnDetail);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveDetails(ReturnDetails returnDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO returndetails (returnID, regNo) VALUES (?, ?)",returnDetail.getReturnID(),returnDetail.getRegNo());
    }


}
