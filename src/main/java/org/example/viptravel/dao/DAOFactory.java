package org.example.viptravel.dao;

import org.example.viptravel.bo.BOFactory;
import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.bo.custom.impl.CustomerBOImpl;
import org.example.viptravel.dao.custom.impl.*;

public class DAOFactory {



    private static DAOFactory daoFactory;
    private DAOFactory(){}


    public   static DAOFactory getdaoFactory(){
        return (daoFactory==null)?daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,VEHICLE,INSURANCE,PAYMENT,REGISTER,LOGIN,RESERVATION,ReservationDETAILS
    }

    public SuperDAO getDAO(DAOTypes dao){
        switch (dao){
            case CUSTOMER:
                return  new CustomerDAOImpl();
            case VEHICLE:
                return  new VehicleDAOImpl();
            case INSURANCE:
                return  new InsuranceDAOImpl();
            case PAYMENT:
                return  new PaymentDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            case LOGIN:
                return  new LoginDAOImpl();
            case RESERVATION:
                return  new ReservationDAOImpl();
            case ReservationDETAILS:
                return new ReservationDetailsDAOImpl();
            default:
                return null;
        }
    }
}
