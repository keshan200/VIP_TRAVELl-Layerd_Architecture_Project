package org.example.viptravel.bo;

import org.example.viptravel.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){}


    public   static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes{
        Customer,VEHICLE,INSURANCE,PAYMENT,REGISTER,LOGIN,ReservationDETAILS
    }

    public  SuperBO getBO(BOTypes bo){
        switch (bo){
            case Customer :
                return  new CustomerBOImpl();
            case VEHICLE:
                return  new VehicleBOImpl();
            case INSURANCE:
                return  new InsuranceBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case REGISTER:
                return new EmpDtlsBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case ReservationDETAILS:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
