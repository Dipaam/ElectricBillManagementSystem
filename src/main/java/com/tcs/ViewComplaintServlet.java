package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewComplaintServlet")
public class ViewComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");

        if (userType == null || !userType.equals("Admin")) {
            response.sendRedirect("login.jsp");
            return;
        }

        ArrayList<Complaint> complaintsList = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "SELECT * FROM complaint";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Complaint complaint = new Complaint(
                        rs.getInt("complaint_id"),
                        rs.getInt("consumer_id"),
                        rs.getString("complaint_type"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status")
                    );
                    complaintsList.add(complaint);
                }

                request.setAttribute("complaints", complaintsList);
                request.getRequestDispatcher("viewComplaints.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error while fetching complaints");
        }
    }
}