package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.EmpDtlsBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.RegisterDAO;
import org.example.viptravel.dto.EmpDtlsDTO;
import org.example.viptravel.dto.PaymentDTO;
import org.example.viptravel.entity.EmpDtls;
import org.example.viptravel.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDtlsBOImpl implements EmpDtlsBO {


    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.REGISTER);

    @Override
    public ArrayList<EmpDtlsDTO> getAllEmpDtls() throws SQLException, ClassNotFoundException {
        ArrayList<EmpDtlsDTO> allPay = new ArrayList<>();
        ArrayList<EmpDtls> pay = registerDAO.getAll();

        for (EmpDtls dto : pay) {
            allPay.add(new EmpDtlsDTO(dto.getNIC(),dto.getName(),dto.getAddress(),dto.getTp(),dto.getUserID(),dto.getPassword(),dto.getEmail()));
        }
        return allPay;
    }

    @Override
    public boolean addEmpDtls(EmpDtlsDTO dto) throws SQLException, ClassNotFoundException {
        return registerDAO.add(new EmpDtls(dto.getNIC(),dto.getName(),dto.getAddress(),dto.getTp(),dto.getUserID(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public boolean updateEmpDtls(EmpDtlsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getCurrentEmpDtlsID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteEmpDtls(String EmpDtlsid) throws SQLException, ClassNotFoundException {
        return registerDAO.delete(EmpDtlsid);
    }

    @Override
    public EmpDtls searchEmpDtls(String EmpDtlsid) throws SQLException, ClassNotFoundException {
        return registerDAO.search(EmpDtlsid);
    }
}
