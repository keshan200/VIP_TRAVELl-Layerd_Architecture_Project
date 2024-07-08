package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.RegisterDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.EmpDtls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public ArrayList<EmpDtls> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.execute("SELECT * FROM employee");

        ArrayList<EmpDtls> empList = new ArrayList<>();

        while (resultSet.next()) {
            String NIC = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tp = Integer.parseInt(resultSet.getString(4));
            String userID = resultSet.getString(5);
            String password = resultSet.getString(6);
            String email = resultSet.getString(7);

            EmpDtls emp = new EmpDtls(NIC,name,address,tp,userID,password,email);
            empList.add(emp);
        }
        return empList;
    }

    @Override
    public boolean add(EmpDtls entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (employe_NIC, name, address,TP,userID, password, email) VALUES (?, ?, ?, ?, ?,?,?)",entity.getNIC(),entity.getName(),entity.getAddress(),entity.getTp(),entity.getUserID(),entity.getPassword(),entity.getEmail());
    }

    @Override
    public boolean update(EmpDtls entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
      return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from employee where employe_NIC =?",id);
    }

    @Override
    public EmpDtls search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from employee where employe_NIC =?",id);

        if (resultSet.next()) {

            String NiC = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tp = Integer.parseInt(resultSet.getString(4));
            String userID = resultSet.getString(5);
            String password = resultSet.getString(6);
            String email = resultSet.getString(7);


            EmpDtls emp = new EmpDtls(NiC,name,address,tp,userID,password,email);

            return  emp;

        }
        return  null;
    }
}
