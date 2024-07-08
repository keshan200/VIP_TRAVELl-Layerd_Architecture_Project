package org.example.viptravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {

    private String ReserstionID;
    private String NIC;
    private LocalDate  reservationDate;


    public ReservationDTO(String reserstionID) {
        ReserstionID = reserstionID;
    }


}
