package com.pakoutfitters.pak_outfitters.servlets;

import com.pakoutfitters.pak_outfitters.models.RentedEquipmentModel;
import com.pakoutfitters.pak_outfitters.services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetRentedEquipmentServlet", value = "/rented")
public class GetRentedEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLdb db = MySQLdb.getInstance();

        try {
            List<RentedEquipmentModel> rentedEquipmentList = db.getRentedEquipment();
            request.setAttribute("rented_equipment_list", rentedEquipmentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("rentedEquipment.jsp");
        requestDispatcher.forward(request, response);
    }
}
