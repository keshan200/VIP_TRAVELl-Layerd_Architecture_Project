package org.example.viptravel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class DashBoardDTO {

    private String carCount;
    private String suvCount;
    private String vanCount;
    private String employeeCount;
    private String customerCount;
    private  String reservationCount;


}
