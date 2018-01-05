package com.accolite.servlet_exercise2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_Exercise2
 */
public class Servlet_Exercise2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con=null;
    public Servlet_Exercise2() {
        super();
        try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/au","root","root");  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Statement stmt;
		try {
			stmt = con.createStatement();
			String id=request.getParameter("id");
			ResultSet rs=stmt.executeQuery("select * from servlet_streams_exercises where id='"+id+"'");
			if(rs.next()) {
				//System.out.println(rs.getString("name")+rs.getString("age"));
				request.setAttribute("name", rs.getString("name"));
				request.setAttribute("age", rs.getString("age"));
			}
			request.getRequestDispatcher("servlet_Exercise21").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
