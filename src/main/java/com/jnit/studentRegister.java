package com.jnit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class studentRegister extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;

	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/college";
		String username = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstName = request.getParameter("firstName");
		String mobileNoStr = request.getParameter("mobileNo");
		int mobileNo = Integer.parseInt(mobileNoStr);
		String lastName = request.getParameter("lastName");
		String fatherName = request.getParameter("fatherName");
		String course = request.getParameter("course");
		String emailAddress = request.getParameter("emailAddress");
		String postelAddress = request.getParameter("postelAddress");

		PrintWriter pw = response.getWriter();
		try {
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
				pw.println("<html><body bgcolor = 'yellow'><h1>Registered successfully</h1><br><a href='./index.html'>Go Home</a></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			pw.println("<html><body bgcolor = 'red'><h1>Error</h1><br><a href='./index.html'>Go Home</a></body></html>");
			e.printStackTrace();

		}

	}
}
