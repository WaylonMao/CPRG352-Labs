<%-- Document : home Created on : 6-Oct-2022, 2:04:22 PM Author : WL --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Home Page</title>
  </head>
  <body>
    <h1>Hello Page!</h1>
    <h2>Hello ${user.username}.</h2>
    <br />
    <a href="login?logout">Log out</a>
  </body>
</html>
