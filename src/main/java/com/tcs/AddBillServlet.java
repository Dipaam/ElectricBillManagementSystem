package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddBillServlet")
public class AddBillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consumerId = request.getParameter("consumerId");
        String amount = request.getParameter("amount");
        String dueDate = request.getParameter("dueDate");

        if (consumerId == null || amount == null || dueDate == null || consumerId.isEmpty() || amount.isEmpty() || dueDate.isEmpty()) {
            response.getWriter().write("Invalid input parameters. Please fill all fields.");
            return;
        }

        PreparedStatement stmt = null;

        try(Connection conn=DataBaseConnection.getConnection()) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String billId = UUID.randomUUID().toString();
            String sql = "INSERT INTO Bill (bill_id, customer_id, amount, due_date, status) VALUES (?, ?, ?, ?, 'Pending')";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, billId);
            stmt.setString(2, consumerId);
            stmt.setDouble(3, Double.parseDouble(amount));
            stmt.setString(4, dueDate);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().write("Bill added successfully. Bill ID: " + billId);
            } else {
                response.getWriter().write("Failed to add bill.");
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
