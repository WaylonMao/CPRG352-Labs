<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <div>
            <p>Hello, ${username}!</p>
            <form name="welcome" method = "post">
                <input type="hidden" name="action" value="logout"/>
                <a href="javascript:document.welcome.submit()">Logout</a> 
            </form>

        </div>
        <div>
            <h2>List</h2>
            <form method="post">
                <label for="item">Add item: </label>
                <input type="text" name="item" id="item" required="required" />
                <input type="hidden" name="action" value="add" />
                <input type="submit" value="Add" />
            </form>
        </div>
        <div>
            <h2>Items</h2>
            <form method="post">
                <ul>
                    <c:forEach items="${items}" var="item" varStatus="index">
                        <li><input type="radio" name="radioItem" value="${index.index}" />${item}</li>
                        </c:forEach>
                </ul>
                <input type="hidden" name="action" value="delete" />
                <input type="submit" value="Delete" />
            </form>
        </div>
    </body>
</html>
