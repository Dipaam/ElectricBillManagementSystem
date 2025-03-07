package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deactivateAccountServlet")
public class DeactivateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles POST requests (deactivate the account)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String consumerId = (String) session.getAttribute("consumerId");
        System.out.println(consumerId);
        if (consumerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "UPDATE Login SET status = 'Inactive' WHERE consumer_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, consumerId);
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    session.invalidate(); // Log the user out
                    response.sendRedirect("deactivationSuccess.jsp");
                } else {
                    response.sendRedirect("error.jsp?message=Deactivation failed");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error");
        }
    }
}

