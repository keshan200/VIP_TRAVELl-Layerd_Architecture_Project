package org.example.viptravel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.viptravel.entity.Payment;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDTO extends Payment {

    private String paymentID;
    private String status;
    private String type;
    private double fullPayment;
    private String reservationID;
    private LocalDate Paydate;
    private String paymentMethod;
    private  double advancedPay;
    private  double balancedPay;


    public PaymentDTO(double fullPayment, String type, String status, String paymentID) {

        this. fullPayment = fullPayment;
        this.type =type;
        this.status =status;
        this.paymentID=paymentID;
    }

}
