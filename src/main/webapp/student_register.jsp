<%@ page import="java.sql.*"%>

<%
Connection con = null;
PreparedStatement ps = null;
Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/college";
String username = "root";
String password = "root";
con = DriverManager.getConnection(url, username, password);
String firstName = request.getParameter("firstName");
String mobileNoStr = request.getParameter("mobileNo");
int mobileNo = Integer.parseInt(mobileNoStr);
String lastName = request.getParameter("lastName");
String fatherName = request.getParameter("fatherName");
String course = request.getParameter("course");
String emailAddress = request.getParameter("emailAddress");
String postelAddress = request.getParameter("postelAddress");
ps = con.prepareStatement(
		"insert into student(firstName,lastName,fatherName,course,emailAddress,mobileNo,postelAddress) values(?,?,?,?,?,?,?)");
ps.setInt(6, mobileNo);
ps.setString(1, firstName);
ps.setString(2, lastName);
ps.setString(3, fatherName);
ps.setString(4, course);
ps.setString(5, emailAddress);
ps.setString(7, postelAddress);

int x = ps.executeUpdate();
if (x != 0)

%>
<html><body bgcolor="yellow"><h2 align="center"><%=firstName %> <%=lastName %> Registered successfully<h2></body></html>
