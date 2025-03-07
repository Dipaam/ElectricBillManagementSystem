package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewBillsServlet")
public class ViewBillsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> bills = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		String consumerId = (String) session.getAttribute("consumerId");
		System.out.println(consumerId);
		try (Connection conn = DataBaseConnection.getConnection()) {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String sql = "SELECT bill_id, amount, due_date, status FROM bill WHERE customer_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, consumerId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				bills.add("Bill ID: " + rs.getString("bill_id") + ", Amount: " + rs.getString("amount") + ", Due Date: "
						+ rs.getString("due_date") + ", Status: " + rs.getString("status"));
			}

			request.setAttribute("bills", bills);
			request.getRequestDispatcher("viewBills.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Database error: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
