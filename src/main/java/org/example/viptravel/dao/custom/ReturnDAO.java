package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.SuperDAO;
import org.example.viptravel.entity.Return;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ReturnDAO extends SuperDAO {


    public   boolean save(Return retModle) throws SQLException, ClassNotFoundException;

    public  String getCurrentId() throws SQLException, ClassNotFoundException;


}
