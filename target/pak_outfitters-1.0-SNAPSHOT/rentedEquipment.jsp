<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:import url="/header.jsp"/>

<div class="cards-container">
    <c:forEach var="item" items="${rented_equipment_list}">
        <div class="card">
            <h2 class="title">${item.getTitle()}</h2>
            <img src="images/${item.getType()}.png" alt="equipment-image" class="pic" />
            <p class="description">${item.getDescription()}</p>
            <p class="return-date">Return Date: ${item.getReturn_date()}</p>
        </div>
    </c:forEach>
</div>


<c:import url="/footer.jsp"/>
