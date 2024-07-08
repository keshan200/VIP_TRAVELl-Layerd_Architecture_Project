package org.example.viptravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Return {

    private String returnID;
    private String status;
    private LocalDate returnDate;
    private String NIC;
    private String damages;
    private String desc;


}
