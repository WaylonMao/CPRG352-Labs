<%-- Document : viewnote.jsp Created on : 29-Sep-2022, 1:32:41 PM Author : WL
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>View Note</title>
  </head>
  <body>
    <h1>Simple Note Keeper</h1>
    <h2>View Note</h2>
    <div>
      <p><b>Title: </b>${note.title}</p>
      <p><b>Contents: </b></p><p>${note.contents}</p>
    </div>
    <p><a href="note?edit">Edit</a></p>
  </body>
</html>
