package com.megacity.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.utils.DBConnection;

/**
 * Servlet implementation class BookCab
 */
@WebServlet("/BookCab")
public class BookCab extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCab() {
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
		int customerID = (int) request.getSession().getAttribute("customerID");
        String pickup = request.getParameter("pickup");
        String drop = request.getParameter("drop");

        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO Booking (CustomerID, PickupLocation, DropLocation, Status) VALUES (?, ?, ?, 'Pending')";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customerID);
            ps.setString(2, pickup);
            ps.setString(3, drop);
            ps.executeUpdate();

            response.sendRedirect("bookingSuccess.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
