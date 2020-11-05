<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>login</title>
</head>
<body>


<h1>Cost Manager</h1>
<br/>
<h3>Login Users:</h3>
<!-- when Login button is pressed reflection will be commited -->
<form action="http://localhost:8080/CostManager_war_exploded/controller/user/login">
    <!-- table container-->
        <table>
                <!-- text field for UserName--->
                <tr><td> UserName : </td><td><input type="text" name="username" ></td></tr>
                <!-- text field for Password--->
                <tr><td> Password : </td><td><input type="password" name="password" ></td></tr>
                <!-- button for user logging in--->
                <tr><td><input type="submit" name="login" value="Login" ></td>
                <!-- button for user SignUp--->
                <td><a href="http://localhost:8080/CostManager_war_exploded/views/signup.jsp">
                        <button type="button">SignUp</button></a></td>
                <!-- button for showing Add Item page--->
                <td><a href="http://localhost:8080/CostManager_war_exploded/controller/item/additem">
                        <button type="button" id="additembtn" name="additembtn" class="btn btn-primary" >Add Item</button></a></td></tr>

        </table>
</form>
<!-- gets message from the controller that transfered with the session if user Logged in successfully --->
<%
    String temp;
    String titleUserId = (String) request.getSession().getAttribute("check");
    if(titleUserId!=null)
        temp = titleUserId;
    else
        temp = "";
%>
<!-- message that will be displayed if user Logged in successfully --->
<%=temp%>

</body>
</html>
