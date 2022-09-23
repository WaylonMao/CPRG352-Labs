<%-- 
    Document   : agecalculator.jsp
    Created on : 22-Sep-2022, 2:00:02 PM
    Author     : WL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Age Calculator</title>
    </head>
    <body>
        <h1>Age Calculator</h1>
            <form action="age" method="POST">
            <div>
                <label>Enter your age: </label>
                <input type="text" name="age" value="${age}">
            </div>
            <div>
                <input type="submit" value="Age next birthday">
            </div>
            <p>${nextage}</p>
        </form>
        <div><a href="arithmetic">Arithmetic Calculator</a></div>
    </body>
</html>
