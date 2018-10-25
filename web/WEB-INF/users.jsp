<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <h1>Manage Users</h1>
        <form action="users" method="post">
            <h2>Users</h2>
            <table>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Delete</th>
                    <th>Edit</th>
                </tr>
                <c:forEach var="user" items="">
                    <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th><input type="submit" name="action" value="Delete"></th>
                    <th><input type="submit" name="action" value="Edit"></th>
                </tr>
                </c:forEach> 
            </table>
            <h2>Add User</h2>
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" value=""></td>
                </tr>
                <tr>
                    <td>First Name: </td>
                    <td><input type="text" name="firstName" value=""></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><input type="text" name="lastName" value=""></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="password" value=""></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value=""></td>
                </tr>
            </table>
            <input type="submit" name="action" value="Save">
        </form>
    </body>
</html>
