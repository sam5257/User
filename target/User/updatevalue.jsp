<%@ page import="com.sameer.business.BusinessClass" %>
<%@ page import="com.sameer.model.UserInfo" %>
<html>
<head>
    <title>Update Data</title>
</head>
<body>
<%
        UserInfo userInfo=(UserInfo)request.getAttribute("user");
        %>
<form action="updatenewdata" method="post">
<input type="hidden" name="id" value="<%=userInfo.getId()%>">
    <p>First Name:</p>
    <!-- Create an element with mandatory name attribute,
    so that data can be transfer to the servlet using getParameter() -->

    <input type="text" name="fname" value="<%=userInfo.getFirstName()%>">
    <br/>
    <p>Last Name:</p>
    <input type="text" name="lname" value="<%=userInfo.getLastName()%>">
    <br/>
    <p>Email:</p>
    <input type="email" name="email" value="<%=userInfo.getEmail()%>">
    <br/><br/>
    <p>Date Of Birth:</p>
    <input type="date" name="dob" value="<%=userInfo.getDate()%>">
    <br/><br/><br/>
    <input type="Submit"/>
</form>
</body>
</html>