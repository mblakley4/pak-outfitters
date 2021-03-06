package com.pakoutfitters.pak_outfitters.servlets;

import com.pakoutfitters.pak_outfitters.models.EquipmentModel;
import com.pakoutfitters.pak_outfitters.services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetItemServlet", value = "/item")
public class GetItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int equipment_id = Integer.parseInt(request.getParameter("id"));
        int rental_id = 99999;

        if (request.getParameter("rentalId") != null) {
            rental_id = Integer.parseInt(request.getParameter("rentalId"));
        }


        MySQLdb db = MySQLdb.getInstance();

        try {
            EquipmentModel item = db.getItem(equipment_id);
            request.setAttribute("item", item);
            if (rental_id != 99999) {
                request.setAttribute("rental_id", rental_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("item.jsp");
        requestDispatcher.forward(request, response);
    }
}
