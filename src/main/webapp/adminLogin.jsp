<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="/header.jsp"/>

<form action="login" method="post" onsubmit="return validate()">
    Username: <input id="username" name="username" type="text" /> <br/>
    Password: <input id="password" name="password" type="password" /> <br/>
    <input type="submit" name="Login" /> <br/> </br>
    <span id="error_msg"></span>
</form>

<p>${error}</p>

<c:import url="/footer.jsp"/>


<script>
    const validate = () => {
        let result = true
        var username = document.getElementById("username").value
        var password = document.getElementById("password").value

        if(username === "") {
            document.getElementById("error_msg").innerHTML = "Please enter a username."
            result = false;
        } else if (password === "") {
            document.getElementById("error_msg").innerHTML = "Please enter a password."
            result = false
        }

        return result
    }
</script>