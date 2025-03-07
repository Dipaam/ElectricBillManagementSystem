package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BillHistoryServlet")
public class BillHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("consumerId") == null) {
            response.sendRedirect("login.jsp"); // Redirect if session expired
            return;
        }

        String consumerId = (String) session.getAttribute("consumerId");
        List<String[]> billHistory = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn=DataBaseConnection.getConnection()){

            // 🔹 Fetch Bill History
            String sql = "SELECT bill_id, amount, due_date, status FROM bill WHERE customer_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, consumerId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String[] bill = new String[5];
                bill[0] = rs.getString("bill_id");    // Bill ID
                bill[1] = String.valueOf(rs.getDouble("amount")); // Amount
                bill[2] = rs.getString("due_date");   // Due Date
                bill[3] = rs.getString("status");     // Status (Paid/Pending)
                //bill[4] = rs.getString("payment_id"); // Payment ID (If paid)
                billHistory.add(bill);
            }

            // Store bill history in request attribute
            request.setAttribute("billHistory", billHistory);
            request.getRequestDispatcher("billHistory.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
