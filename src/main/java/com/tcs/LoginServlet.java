package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try(Connection con=DataBaseConnection.getConnection()) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Validate credentials
            pstmt = con.prepareStatement("SELECT * FROM login WHERE email = ? AND password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            String consumerId=null;
            if (rs.next()) {
                consumerId=rs.getString("consumer_id");
                String status = rs.getString("status");
                if ("Inactive".equals(status)) {
                    request.setAttribute("errorMessage", "Your account is deactivated.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("consumerId", consumerId);
                session.setAttribute("userEmail", email);
                session.setAttribute("userType", rs.getString("userType"));

                if ("Admin".equals(rs.getString("userType"))) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("customerDashboard.jsp");
                }

            } else {
                request.setAttribute("errorMessage", "Invalid Credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


