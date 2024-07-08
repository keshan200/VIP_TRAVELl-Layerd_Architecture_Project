package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.LoginBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.LoginDAO;
import org.example.viptravel.dto.LoginDTO;
import org.example.viptravel.entity.Login;

import java.io.IOException;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public Login checkCredential(String userID, String password) throws SQLException, IOException, ClassNotFoundException {
        return loginDAO.checkCredential(userID,password);
    }

    @Override
    public String navigateDashboard(String userID) throws IOException, SQLException, ClassNotFoundException {
        return loginDAO.navigateDashboard(userID);
    }
}
