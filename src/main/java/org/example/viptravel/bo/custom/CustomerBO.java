package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dto.CustomerDTO;
import org.example.viptravel.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public boolean addCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public String getCurrentCustomerID() throws SQLException, ClassNotFoundException;
    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException;
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
