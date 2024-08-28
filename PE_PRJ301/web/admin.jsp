<%-- 
    Document   : foodList
    Created on : 30-10-2022
    Author     : hd
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food List Page</title>
    </head>
    <body>
        <!--your code here-->
        Welcome ${sessionScope.USER.fullName}
        <form action="MainController" method="POST">
            <input type="submit" value="Logout" name="action" />
        </form>
        <form action="MainController" method="POST">           
            Search: <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="action"/> <br/>
        </form>


        <c:set var="searchP" value="${param.txtSearch}" />
        <c:if test="${not empty searchP}">
            <c:set var="searchV" value="${requestScope.SEARCHPAGE}" /> 
            <c:if test="${not empty searchV}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>UserID</th>
                            <th>FullName</th>
                            <th>RoleID</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="result" items="${searchV}" varStatus="counter" >
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>${result.userID}</td>
                                <td>${result.fullName}</td>
                                <td>${result.roleID}</td>
                                <td>${result.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
    </body>
</html>
