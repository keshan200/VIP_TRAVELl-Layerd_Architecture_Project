package org.example.viptravel.bo.custom;

import org.example.viptravel.bo.SuperBO;
import org.example.viptravel.dto.VehicleDTO;
import org.example.viptravel.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO extends SuperBO {

    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;
    public boolean addVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;
    public String getCurrentVehicleID() throws SQLException, ClassNotFoundException;
    public boolean deleteVehicle(String Vehicleid) throws SQLException, ClassNotFoundException;
    public Vehicle searchVehicle(String Vehicleid) throws SQLException, ClassNotFoundException;


    public List<String> getAllAvailableVehicles() throws SQLException, ClassNotFoundException;
    public List<VehicleDTO> SearchByVehicleName(String vehicleName) throws SQLException, ClassNotFoundException;
}
