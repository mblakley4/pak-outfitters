package com.pakoutfitters.pak_outfitters.servlets;

import com.pakoutfitters.pak_outfitters.models.AdminModel;
import com.pakoutfitters.pak_outfitters.services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminLoginServlet", value = "/login")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MySQLdb db = MySQLdb.getInstance();
        AdminModel adminModel = null;

        try {
            adminModel = db.login(username, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if (adminModel != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", adminModel);

           request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Incorrect username or password..!!!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
