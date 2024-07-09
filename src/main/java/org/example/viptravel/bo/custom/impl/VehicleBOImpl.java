package org.example.viptravel.bo.custom.impl;

import org.example.viptravel.bo.custom.VehicleBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.VehicleDAO;
import org.example.viptravel.dto.VehicleDTO;
import org.example.viptravel.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {


    VehicleDAO vehicledao = (VehicleDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);


    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> allCUS = new ArrayList<>();
        ArrayList<Vehicle>cus = vehicledao.getAll();

        for (Vehicle c : cus){
            allCUS.add(new VehicleDTO(c.getVehicleID(),c.getRegNo(),c.getYear(),c.getVehicleName(),c.getFuelType(),c.getVehicleType(),c.getCost(),c.getAvailability()));
        }
        return allCUS;
    }

    @Override
    public boolean addVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicledao.add(new Vehicle(dto.getVehicleID(),dto.getRegNo(),dto.getYear(),dto.getVehicleName(),dto.getFuelType(),dto.getVehicleType(),dto.getCost(),dto.getAvailability()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicledao.update(new Vehicle(dto.getVehicleID(),dto.getCost(),dto.getAvailability()));
    }

    @Override
    public String getCurrentVehicleID() throws SQLException, ClassNotFoundException {
        return vehicledao.getCurrentID();
    }

    @Override
    public boolean deleteVehicle(String Vehicleid) throws SQLException, ClassNotFoundException {
        return vehicledao.delete(Vehicleid);
    }

    @Override
    public Vehicle searchVehicle(String Vehicleid) throws SQLException, ClassNotFoundException {
        return vehicledao.search(Vehicleid);
    }

    @Override
    public List<String> getAllAvailableVehicles() throws SQLException, ClassNotFoundException {
        return vehicledao.getAllAvailableVehicles();
    }

    @Override
    public List<VehicleDTO> SearchByVehicleName(String vehicleName) throws SQLException, ClassNotFoundException {
        List<VehicleDTO> allmat = new ArrayList<>();
        List<Vehicle> mat = vehicledao.SearchByVehicleName(vehicleName);

        for (Vehicle m : mat){
            allmat.add(new VehicleDTO(m.getRegNo(),m.getCost()));
        }
        return allmat;
    }


}
