package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dto.ReturnDTO;
import org.example.viptravel.dto.ReturnDetailsDTO;
import org.example.viptravel.dto.RetutnFormDTO;
import org.example.viptravel.entity.Return;
import org.example.viptravel.entity.ReturnDetails;
import org.example.viptravel.entity.RetutnForm;

import java.sql.SQLException;
import java.util.List;

public interface ReturnBO extends SuperBO {

    public boolean save(ReturnDTO retModle) throws SQLException, ClassNotFoundException;
    public String getCurrentId() throws SQLException, ClassNotFoundException;
    List<ReturnDetailsDTO> getAllReturns() throws SQLException, ClassNotFoundException;
    List<ReturnDetailsDTO> getReturnsToCartByNIC(String nic) throws SQLException, ClassNotFoundException;
    boolean save(List<ReturnDetailsDTO> returnList) throws SQLException, ClassNotFoundException;
    boolean saveDetails(ReturnDetailsDTO returnDetail) throws SQLException, ClassNotFoundException;
    boolean SetReturn(RetutnForm ret) throws SQLException;

}
