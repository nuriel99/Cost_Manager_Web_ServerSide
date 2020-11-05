<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-Up</title>
</head>
<body>

<h1>                 Registration                   </h1>
<!-- when Ok button is pressed reflection will be commited -->
<form action="http://localhost:8080/CostManager_war_exploded/controller/user/signup" method="post">
    <table>
        <!-- text field for User Id--->
        <tr><td> User Id : </td><td><input type="number" name="userid" pattern="[0-9]+" max="1000000000" required/></td></tr>
        <!-- text field for User Name--->
        <tr><td> User Name : </td><td><input type="text" name="username" required/></td></tr>
        <!-- text field for Last Name--->
        <tr><td> Last Name : </td><td><input type="text" name="lastname" pattern="[A-Za-z]*" required/></td></tr>
        <!-- text field for Password--->
        <tr><td> Password : </td><td><input type="password" name="password" required/></td></tr>
        <!-- button for user Signing Up after filling all text field --->
        <tr><td><input type="submit" name="submit" value="Ok"/></td>
            <!-- href for transfering to login page --->
            <td><a href="http://localhost:8080/CostManager_war_exploded/controller/user/login">Back</a></td></tr>
    </table>
</form>
<!-- gets message from the controller that transfered with the session if user registered successfully --->
<%
    String temp;
    String titleUserId = (String) request.getSession().getAttribute("check1");
    if(titleUserId!=null)
        temp = titleUserId;
    else
        temp = "";
%>
<!-- message that will be displayed if user registered successfully --->
<%=temp%>

</body>
</html>
