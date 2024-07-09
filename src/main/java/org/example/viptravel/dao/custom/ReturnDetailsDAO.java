package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.entity.ReturnDetails;
import org.example.viptravel.entity.RetutnForm;

import java.sql.SQLException;
import java.util.List;

public interface ReturnDetailsDAO extends SuperDAO {

     List<ReturnDetails> getAllReturns() throws SQLException, ClassNotFoundException;

      List<ReturnDetails> getReturnsToCartByNIC(String nic) throws SQLException, ClassNotFoundException;

      boolean save(List<ReturnDetails> returnList) throws SQLException, ClassNotFoundException;

      boolean saveDetails(ReturnDetails returnDetail) throws SQLException, ClassNotFoundException;


}
