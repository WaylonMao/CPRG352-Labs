<%-- Document : login Created on : 6-Oct-2022, 2:04:31 PM Author : WL --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
  </head>
  <body>
    <h1>Login</h1>
    <div>
      <form action="login?login" method="post">
        <label for="username">Username: </label>
        <input type="text" name="username" id="username" value="${username}" />
        <br />
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" value="${password}" />
        <br />
        <input type="submit" value="Log in" />
      </form>
      <div>
        <p>${message}</p>
      </div>
    </div>
  </body>
</html>
