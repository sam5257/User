<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sameer.business.BusinessClass" %>
<%@ page import="com.sameer.model.UserInfo" %>
<html>
 <body>
 <table width=50% border=1>
 <caption>List Of User:</caption>
        <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Age</th>
        <th>Action</th>
        <th>Action</th>
        </tr>
         <%
                ArrayList<UserInfo> userInfoArrayList=(ArrayList<UserInfo>)request.getAttribute("userList");
                for(UserInfo info:userInfoArrayList){
                %>
                      <tr>
                          <td><%=info.getFirstName()%></td>
                          <td><%=info.getLastName()%></td>
                          <td><%=info.getEmail()%></td>
                          <td><%=info.getAge()%></td>
                          <td><a href="updatevalue?id=<%=info.getId()%>">Update</a></td>
                          <td><a href="delete?id=<%=info.getId()%>">Delete</a></td>


                      </tr>

                <%
                }
                %>
                </table>
                </body>
                </html>