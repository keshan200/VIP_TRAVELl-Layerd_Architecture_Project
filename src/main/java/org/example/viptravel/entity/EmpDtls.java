package org.example.viptravel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class EmpDtls {

    private String NIC;
    private String name;
    private String address;
    private  int tp;
    private  String userID;
    private String password;
    private  String email;


    public EmpDtls(String NIC, String address, int tp, String userID, String password, String email) {
        this.NIC = NIC;
        this.address=address;
        this.tp=tp;
        this.userID=userID;
        this.password=password;
        this.email=email;
    }
}
