package org.example.viptravel.dao.custom;

import org.example.viptravel.dao.CrudDAO;
import org.example.viptravel.entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {

    public  List<String> getAllAvailableVehicles() throws SQLException, ClassNotFoundException;

    public  List<Vehicle> SearchByVehicleName(String vehicleName) throws SQLException, ClassNotFoundException;

}
