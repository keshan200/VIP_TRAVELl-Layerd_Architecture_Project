package org.example.viptravel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class Customer {
    private String customerID;
    private String NIC;
    private String name;
    private  int telNO;
    private  String address;


    /*public Customer(String NIC, String name,String address,int telNO) {
        this.NIC = NIC;
        this.name = name;
        this.telNO = telNO;
        this.address = address;
    }*/

    public Customer(String NIC, String name, int telNO, String address) {
        this.NIC = NIC;
        this.name = name;
        this.telNO = telNO;
        this.address = address;
    }
    public Customer(String NIC, String name) {
        this.NIC = NIC;
        this.name = name;
    }

    public Customer(String NIC) {
        this.NIC=NIC;
    }


}
