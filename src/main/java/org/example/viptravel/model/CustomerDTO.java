package org.example.viptravel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class CustomerDTO {
    private String customerID;
    private String NIC;
    private String name;
    private  int telNO;
    private  String address;


    /*public CustomerDTO(String NIC, String name, int telNO, String address) {
        this.NIC = NIC;
        this.name = name;
        this.telNO = telNO;
        this.address = address;
    }*/
    public CustomerDTO(String NIC, String name, int telNO, String address) {
        this.NIC = NIC;
        this.name = name;
        this.telNO = telNO;
        this.address = address;
    }
    public CustomerDTO(String NIC, String name) {
        this.NIC = NIC;
        this.name = name;
    }


}
