<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
    requestDispatcher.forward(request, response);
%>
