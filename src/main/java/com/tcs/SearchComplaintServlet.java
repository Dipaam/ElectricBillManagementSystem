package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchComplaintServlet")
public class SearchComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve complaint ID from request
        String complaintId = request.getParameter("complaintId");

        if (complaintId == null || complaintId.trim().isEmpty()) {
            response.getWriter().write("Error: Complaint ID is required.");
            return;
        }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try(Connection conn=DataBaseConnection.getConnection()){

            // Query to fetch complaint details
            String sql = "SELECT * FROM complaint WHERE complaint_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(complaintId));

            rs = stmt.executeQuery();

            if (rs.next()) {
                // Fetching complaint details

                request.setAttribute("complaintId", rs.getInt("complaint_id"));
                request.setAttribute("consumerId", rs.getString("consumer_id"));
                request.setAttribute("complaintType", rs.getString("complaint_type"));
                request.setAttribute("category", rs.getString("category"));
                request.setAttribute("description", rs.getString("description"));
                request.setAttribute("status", rs.getString("status"));

                // Forward to JSP for display
                RequestDispatcher dispatcher = request.getRequestDispatcher("complaintDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().write("Error: No complaint found with the given ID.");
            }

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
