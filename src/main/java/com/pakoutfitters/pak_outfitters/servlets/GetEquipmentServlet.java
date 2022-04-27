package com.pakoutfitters.pak_outfitters.servlets;

import com.pakoutfitters.pak_outfitters.models.EquipmentModel;
import com.pakoutfitters.pak_outfitters.services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetEquipmentServlet", value = "/equipment")
public class GetEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();

        if(session != null) {
            try {
                List<EquipmentModel> equipmentModelList = db.getEquipment();
                request.setAttribute("equipment_list", equipmentModelList);
            } catch (SQLException e) {
                System.out.println("Error Code = " + e.getErrorCode());
                System.out.println("SQL state = " + e.getSQLState());
                System.out.println("Message = " + e.getMessage());
                System.out.println("printTrace /n");
                e.printStackTrace();
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("availableEquipment.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
