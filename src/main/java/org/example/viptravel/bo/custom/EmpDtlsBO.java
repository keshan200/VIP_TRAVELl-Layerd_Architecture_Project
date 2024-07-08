package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.dto.EmpDtlsDTO;
import org.example.viptravel.dto.InsuranceDTO;
import org.example.viptravel.entity.EmpDtls;
import org.example.viptravel.entity.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmpDtlsBO extends SuperBO {
    public ArrayList<EmpDtlsDTO> getAllEmpDtls() throws SQLException, ClassNotFoundException;
    public boolean addEmpDtls(EmpDtlsDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmpDtls(EmpDtlsDTO dto) throws SQLException, ClassNotFoundException;
    public String getCurrentEmpDtlsID() throws SQLException, ClassNotFoundException;
    public boolean deleteEmpDtls(String EmpDtlsid) throws SQLException, ClassNotFoundException;
    public EmpDtls searchEmpDtls(String EmpDtlsid) throws SQLException, ClassNotFoundException;
}
