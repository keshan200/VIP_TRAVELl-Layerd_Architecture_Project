package org.example.viptravel.bo.custom.impl;

import net.sf.jasperreports.engine.util.JsonUtil;
import org.example.viptravel.bo.custom.DashBoardMiddleBO;
import org.example.viptravel.dao.DAOFactory;
import org.example.viptravel.dao.custom.DashBoardMidleDAO;
import org.example.viptravel.dto.BookingDetailsDTO;
import org.example.viptravel.dto.DashBoardDTO;
import org.example.viptravel.entity.BookingDetails;
import org.example.viptravel.entity.DashBoard;
import org.example.viptravel.view.tdm.DashboardTableTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardMiddleBOImpl implements DashBoardMiddleBO {


   DashBoardMidleDAO dashBoardMidleDAO = (DashBoardMidleDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.DashBoardMiddle);


    @Override
    public int getCustomerCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        return  dashBoardMidleDAO.getCustomerCount(dashBoardModle);
    }

    @Override
    public int getEmployeeCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        return dashBoardMidleDAO.getEmployeeCount(dashBoardModle);
    }

    @Override
    public int getcarCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        return dashBoardMidleDAO.getcarCount(dashBoardModle);
    }

    @Override
    public int getvanCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        return dashBoardMidleDAO.getvanCount(dashBoardModle);
    }

    @Override
    public int getsuvCount(DashBoard dashBoardModle) throws SQLException, ClassNotFoundException {
        return dashBoardMidleDAO.getsuvCount(dashBoardModle);
    }

    @Override
    public int getbookingCount(String reservations) throws SQLException, ClassNotFoundException {
        return dashBoardMidleDAO.getbookingCount(reservations);
    }

    @Override
    public List<DashboardTableTM> getVehicleStatistics() throws SQLException, ClassNotFoundException {
        List<DashboardTableTM> va =dashBoardMidleDAO.getVehicleStatistics();
        List<DashboardTableTM> n = new ArrayList<>();
        for (DashboardTableTM tab :n){
                va.add(new DashboardTableTM(tab.getVehicleName(),tab.getTotalAvailable(), tab.getCostPerDay()));

        }
       return va;

    }

    @Override
    public List<BookingDetails> getAllSalesByPaymentStatus() throws SQLException {
        List<BookingDetails> payment = dashBoardMidleDAO.getAllSalesByPaymentStatus();
        List<BookingDetails> entity = new ArrayList<>();

        for (BookingDetails book : entity){
            payment.add(new BookingDetails(book.getPaymentStatus(),book.getCount()));
        }
return payment;

    }
}
