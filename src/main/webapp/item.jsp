<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<c:import url="/header.jsp"/>

<h2 class="title"><c:out value="${item.title}"/></h2>

<p class="item-action-container">
    <c:if test="${item.available == true}">
        <a href="rentalForm.jsp?title=${item.title}&price=${item.price}">Rent Item</a>
    </c:if>

    <c:if test="${item.available == false}">
        <form action="returnForm.jsp">
            <input type="submit" value="Return item"/>
        </form>
        <span>* must be an administrator to return items</span>
    </c:if>
</p>

<c:choose>
    <c:when test="${item.type.equals('paddleboard')}">
        <img src="images/paddleboard.png" alt="equipment-image" class="pic" />
    </c:when>

    <c:when test="${item.type.equals('kayak')}">
        <img src="images/kayak.png" alt="equipment-image" class="pic" />
    </c:when>

    <c:otherwise>
        <img src="images/surf.png" alt="equipment-image" class="pic" />
    </c:otherwise>
</c:choose>

<p>Description: <c:out value="${item.description}"/> </p>

<p>Price: $<c:out value="${item.price}"/> </p>



<c:import url="/footer.jsp"/>
