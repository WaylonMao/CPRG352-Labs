<%-- 
    Document   : arithmeticcalculator
    Created on : 22-Sep-2022, 7:47:03 PM
    Author     : WL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arithmetic Calculator</title>
    </head>
    <body>
        <h1>Arithmetic Calculator</h1>
        <form action="arithmetic" method="POST">
            <div>
                <label>First: </label>
                <input type="text" name="first" value="${first}">
            </div>
            <div>
                <label>Last: </label>
                <input type="text" name="last" value="${last}">
            </div>
            <div>
                <input type="submit" name="operation" value="+">
                <input type="submit" name="operation" value="-">
                <input type="submit" name="operation" value="*">
                <input type="submit" name="operation" value="%">
            </div>
        </form>
        <div><label>Result: ${result}</label></div>
        <div><a href="age">Arithmetic Calculator</a></div>
    </body>
</html>
