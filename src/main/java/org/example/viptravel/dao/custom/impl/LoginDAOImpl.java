package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.LoginDAO;
import org.example.viptravel.entity.Login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    public Login checkCredential(String userID, String password) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userID, password FROM employee WHERE userID = ?",userID);
        if (resultSet.next()) {
            String dbPw = resultSet.getString("password");
            String useer = resultSet.getString("UserID");

            Login login = new Login(dbPw,useer);
            return login;
        }
        return  null;
    }

    public String navigateDashboard(String userID) throws IOException, SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT name from employee where userID=?", userID);
        String employeeName = null;
        if (resultSet.next()) {
            employeeName = resultSet.getString("name");
        }
       return  employeeName;
    }
}
