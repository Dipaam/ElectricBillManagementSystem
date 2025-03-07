package com.tcs;

import java.io.IOException;
import java.sql.Connection;
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

@WebServlet("/ViewComplaintHistoryServlet")
public class ComplaintHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String consumerId = (String) session.getAttribute("consumerId"); // Get consumer ID from session

        if (consumerId == null) {
            request.setAttribute("errorMessage", "Consumer ID not found. Please log in again.");
            request.getRequestDispatcher("complaintHistory.jsp").forward(request, response);
            return;
        }

        List<String[]> complaintHistory = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "SELECT complaint_id, complaint_type, category, description, status FROM Complaint WHERE consumer_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, consumerId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String[] complaint = {
                            String.valueOf(rs.getInt("complaint_id")),
                            rs.getString("complaint_type"),
                            rs.getString("category"),
                            rs.getString("description"),
                            rs.getString("status")
                        };
                        complaintHistory.add(complaint);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching complaint history.");
        }

        request.setAttribute("complaintHistory", complaintHistory);
        request.getRequestDispatcher("complaintHistory.jsp").forward(request, response);
    }
}

