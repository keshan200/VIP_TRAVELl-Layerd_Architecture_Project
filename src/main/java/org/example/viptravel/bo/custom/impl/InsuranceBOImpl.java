package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.InsuranceBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.InsuranceDAO;
import org.example.viptravel.dto.InsuranceDTO;
import org.example.viptravel.entity.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceBOImpl implements InsuranceBO {



    InsuranceDAO insuranceDAO = (InsuranceDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.INSURANCE);
    @Override
    public ArrayList<InsuranceDTO> getAllInsurance() throws SQLException, ClassNotFoundException {
         return  null;
    }

    @Override
    public boolean addInsurance(InsuranceDTO dto) throws SQLException, ClassNotFoundException {
        return insuranceDAO.add(new Insurance(dto.getInsuranceID(),dto.getCompanyName(),dto.getType(), dto.getEndDate(),dto.getRegNO()));
    }

    @Override
    public boolean updateInsurance(InsuranceDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getCurrentInsuranceID() throws SQLException, ClassNotFoundException {
        return insuranceDAO.getCurrentID();
    }

    @Override
    public boolean deleteInsurance(String Insuranceid) throws SQLException, ClassNotFoundException {
        return insuranceDAO.delete(Insuranceid);
    }

    @Override
    public Insurance searchInsurance(String Insuranceid) throws SQLException, ClassNotFoundException {
        return insuranceDAO.search(Insuranceid);
    }
}
