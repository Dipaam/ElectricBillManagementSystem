package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RestoreAccountServlet")
public class RestoreAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "UPDATE Login SET status = 'Active' WHERE email = ? AND status = 'Inactive'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                request.setAttribute("message", "Account restored successfully! You can now log in.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "Invalid email or account is already active.");
                RequestDispatcher rd = request.getRequestDispatcher("restoreAccount.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            RequestDispatcher rd = request.getRequestDispatcher("restoreAccount.jsp");
            rd.forward(request, response);
        }
    }
}
