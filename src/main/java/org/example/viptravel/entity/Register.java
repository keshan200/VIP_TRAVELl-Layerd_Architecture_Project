package org.example.viptravel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Register {

    private  String userID;
    private String password;
    private  String email;

    private  String nic;



}
