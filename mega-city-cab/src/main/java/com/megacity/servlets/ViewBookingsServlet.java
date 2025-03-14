package com.megacity.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.utils.DBConnection;

/**
 * Servlet implementation class ViewBookingsServlet
 */
@WebServlet("/ViewBookingsServlet")
public class ViewBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String query = "SELECT BookingID, CarName, CarBrand, CarPrice, DateFrom, DateTo, "
                     + "PassengerCount, DriverNeeded, FullName, ContactNumber, Email, BookingDate "
                     + "FROM Booking ORDER BY BookingDate DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            List<Object[]> bookings = new ArrayList<>();

            while (rs.next()) {
                bookings.add(new Object[]{
                        rs.getInt("BookingID"),
                        rs.getString("CarName"),
                        rs.getString("CarBrand"),
                        rs.getDouble("CarPrice"),
                        rs.getDate("DateFrom").toString(),
                        rs.getDate("DateTo").toString(),
                        rs.getInt("PassengerCount"),
                        rs.getString("DriverNeeded"),
                        rs.getString("FullName"),
                        rs.getString("ContactNumber"),
                        rs.getString("Email"),
                        rs.getTimestamp("BookingDate").toString()
                });
            }

            String json = new Gson().toJson(bookings);
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
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
