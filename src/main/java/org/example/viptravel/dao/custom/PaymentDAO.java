package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.CrudDAO;
import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.db.DBconnection;
import org.example.viptravel.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {

    public  List<Payment> getPaymentsByNIC(String NIC)throws SQLException;

    public  int getPendingPayment(Payment pay)throws SQLException, ClassNotFoundException;

    public int getCompletePayment(Payment pay)throws SQLException, ClassNotFoundException;
}
