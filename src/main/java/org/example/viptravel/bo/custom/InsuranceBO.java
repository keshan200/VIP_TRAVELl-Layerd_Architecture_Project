package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dto.InsuranceDTO;
import org.example.viptravel.dto.VehicleDTO;
import org.example.viptravel.entity.Insurance;
import org.example.viptravel.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InsuranceBO extends SuperBO {

    public ArrayList<InsuranceDTO> getAllInsurance() throws SQLException, ClassNotFoundException;
    public boolean addInsurance(InsuranceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateInsurance(InsuranceDTO dto) throws SQLException, ClassNotFoundException;
    public String getCurrentInsuranceID() throws SQLException, ClassNotFoundException;
    public boolean deleteInsurance(String Insuranceid) throws SQLException, ClassNotFoundException;
    public Insurance searchInsurance(String Insuranceid) throws SQLException, ClassNotFoundException;
}
