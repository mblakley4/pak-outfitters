<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:import url="/header.jsp"/>

<div class="cards-container">
<c:forEach var="item" items="${equipment_list}">
        <div class="card">
            <h2 class="title">${item.getTitle()}</h2>
            <img src="images/${item.getType()}.png" alt="equipment-image" class="pic" />
            <p class="description">${item.getDescription()}</p>
            <p class="price">$${item.getPrice()}</p>
        </div>
</c:forEach>
</div>

<%--<p>${message}</p>--%>

<%--<%--%>
<%--    } else {--%>
<%--        RequestDispatcher requestDispatcher = request.getRequestDispatcher("availableEquipment.jsp");--%>
<%--        request.setAttribute("error", "No session!!");--%>
<%--        requestDispatcher.forward(request, response);--%>
<%--    }--%>
<%--%>--%>


<c:import url="/footer.jsp"/>