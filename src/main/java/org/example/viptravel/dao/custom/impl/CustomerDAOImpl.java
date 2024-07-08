package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.CustomerDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from customer");

        ArrayList<Customer> customer = new ArrayList<>();
        while (resultSet.next()){

            String cutomerID = resultSet.getString(1);
            String NIC = resultSet.getString(2);
            String name = resultSet.getString(3);
            int telno = Integer.parseInt(resultSet.getString(4));
            String addrs = resultSet.getString(5);

            Customer cus = new Customer(cutomerID,NIC,name,telno,addrs);
            customer.add(cus);
        }
        return  customer;
    }



    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT customerID FROM customer ORDER BY customerID DESC LIMIT 1");
        if (resultSet.next()) {
            String CustomerID = resultSet.getString(1);
            return CustomerID;
        }
        return  null;
    }


    @Override

    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
         return SQLUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?)",entity.getCustomerID(),entity.getNIC(),entity.getName(),entity.getTelNO(),entity.getAddress());
    }


    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?, telephoneNO = ?, address = ? WHERE NIC = ?",entity.getName(),entity.getTelNO(),entity.getAddress(),entity.getNIC());
    }



    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from customer where customerID =?",id);
    }

    @Override
    public  Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT  * from customer where NIC =?",id);
        rst.next();
        String custID = rst.getString(1);
        String Nic = rst.getString(2);
        String name = rst.getString(3);
        int telNo = Integer.parseInt(rst.getString(4));
        String adrs = rst.getString(5);

        Customer customerModle = new Customer(custID,Nic,name,telNo,adrs);

        return  customerModle;
    }
}
