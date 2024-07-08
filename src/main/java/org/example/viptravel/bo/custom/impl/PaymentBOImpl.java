package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.PaymentBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.PaymentDAO;
import org.example.viptravel.dto.PaymentDTO;
import org.example.viptravel.entity.Insurance;
import org.example.viptravel.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {


    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPay = new ArrayList<>();
        ArrayList<Payment> pay = paymentDAO.getAll();

        for (Payment dto : pay) {
            allPay.add(new PaymentDTO(dto.getPaymentID(), dto.getStatus(), dto.getType(), dto.getFullPayment(), dto.getReservationID(), dto.getPaydate(), dto.getPaymentMethod(), dto.getAdvancedPay(), dto.getBalancedPay()));
        }
        return allPay;
    }

    @Override
    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getPaymentID(), dto.getStatus(), dto.getType(), dto.getFullPayment(), dto.getReservationID(), dto.getPaydate(), dto.getPaymentMethod(), dto.getAdvancedPay(), dto.getBalancedPay()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getCurrentPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.getCurrentID();
    }

    @Override
    public boolean deletePayment(String Paymentid) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Insurance searchPayment(String Paymentid) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Payment> getPaymentsByNIC(String NIC) throws SQLException {
        List<PaymentDTO> allPay = new ArrayList<>();
        List<Payment> pay = paymentDAO.getPaymentsByNIC(NIC);

        for (Payment dto : pay) {
            allPay.add(new PaymentDTO(dto.getPaymentID(), dto.getStatus(), dto.getType(), dto.getFullPayment(), dto.getReservationID(), dto.getPaydate(), dto.getPaymentMethod(), dto.getAdvancedPay(), dto.getBalancedPay()));
        }
        return pay;

    }

    @Override
    public int getPendingPayment(PaymentDTO pay) throws SQLException, ClassNotFoundException {
        return paymentDAO.getPendingPayment(pay);
    }

    @Override
    public int getCompletePayment(PaymentDTO pay) throws SQLException, ClassNotFoundException {
        return paymentDAO.getCompletePayment(pay);
    }

}
