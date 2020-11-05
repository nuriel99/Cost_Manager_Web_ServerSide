<%@ page import="il.ac.hit.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>AddItem</title>
    <!-- Styles -->
    <style>
        table {
            width: 50%;
        }

        th, td {
            text-align: center;
            padding: 8px;
        }

        tr:nth-child(even) {background-color: #f2f2f2;}
    </style>
</head>
<body>
<!-- get UserID from this session-->
<%
      int titleUserId = (Integer) request.getSession().getAttribute("userid");
%>
<h1 style="text-align: center">Cost Manager</h1>
<br/>
<h3>My ID is : (<%= titleUserId %>) </h3>
<!-- when Add Item button is pressed reflection will be commited -->
<form action="http://localhost:8080/CostManager_war_exploded/controller/item/additem" method="post">
    <table >
        <!-- text field for item id--->
        <tr><td> ID : </td><td><input type="number" name="id"  pattern="[0-9]+" max="1000000000" required/></td></tr>
        <!-- text field for item cost--->
        <tr><td> Cost : </td><td><input type="number" name="cost" pattern="[0-9]+" max="1000000000" required/></td></tr>
        <!-- text field for item date--->
        <tr><td> Date : </td><td><input type="date" id="date" name="date"
                                        value="2020-01-01"
                                        min="2010-01-01" max="2020-12-31" required/></td></tr>
        <!-- drop down list for category--->
        <tr><td> Type : </td><td>
            <select name="type" id="type">
                <option value="food">Food</option>
                <option value="clothes">Clothes</option>
                <option value="car">Car</option>
                <option value="mortgage">Mortgage</option>
                <option value="restaurant">Restaurant</option>
                <option value="electricity">Electricity</option>
                <option value="water">Water</option>
                <option value="phone">Phone</option>
                <option value="school">School</option>
                <option value="propertytax">Property Tax</option>
            </select>
        </td></tr>
        <!-- button for adding item to database shown in table--->
        <tr><td><input type="submit" name="submit" value="Add Item"/></td>
            <!-- button for showing monthly report page--->
            <td><a href="http://localhost:8080/CostManager_war_exploded/views/report.jsp">
                <button type="button">Report</button></a></td>
            <!-- button for user logging out--->
            <td><a href="http://localhost:8080/CostManager_war_exploded/controller/user/logout">
                <button type="button">Log Out</button></a></td></tr>
    </table>
</form>

<!-- items from database shown in table -->
<table style="background: rgba(143,199,222,0.48)" style="border-bottom-width: thin" >
    <tr><th>User Id</th><th>ID</th><th>Type</th><th>Cost</th><th>Date</th></tr>
    <%
        List<Item> userItemList = (List<Item>) request.getSession().getAttribute("tableitems");
        if(userItemList!=null)
        {
        for (Item lineItem: userItemList)
         {
            int userId = lineItem.getUserId();
            int id = lineItem.getId();
            String type = lineItem.getType();
            int cost = lineItem.getCost();
            String date = lineItem.getDate();
    %>
    <tr><td><%= userId %></td>
        <td><%= id %></td>
        <td><%= type %></td>
        <td><%= cost %></td>
        <td><%= date %></td>

    </tr>
    <%
         }
        }
    %>
</table> <!-- end of table -->
</body>

</html>
