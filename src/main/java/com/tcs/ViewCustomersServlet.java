package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewCustomersServlet")
public class ViewCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> customers = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try(Connection conn=DataBaseConnection.getConnection()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            String sql = "SELECT consumer_id, customer_name, email, mobile_number FROM Customer";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                customers.add("ID: " + rs.getString("consumer_id") + ", Name: " + rs.getString("customer_name") + ", Email: " + rs.getString("email") + ", Mobile: " + rs.getString("mobile_number"));
            }
            
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
