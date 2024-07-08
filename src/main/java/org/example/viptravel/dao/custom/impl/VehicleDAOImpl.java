package org.example.viptravel.dao.custom.impl;

import org.example.viptravel.dao.SQLUtil;
import org.example.viptravel.dao.custom.VehicleDAO;
import org.example.viptravel.dto.ReservationDTO;
import org.example.viptravel.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehicle");

        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        while (resultSet.next()) {
            String vehicleID = resultSet.getString(1);
            String regNo = resultSet.getString(2);
            String year = resultSet.getString(3);
            String vehicleName = resultSet.getString(4);
            String fuelType = resultSet.getString(5);
            String vehicleType = resultSet.getString(6);
            double cost = Double.parseDouble(resultSet.getString(7));
            String availability = resultSet.getString(8);

            Vehicle vehicle = new Vehicle(vehicleID, regNo, year, vehicleName, fuelType, vehicleType, cost, availability);
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }


    @Override
    public boolean add(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vehicle VALUES (?, ?, ?, ?, ?, ?, ?, ?)", entity.getVehicleID(), entity.getRegNo(), entity.getYear(), entity.getVehicleName(), entity.getFuelType(), entity.getVehicleType(), entity.getCost(), entity.getAvailability());
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE vehicle SET cost = ?, availability = ? WHERE vehicleID = ?", entity.getCost(), entity.getAvailability(), entity.getVehicleID());
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT vehicleID FROM vehicle ORDER BY vehicleID DESC LIMIT 1");
        if (rst.next()) {
            String vehicleID = rst.getString(1);
            return vehicleID;
        }
        return null;
    }

    @Override
    public boolean delete(String VehicleId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from vehicle where vehicleID =?", VehicleId);
    }

    @Override
    public Vehicle search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from vehicle where vehicleID =?",id);

        if (resultSet.next()) {

            String vehicleId = resultSet.getString(1);
            String RegNo = resultSet.getString(2);
            String year = resultSet.getString(3);
            String vehicleName = resultSet.getString(4);
            String fuelType = resultSet.getString(5);
            String vehicleType = resultSet.getString(6);
            double cost = resultSet.getDouble(7);
            String availability = resultSet.getString(8);

            Vehicle vehicleModle = new Vehicle(vehicleId, RegNo, year, vehicleName, fuelType, vehicleType, cost, availability);
            return vehicleModle;
        }
        return  null;
    }

    @Override
    public List<String> getAllAvailableVehicles() throws SQLException, ClassNotFoundException {

            ResultSet resultSet = SQLUtil.execute("SELECT vehicleName from vehicle where availability='Available'");

            List<String> nameList = new ArrayList<>();
            while (resultSet.next()){
                nameList.add(resultSet.getString("vehicleName"));
            }
            return nameList;
        }

    @Override
    public List<Vehicle> SearchByVehicleName(String vehicleName) throws SQLException, ClassNotFoundException {

            ResultSet resultSet = SQLUtil.execute("SELECT vehicleName, regNo, cost AS costPerDay FROM vehicle WHERE availability = 'Available' AND vehicleName = ?",vehicleName);

            List<Vehicle> resultList = new ArrayList<>();
            while (resultSet.next()) {
                String vehicleNameResult = resultSet.getString(1);
                String regNo = resultSet.getString(2);
                Double cost = resultSet.getDouble(3);
                Vehicle vehicleModle = new Vehicle(vehicleNameResult, regNo, cost);
                resultList.add(vehicleModle);
            }
            return resultList;

    }

}
