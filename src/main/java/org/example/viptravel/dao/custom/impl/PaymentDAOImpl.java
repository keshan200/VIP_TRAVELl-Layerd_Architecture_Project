package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.PaymentDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");
        ArrayList<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String paymentID = resultSet.getString("paymentID");
            String status = resultSet.getString("status");
            String type = resultSet.getString("type");
            double fullPayment = resultSet.getDouble("fullpayment");
            String reservationID = resultSet.getString("reservationID");
            LocalDate paymentDate = resultSet.getDate("paymentDate").toLocalDate();
            String paymentMethod = resultSet.getString("paymentMethod");
            double advanced = resultSet.getDouble("advanced_payment");
            double balancded = resultSet.getDouble("balance_payment");


            Payment payment = new Payment(paymentID, status, type, fullPayment, reservationID, paymentDate,paymentMethod,advanced,balancded);
            paymentList.add(payment);
        }
        return paymentList;
    }



    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES (?, ?, ?, ?, ?, ?,?,?,?)",entity.getPaymentID(),entity.getStatus(),entity.getFullPayment(),entity.getReservationID(),entity.getPaydate(),entity.getPaymentMethod(),entity.getAdvancedPay(),entity.getBalancedPay());

    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET fullpayment = ?, type = ?, status = ? WHERE paymentID = ?",entity.getFullPayment(),entity.getType(),entity.getStatus(),entity.getPaymentID());
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1");
        if (rst.next()) {
            String PaymentID = rst.getString(1);
            return PaymentID;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
      return null;
    }


    public  List<Payment> getPaymentsByNIC(String NIC) throws SQLException {
        List<Payment> paymentList = new ArrayList<>();
        try (ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE status ='Pending' AND reservationID IN (SELECT reservationID FROM reservation WHERE NIC = ?)",NIC)) {
            while (resultSet.next()) {
                Payment paymentModel = new Payment(
                        resultSet.getString("paymentID"),
                        resultSet.getString("status"),
                        resultSet.getString("type"),
                        resultSet.getDouble("fullpayment"),
                        resultSet.getString("reservationID"),
                        resultSet.getDate("paymentDate").toLocalDate(), // Assuming paymentDate is a date type
                        resultSet.getString("paymentMethod"),
                        resultSet.getDouble("advanced_payment"),
                        resultSet.getDouble("balance_payment")

                );
                paymentList.add(paymentModel);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return paymentList;
    }

    public  int getPendingPayment(Payment pay) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS PendingPayment FROM payment WHERE status = 'Pending'");
        if(resultSet.next()){
         //+   System.out.println(resultSet);
            return  resultSet.getInt("PendingPayment");

        }
        return  0;
    }

    public int getCompletePayment(Payment pay) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS CompletePayment FROM payment WHERE status = 'Completed' ");
        if(resultSet.next()){
          // System.out.println(resultSet);
            return  resultSet.getInt("CompletePayment");
        }
        return  0;
    }

}
