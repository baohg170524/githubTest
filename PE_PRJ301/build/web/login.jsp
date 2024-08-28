<%-- 
    Document   : login
    Created on : 30-10-2022
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>Login information</h1>
        <!--tạo form để truyền về MainController-->
        <form action="MainController" method="POST">
            UserID: <input type="text" name="userid" value="" /><br/>
            Password: <input type="password" name="password" value="" /> <br/>
            <input type="submit" value="Login" name="action"/>
            
            
            <c:set var="invalid" value="${requestScope.INCORRECT}"/>
            <c:if test="${not empty invalid}">
                ${invalid.incorrect}
                
            </c:if>
            
        </form>
    </body>
    
</html>

