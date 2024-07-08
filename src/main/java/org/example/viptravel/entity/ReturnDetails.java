package org.example.viptravel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnDetails {

    //private String returnID;// Add vehicleID field
    //private String regNo;


    private String returnID;
    private String status;
    private LocalDate returnDate;
    private String NIC;
    private  String regNo;
    private String damages;
    private String desc;


    public ReturnDetails(String returnID, String regNo) {
        this.returnID = returnID;
        this.regNo = regNo;
    }
}
