<%-- 
    Document   : notes
    Created on : 31-Oct-2018, 3:23:20 PM
    Author     : 763198
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <form action="notes" method="post">
            <table>
                <tr>
                    <th>Note ID</th>
                    <th>Date Created</th>
                    <th>Contents</th>
                </tr>
                <c:forEach items="${notes}" var="note" varStatus="status">
                    <tr>
                        <td><c:out value="${note.noteid}"></c:out></td>
                        <td><c:out value="${note.dateCreated}"></c:out></td>
                        <td><c:out value="${note.contents}"></c:out></td>
                        <td><button type="submit" name="action" value="Delete${note.noteid}">Delete</button></td>
                        <td><button type="submit" name="action" value="Edit${note.noteid}">Edit</button></td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${empty editnote}"><h2>Add Note</h2></c:if>
            <c:if test="${not empty editnote}"><h2>Edit Note ${editnote.noteid}</h2></c:if>
            <textarea rows="5" cols="50" name="textarea"><c:if test="${not empty editnote}"><c:out value="${editnote.contents}"></c:out></c:if></textarea>
                <br>
            <c:if test="${empty editnote}">
                <button type="submit" name="action" value="Add">Add</button>
            </c:if>
            <c:if test="${not empty editnote}">
                <button type="submit" name="action" value="Save${editnote.noteid}">Save</button>
            </c:if>
        </form>
    </body>
</html>
