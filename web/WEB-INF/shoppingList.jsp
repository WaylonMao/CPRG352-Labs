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
        <c:if test="${items!=null&&items.size()!=0}">
            <div>
                <h2>Items</h2>            
                <form method="post">
                    <ul>
                        <c:forEach items="${showItems}" var="item" varStatus="index">
                            <li><input type="radio" name="radioItem" value="${index.index + PAGE_SIZE * (page - 1)}" />${item}</li>
                            </c:forEach>
                    </ul>
                    
                    <div>
                        <c:if test="${sumpage > 0}">
                            <div>
                                <p>Page: ${page} Total: ${sumpage}</p>
                            </div>
                            <c:if test="${page > 1}">
                                <a href="
                                   <c:url value='/'>
                                       <c:param name='page' value='${page-1}'/>
                                   </c:url>
                                   ">&lt;PREVIOUS</a>
                            </c:if>
                            <c:if test="${page < sumpage}">
                                <a href="
                                   <c:url value='/'>
                                       <c:param name='page' value='${page+1}'/>
                                   </c:url>
                                   ">NEXT&gt;</a>
                            </c:if>
                        </c:if>
                    </div>
                    <input type="hidden" name="action" value="delete" />
                    <input type="submit" value="Delete" />
                    <div>
                        <p>${message}</p>
                    </div>
                </form>
            </div>
        </c:if>
    </body>
</html>
