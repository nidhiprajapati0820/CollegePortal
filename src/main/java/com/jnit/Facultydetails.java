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

public class Facultydetails extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;

	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/college";
		String username = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String qulification = request.getParameter("qulification");
		String experience_year = request.getParameter("experience_year");
		String publication = request.getParameter("publication");

		PrintWriter pw = response.getWriter();
		try {
			ps = con.prepareStatement(
					"insert into faculty(firstName,lastName,address,qulification,experience_year,publication)values(?,?,?,?,?,?)");
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, address);
			ps.setString(4, qulification);
			ps.setString(5, experience_year);
			ps.setString(6, publication);
			
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
