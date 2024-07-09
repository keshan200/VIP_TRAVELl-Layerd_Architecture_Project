package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.CustomerBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.CustomerDAO;
import org.example.viptravel.dto.CustomerDTO;
import org.example.viptravel.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{

       CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> allCUS = new ArrayList<>();
        ArrayList<Customer>cus = customerDAO.getAll();

        for (Customer c : cus){
            allCUS.add(new CustomerDTO(c.getCustomerID(),c.getNIC(),c.getName(),c.getTelNO(),c.getAddress()));
        }
        return allCUS;
    }

    @Override
    public boolean addCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
      return customerDAO.add(new Customer(dto.getCustomerID(), dto.getName(), dto.getNIC(),dto.getTelNO(), dto.getAddress()));
    }

    @Override
    public boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getName(),dto.getNIC(),dto.getTelNO(),dto.getAddress()));
    }

    @Override
    public String getCurrentCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.getCurrentID();
    }

    @Override
    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.search(id);
    }
}
