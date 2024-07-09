package org.example.viptravel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Payment {

    private String paymentID;
    private String status;
    private String type;
    private double fullPayment;
    private String reservationID;
    private LocalDate Paydate;
    private String paymentMethod;
    private  double advancedPay;
    private  double balancedPay;


    public Payment(double fullPayment, String type, String status, String paymentID) {

        this. fullPayment = fullPayment;
        this.type =type;
        this.status =status;
        this.paymentID=paymentID;
    }
}
