package com.megacity.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.utils.DBConnection;

/**
 * Servlet implementation class ConfirmBookingServlet
 */
@WebServlet("/ConfirmBookingServlet")
public class ConfirmBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmBookingServlet() {
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
		try (Connection con = DBConnection.getConnection()) {

            String carName = request.getParameter("carName");
            String carBrand = request.getParameter("carBrand");
            double carPrice = Double.parseDouble(request.getParameter("carPrice"));
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            int passengerCount = Integer.parseInt(request.getParameter("passengerCount"));
            String driverNeeded = request.getParameter("driverNeeded");
            String fullName = request.getParameter("fullName");
            String contactNumber = request.getParameter("contactNumber");
            String email = request.getParameter("email");

            // Insert booking into database
            String sql = "INSERT INTO Booking (CarName, CarBrand, CarPrice, DateFrom, DateTo, PassengerCount, DriverNeeded, FullName, ContactNumber, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, carName);
            stmt.setString(2, carBrand);
            stmt.setDouble(3, carPrice);
            stmt.setString(4, dateFrom);
            stmt.setString(5, dateTo);
            stmt.setInt(6, passengerCount);
            stmt.setString(7, driverNeeded);
            stmt.setString(8, fullName);
            stmt.setString(9, contactNumber);
            stmt.setString(10, email);

            stmt.executeUpdate();
            
            // Redirect to payment page with details
            response.sendRedirect("payment.jsp?carName=" + carName + "&carBrand=" + carBrand + "&carPrice=" + carPrice + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "&passengerCount=" + passengerCount + "&driverNeeded=" + driverNeeded + "&fullName=" + fullName + "&contactNumber=" + contactNumber + "&email=" + email);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}

}
