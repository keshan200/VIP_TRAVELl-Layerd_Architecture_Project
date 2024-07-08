package org.example.viptravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.viptravel.dto.BookingDetailsDTO;
import org.example.viptravel.dto.ReservationDTO;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data


public class BookingForm {

    private Reservation reservation;
    private List<BookingDetails> BookingList;


}
