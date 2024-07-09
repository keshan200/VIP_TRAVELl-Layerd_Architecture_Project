package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.ReturnBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.ReturnDAO;
import org.example.viptravel.dao.custom.ReturnDetailsDAO;
import org.example.viptravel.db.DBconnection;
import org.example.viptravel.dto.ReturnDTO;
import org.example.viptravel.dto.ReturnDetailsDTO;
import org.example.viptravel.dto.RetutnFormDTO;
import org.example.viptravel.entity.Return;
import org.example.viptravel.entity.ReturnDetails;
import org.example.viptravel.entity.RetutnForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnBOImpl implements ReturnBO {

     ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.RETURN);
     ReturnDetailsDAO returnDetailsDAO = (ReturnDetailsDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.ReturnDETAILS);
    @Override
    public boolean save(ReturnDTO retModle) throws SQLException, ClassNotFoundException {
        return returnDAO.save(new Return(retModle.getReturnID(),retModle.getStatus(),retModle.getReturnDate(),retModle.getNIC(),retModle.getDamages(),retModle.getStatus()));
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return returnDAO.getCurrentId();
    }

    @Override
    public List<ReturnDetailsDTO> getAllReturns() throws SQLException, ClassNotFoundException {
        List<ReturnDetails> all = returnDetailsDAO.getAllReturns();
        List<ReturnDetailsDTO>convert = new ArrayList<>();

        for (ReturnDetails r : all){
            convert.add(new ReturnDetailsDTO(r.getReturnID(),r.getStatus(),r.getReturnDate(),r.getNIC(),r.getRegNo(),r.getDamages(),r.getDesc()));
        }
        return convert;
    }

    @Override
    public List<ReturnDetailsDTO> getReturnsToCartByNIC(String nic) throws SQLException, ClassNotFoundException {
        return  null;
    }

    @Override
    public boolean save(List<ReturnDetailsDTO> returnList) throws SQLException, ClassNotFoundException {
        for (ReturnDetailsDTO returnDetail : returnList) {
            boolean isSaved = saveDetails(returnDetail);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveDetails(ReturnDetailsDTO returnDetail) throws SQLException, ClassNotFoundException {
        return returnDetailsDAO.saveDetails(new ReturnDetails(returnDetail.getReturnID(),returnDetail.getRegNo()));
    }

    @Override
    public boolean SetReturn(RetutnForm ret) throws SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isReturnSaved = returnDAO.save(ret.getReturn());
            if (isReturnSaved) {
                boolean isReturnDetailsSaved = returnDetailsDAO.save(ret.getReturnList());
                if (isReturnDetailsSaved) {
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

