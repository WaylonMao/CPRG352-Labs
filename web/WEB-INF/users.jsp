<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>User Manage Page</title>
    <style type="text/css">
      h1,
      th {
        font-family: sans-serif;
        color: #333;
      }
    </style>
  </head>
  <body>
    <header><h1>User Manage Page</h1></header>
    <table>
      <tr>
        <th>Add User</th>
        <th>Manage Users</th>
        <th>Edit User</th>
      </tr>
      <tr>
        <td>
          <form method="post">
            <table>
              <tr>
                <td><input type="text" name="email" placeholder="Email" /></td>
              </tr>
              <tr>
                <td>
                  <input
                    type="checkbox"
                    name="active"
                    value="1"
                    checked
                  />Active
                </td>
              </tr>
              <tr>
                <td>
                  <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <input type="text" name="lastName" placeholder="Last Name" />
                </td>
              </tr>
              <tr>
                <td>
                  <input type="text" name="password" placeholder="Password" />
                </td>
              </tr>
              <tr>
                <td>
                  <select name="roleId">
                    <c:forEach items="${roles}" var="role">
                      <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                  </select>
                </td>
              </tr>
              <tr>
                <td>
                  <input type="hidden" name="action" value="add" />
                  <input type="submit" value="Add" />
                </td>
              </tr>
            </table>
          </form>
        </td>
        <td>
          <table>
            <tr>
              <th>Email</th>
              <th>Active</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Role</th>
              <th>Edit</th>
              <th>Delete</th>
            </tr>
            <tr>
              <c:forEach items="${users}" var="user">
                <tr>
                  <td>${user.email}</td>
                  <td>${user.active}</td>
                  <td>${user.firstName}</td>
                  <td>${user.lastName}</td>
                  <td>${user.roleName}</td>
                  <td>
                    <form method="post">
                      <input type="hidden" name="email" value="${user.email}" />
                      <input type="hidden" name="action" value="edit" />
                      <input type="submit" value="Edit" />
                    </form>
                  </td>
                  <td>
                    <form method="post">
                      <input type="hidden" name="email" value="${user.email}" />
                      <input type="hidden" name="action" value="delete" />
                      <input type="submit" value="Delete" />
                    </form>
                  </td>
                </tr>
              </c:forEach>
            </tr>
          </table>
        </td>
        <td>
          <form method="post">
            <table>
              <tr>
                <td>Email:</td>
                <td><c:if test="${editUser!=null}">${editUser.email}</c:if></td>
              </tr>
              <tr>
                <td>Active:</td>
                <td>
                  <c:if test="${editUser!=null}">
                    <c:choose>
                      <c:when test="${editUser.active == 1}">
                        <input
                          type="checkbox"
                          name="active"
                          value="1"
                          checked
                        />
                      </c:when>
                      <c:otherwise>
                        <input type="checkbox" name="active" value="1" />
                      </c:otherwise>
                    </c:choose>
                  </c:if>
                </td>
              </tr>
              <tr>
                <td>First Name:</td>
                <td>
                  <c:if test="${editUser!=null}">
                    <input
                      type="text"
                      name="firstName"
                      value="${editUser.firstName}"
                    />
                  </c:if>
                </td>
              </tr>
              <tr>
                <td>Last Name:</td>
                <td>
                  <c:if test="${editUser!=null}">
                    <input
                      type="text"
                      name="lastName"
                      value="${editUser.lastName}"
                    />
                  </c:if>
                </td>
              </tr>
              <tr>
                <td>Password:</td>
                <td>
                  <c:if test="${editUser!=null}">
                    <input
                      type="text"
                      name="password"
                      value="${editUser.password}"
                    />
                  </c:if>
                </td>
              </tr>
              <tr>
                <td>Role:</td>
                <td>
                  <c:if test="${editUser!=null}">
                    <select name="roleId">
                      <c:forEach items="${roles}" var="role">
                        <c:choose>
                          <c:when test="${role.id==editUser.role}">
                            <option value="${role.id}" selected>
                              ${role.name}
                            </option>
                          </c:when>
                          <c:otherwise>
                            <option value="${role.id}">${role.name}</option>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </select>
                  </c:if>
                </td>
              </tr>
              <tr>
                <td>
                  <input type="hidden" name="email" value="${editUser.email}" />
                  <input type="hidden" name="action" value="update" />
                  <input type="submit" value="Save" />
                </td>
                <td>
                  <input type="hidden" name="email" value="${editUser.email}" />
                  <input type="hidden" name="action" value="cancel" />
                  <input type="submit" value="Cancel" />
                </td>
              </tr>
            </table>
          </form>
        </td>
      </tr>
    </table>
    <div>
      <c:if test="${message !=null}"><h2>${message}</h2></c:if>
    </div>
  </body>
</html>
