package com.pakoutfitters.pak_outfitters.servlets;

import com.pakoutfitters.pak_outfitters.services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ReturnEquipmentServlet", value = "/returnEquipment")
public class ReturnEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rentalId = Integer.parseInt(request.getParameter("id"));

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateReturned = date.format(formatter);

        MySQLdb db = MySQLdb.getInstance();

        try {
            boolean result = db.returnItem(rentalId, dateReturned);

            if (result) {
                request.setAttribute("message", "Your return was succesful!");
            } else {
                request.setAttribute("message", "Something went wrong! Server error!");
            }

            request.getRequestDispatcher("status.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
