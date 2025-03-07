package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PayBillServlet")
public class PayBillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billId = request.getParameter("billId");
        HttpSession session = request.getSession();
        String consumerId = (String) session.getAttribute("consumerId");

        // Debugging: Print values
        System.out.println("Received billId: " + billId);
        System.out.println("Session consumerId: " + consumerId);

        if (billId == null || consumerId == null) {
            response.getWriter().write("Invalid request parameters: Bill ID or Consumer ID is null.");
            return;
        }

        PreparedStatement stmt = null;

        try(Connection conn=DataBaseConnection.getConnection()) {

            String paymentId = UUID.randomUUID().toString();
            String sql = "UPDATE Bill SET payment_id=? , status = 'Paid' WHERE bill_id = ? AND customer_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            stmt.setString(2, billId);
            stmt.setString(3, consumerId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                response.getWriter().write("Payment successful. Payment ID: " + paymentId);
            } else {
                response.getWriter().write("Payment failed. Please try again.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

