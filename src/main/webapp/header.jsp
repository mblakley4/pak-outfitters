<%@ page import="com.pakoutfitters.pak_outfitters.models.AdminModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>PAK Outfitters</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>

<%
    if (request.getSession() != null) {
        if(session.getAttribute("user") != null) {
            AdminModel user = (AdminModel) session.getAttribute("user");
        %>

<%--Logged in header--%>
<header>
    <h1><%= "PAK Outfitters" %></h1>
    <nav>
        <ul>
            <li><a href="equipment">Available</a></li>
            <li><a href="rented">Rented</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>

<%
        } else {
        %>

<%--Logged out header--%>

<header>
    <h1><%= "PAK Outfitters" %></h1>
    <nav>
        <ul>
            <li><a href="equipment">Available</a></li>
            <li><a href="rented">Rented</a></li>
            <li><a href="adminLogin.jsp">Admin</a></li>
        </ul>
    </nav>
</header>

<%
        }
    }
    %>

<section class="main">