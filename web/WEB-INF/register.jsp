<%-- Document : register Created on : 13-Oct-2022, 10:51:59 PM Author : WL --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Shopping List Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <div>
            <form method="post">
                <input type="text" name="username" required="required" onkeyup="this.value=this.value.replace(/[^\w+$]/g,'')" />
                <input type="hidden" name="action" value="register" />
                <input type="submit" value="Register name" />
            </form>
        </div>
    </body>
</html>
