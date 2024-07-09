package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.InsuranceDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.Insurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceDAOImpl implements InsuranceDAO {
    @Override
    public ArrayList<Insurance> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT insuranceID FROM insurance ORDER BY insuranceID DESC LIMIT 1");
        if (resultSet.next()) {
            String insuranceID = resultSet.getString(1);
            return insuranceID;
        }
        return  null;
    }


    @Override
    public boolean add(Insurance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT into insurance(insuranceID, companyName, type, endDate, regNO)values(?,?,?,?,?)",entity.getInsuranceID(),entity.getCompanyName(),entity.getType(),entity.getEndDate(),entity.getRegNO());
    }


    @Override
    public boolean update(Insurance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE insurance SET companyName = ?, type = ?, endDate = ? WHERE insuranceID = ? ",entity.getCompanyName(),entity.getType(),entity.getEndDate(),entity.getInsuranceID());
    }

    @Override
    public Insurance search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * from insurance where regNo = ?",id);

        if (rst.next()) {

            String insuranceID =  rst.getString(1);
            String companyName = rst.getString(2);
            String type = rst.getString(3);
            String date = rst.getString(4);
            String regNo  = rst.getString(5);
            String vehicleID = rst.getString(6);


            Insurance insuranceModle = new Insurance(insuranceID,companyName,type,date,regNo,vehicleID);
            return  insuranceModle;

        }
        return  null;

    }
}
