package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.entity.Login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {

    public Login checkCredential(String userID, String password) throws SQLException, IOException, ClassNotFoundException ;

    public String navigateDashboard(String userID) throws IOException, SQLException, ClassNotFoundException ;

}
