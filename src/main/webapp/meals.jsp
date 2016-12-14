<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals Table</title>

    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
</head>
<body>
<center>
<h1>Meals</h1>
<table>

   <tr>
       <th width="120"><b>Date</b></th>
       <th width="120"><b>Description</b></th>
       <th width="120"><b>Calories</b></th>
       <th width="60"><b>Delete</b></th>
   </tr>
    <tr>
    <c:forEach var="meals" items="${meals}">
        <td>${meals.dateTime}</td>
        <td>${meals.description}</td>
        <td>${meals.calories}</td>
        <td>${meals.exceed}</td>
    </tr>
    </c:forEach>
</table>
</center>
 </body>
</html>