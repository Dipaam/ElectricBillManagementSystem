package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String consumerId = (String) session.getAttribute("consumerId");

        if (consumerId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "UPDATE customer SET customer_name=?, email=?, mobile_number=? WHERE consumer_id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setString(4, consumerId);

                int updatedRows = stmt.executeUpdate();
                
                String updateLoginSQL = "UPDATE login SET email=? WHERE consumer_id=?";
                PreparedStatement loginStmt = conn.prepareStatement(updateLoginSQL);
                loginStmt.setString(1, email);
                loginStmt.setString(2, consumerId);
                int loginUpdated = loginStmt.executeUpdate();
                if (updatedRows > 0 && loginUpdated>0) {
                    session.setAttribute("message", "Details updated successfully!");
                } else {
                    session.setAttribute("errorMessage", "Failed to update details.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error updating details.");
        }

        response.sendRedirect("updateSuccess.jsp");
    }
}
