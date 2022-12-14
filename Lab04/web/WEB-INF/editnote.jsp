<%-- Document : editnote.jsp Created on : 29-Sep-2022, 1:33:55 PM Author : WL
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Simple Note Keeper</title>
  </head>
  <body>
    <h1>Simple Note Keeper</h1>
    <h2>Edit Note</h2>
    <div>
      <form action="note" method="post">
        <table>
          <tr>
            <td>
              <label for="title"><b>Title: </b></label>
            </td>
            <td>
              <input type="text" name="title" id="title" value="${note.title}"/>
            </td>
          </tr>
          <tr>
            <td>
              <label for="contents"><b>Contents: </b></label>
            </td>
            <td>
              <textarea name="contents" id="contents" cols="30" rows="10">${note.contents}</textarea>
            </td>
          </tr>
        </table>
        <input type="submit" value="SAVE" />
      </form>
    </div>
  </body>
</html>
