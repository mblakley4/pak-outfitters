<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:import url="/header.jsp"/>

<h2>Out for Rental</h2>

<div class="cards-container">
    <c:forEach var="rental" items="${rented_equipment_list}">
        <a href="<c:url value='item?id=${rental.getEquipment_id()}&rentalId=${rental.getRental_id()}' />">
            <div class="card">
                <h2 class="title">${rental.getTitle()}</h2>
                <img src="images/${rental.getType()}.png" alt="equipment-image" class="pic" />
                <p class="description">${rental.getDescription()}</p>
                <p class="return-date">Return Date: ${rental.getReturn_date()}</p>
            </div>
        </a>
    </c:forEach>
</div>


<c:import url="/footer.jsp"/>
