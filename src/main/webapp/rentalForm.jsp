<%@ page import="com.pakoutfitters.pak_outfitters.models.EquipmentModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="/header.jsp"/>

<%
    String title = (String) request.getParameter("title");
    String price = (String) request.getParameter("price");
%>

<h2><%=title %></h2>
<p>$<%=price%></p>

<form action="rentEquipment" method="post" onsubmit="return validate()">
    <p>Enter the number of days you want to rent the equipment.</p>

    Number of days:<input type="text" id="rentalDays" name="rentalDays"><br />

    <input type="submit" value="Submit"><br />

    <p>Your rental price: $<span id="price_calculation"></span></p>

    <span id="error_msg"></span>
</form>


<c:import url="/footer.jsp"/>

<script>
    document
        .getElementById("rentalDays")
        .addEventListener('keyup', () => displayFeedback());

    const calculatePrice = () => {
        document.getElementById("error_msg").innerHTML = "";
        const days = document.getElementById("rentalDays").value;
        const itemPrice = '<%=price%>'

        document.getElementById("price_calculation").innerHTML = days * itemPrice;
    }

    const validate = () => {
        const days = +document.getElementById("rentalDays").value;

        const VALIDATED = true;

        if (!days || typeof(days) !== "number") {
            document.getElementById("error_msg").innerHTML = "Please enter a number.";
            return !VALIDATED
        }

        if (days > 14 || days <= 0) {
            document.getElementById("error_msg").innerHTML = "Rentals must be two weeks or less.";
            return !VALIDATED
        }
        return VALIDATED;
    }

    const displayFeedback = () => {
        const validInput = validate();

        validInput
            ? calculatePrice()
            : document.getElementById("price_calculation").innerHTML = "";
    }
</script>