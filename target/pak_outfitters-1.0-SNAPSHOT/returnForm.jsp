<%@ page import="com.pakoutfitters.pak_outfitters.models.AdminModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="/header.jsp"/>

<%
    if (request.getSession() != null) {
        if(session.getAttribute("user") != null) {
            AdminModel user = (AdminModel) session.getAttribute("user");

            int rentalId =  Integer.parseInt(request.getParameter("id"));
            String title = (String) request.getParameter("title");
            %>

<h2><%=title %></h2>

<form action="returnEquipment?id=<%=rentalId%>" method="post">
    <p>Return item to inventory</p>
    <input type="submit" value="Submit"><br />
</form>

<%
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("status.jsp");
            request.setAttribute("message", "You must be logged in as an administrator to return items.");
            requestDispatcher.forward(request, response);
        }
    }
%>

<c:import url="/footer.jsp"/>