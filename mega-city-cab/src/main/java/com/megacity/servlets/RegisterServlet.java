package com.megacity.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.utils.DBConnection;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String nic = request.getParameter("nic");
        
        try (Connection con = DBConnection.getConnection()) { // Using DBConnection utility

        	// Check if the username already exists
        	String checkQuery = "SELECT * FROM [User] WHERE Username = ?";
        	try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
        		checkStmt.setString(1, username);
        		ResultSet rs = checkStmt.executeQuery();

        		if (rs.next()) {
        			request.setAttribute("errorMessage", "Username already taken. Choose a different one.");
        			request.getRequestDispatcher("register.jsp").forward(request, response);
        			return;
        		}
        	}

        	// Insert new user into the database
        	String insertQuery = "INSERT INTO [User] (Name, Username, PasswordHash, Email, PhoneNumber, NIC, IsAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
        		stmt.setString(1, fullName);
        		stmt.setString(2, username);
        		stmt.setString(3, password);
        		stmt.setString(4, email);
        		stmt.setString(5, phone);
        		stmt.setString(6, nic);
        		stmt.setInt(7, 0);

        		int rowsInserted = stmt.executeUpdate();
        		if (rowsInserted > 0) {
        			response.sendRedirect("login.jsp?success=true");
        		} else {
        			request.setAttribute("errorMessage", "Registration failed. Try again.");
        			request.getRequestDispatcher("register.jsp").forward(request, response);
        		}
        	}

        } catch (Exception e) {
        	e.printStackTrace();
        	request.setAttribute("errorMessage", "Database connection error.");
        	request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
	}

}
