<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>User Manage Page</title>
        <style>
            <%@include file="/WEB-INF/style.css"%>
        </style>
    </head>
    <body>
        <table class="main_table">
            <tr>
                <td></td>
                <td><h1>User Manage Page</h1></td>
                <td></td>
            </tr>
            <tr>
                <th>Add User</th>
                <th>Manage Users</th>
                <th><c:if test="${editUser!=null}">Edit User</c:if></th>
                </tr>
                <tr>
                    <td>
                        <form method="post">
                            <table class="side_table">
                                <tr>
                                    <td><input type="text" name="email" placeholder="Email" /></td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" name="active" value="1" checked />Active</td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="text" name="firstName" placeholder="First Name" />
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
                                            <option value="${role.roleId}">${role.roleName}</option>
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
                    <table class="side_table">
                        <tr>
                            <th>Email</th>
                            <th>Active</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Role</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.email}</td>
                                <td><c:if test="${user.active==true}">Actived</c:if></td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.role.roleName}</td>
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
                    </table>
                </td>
                <td>
                    <c:if test="${editUser!=null}">                        
                        <table class="side_table">
                            <form method="post">
                                <tr>
                                    <td>Email:</td>
                                    <td>${editUser.email}</td>
                                </tr>
                                <tr>
                                    <td>Active:</td>
                                    <td><c:choose>
                                            <c:when test="${editUser.active == true}">
                                                <input type="checkbox" name="active" value="1" checked />
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" name="active" value="1" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>First Name:</td>
                                    <td><input type="text" name="firstName" value="${editUser.firstName}" /></td>
                                </tr>
                                <tr>
                                    <td>Last Name:</td>
                                    <td><input type="text" name="lastName" value="${editUser.lastName}" /></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type="text" name="password" value="${editUser.password}" /></td>
                                </tr>
                                <tr>
                                    <td>Role:</td>
                                    <td><select name="roleId">
                                            <c:forEach items="${roles}" var="role">
                                                <c:choose>
                                                    <c:when test="${role.roleId==editUser.role.roleId}">
                                                        <option value="${role.roleId}" selected>
                                                            ${role.roleName}
                                                        </option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${role.roleId}">${role.roleName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" name="email" value="${editUser.email}" />
                                        <input type="hidden" name="action" value="update" />
                                        <input type="submit" value="Save" />
                                    </td>
                                    <td></td>
                                </tr>
                            </form>
                            <tr>
                                <td>
                                    <form method="post">
                                        <input type="hidden" name="action" value="cancel" />
                                        <input type="submit" value="Cancel" />
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </td>
            </tr>
        </table>
        <div class="message">
            <c:if test="${message !=null}"><h2>${message}</h2></c:if>
        </div>
    </body>
</html>
