package org.example.viptravel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class RegisterDTO {

    private  String userID;
    private String password;
    private  String email;

    private  String nic;



}
