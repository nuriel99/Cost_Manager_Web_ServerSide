<%@ page import="il.ac.hit.model.Item" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report</title>
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
<h2 style="text-align: center"> Report by month </h2>
<br/>
<!-- when Show button is pressed reflection will be commited -->
<form action="http://localhost:8080/CostManager_war_exploded/controller/item/report">
    <!-- label for choosing a month--->
    <label for="month">Choose a month:</label>
    <!-- drop down list for choosing a month--->
    <select name="month" id="month">
        <option value="01">January</option>
        <option value="02">February</option>
        <option value="03">March</option>
        <option value="04">April</option>
        <option value="05">May</option>
        <option value="06">June</option>
        <option value="07">July </option>
        <option value="08">August</option>
        <option value="09">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
    </select>
    <br><br>
    <!-- label for choosing a year--->
    <label for="year">Choose a year:</label>
    <!-- drop down list for choosing a year--->
    <select name="year" id="year">
        <option value="2020">2020</option>
        <option value="2019">2019</option>
        <option value="2018">2018</option>
        <option value="2017">2017</option>
        <option value="2016">2016</option>
        <option value="2015">2015</option>
        <option value="2014">2014</option>
        <option value="2013">2013</option>
        <option value="2012">2012</option>
        <option value="2011">2011</option>
        <option value="2010">2010</option>
    </select>
    <!-- button for showing detailed table according to selected parameters--->
    <input type="submit" value="Show">
    <!-- button for transfering to add item page --->
    <td><a href="http://localhost:8080/CostManager_war_exploded/views/additem.jsp">
        <button type="button">Back</button></a></td>
</form>
<!-- items from database shown in table -->
<table style="background: rgba(160,222,143,0.48)" style="border-bottom-width: thin" >
    <tr><th>User Id</th><th>ID</th><th>Type</th><th>Cost</th><th>Date</th></tr>
    <%

        List<Item> reportList = (List<Item>) request.getSession().getAttribute("report");
        if(reportList!=null)
        {
            for (Item lineItem: reportList)
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
