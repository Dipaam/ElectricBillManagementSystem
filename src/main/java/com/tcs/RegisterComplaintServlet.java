package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegisterComplaintServlet")
public class RegisterComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve consumer ID from session
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("consumerId") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String consumerId = (String) session.getAttribute("consumerId");

		// Retrieve form parameters
		String complaintType = request.getParameter("complaintType");
		String category = request.getParameter("category");
		String description = request.getParameter("description");

		if (complaintType == null || complaintType.isEmpty() || description == null || description.isEmpty()) {
			response.getWriter().write("Invalid input! Complaint Type and Description are required.");
			return;
		}

		PreparedStatement stmt = null;

		try (Connection conn = DataBaseConnection.getConnection()) {

			// Insert complaint into the database
			String sql = "INSERT INTO complaint (consumer_id, complaint_type, category, description, status) VALUES (?, ?, ?, ?, 'Open')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, consumerId);
			stmt.setString(2, complaintType);
			stmt.setString(3, category);
			stmt.setString(4, description);

			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted > 0) {
				response.sendRedirect("complaintSuccess.jsp");
			} else {
				response.getWriter().write("Error: Complaint registration failed.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.getWriter().write("Database error: " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
