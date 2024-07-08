package org.example.viptravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data


public class BookingFormDTO {

    private ReservationDTO reservation;
    private List<BookingDetailsDTO> BookingList;

}
