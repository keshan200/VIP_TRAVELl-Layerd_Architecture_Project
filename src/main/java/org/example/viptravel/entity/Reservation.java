package org.example.viptravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {

    private String ReserstionID;
    private String NIC;
    private LocalDate  reservationDate;


    public Reservation(String reserstionID) {
        ReserstionID = reserstionID;
    }


}
