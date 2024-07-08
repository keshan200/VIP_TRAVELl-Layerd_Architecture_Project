package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dto.PaymentDTO;
import org.example.viptravel.entity.Insurance;
import org.example.viptravel.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {


    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;
    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    public String getCurrentPaymentID() throws SQLException, ClassNotFoundException;
    public boolean deletePayment(String Paymentid) throws SQLException, ClassNotFoundException;
    public Insurance searchPayment(String Paymentid) throws SQLException, ClassNotFoundException;




    public List<Payment> getPaymentsByNIC(String NIC) throws SQLException;

    public int getPendingPayment(PaymentDTO pay) throws SQLException, ClassNotFoundException;

    public int getCompletePayment(PaymentDTO pay) throws SQLException, ClassNotFoundException;
}
