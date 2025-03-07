package com.tcs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("cName");
        String email = request.getParameter("cEmail");
        String mobile = request.getParameter("cPhNo");
        String password = request.getParameter("cPass");
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            response.getWriter().println("Invalid email format.");
            return;
        }

        PreparedStatement psCustomer = null, psLogin = null, psCheck = null;
        ResultSet rs = null;

        try(Connection conn1=DataBaseConnection.getConnection()){

            // Check if email already exists
            psCheck = conn1.prepareStatement("SELECT email FROM login WHERE email = ?");
            psCheck.setString(1, email);
            rs = psCheck.executeQuery();
            if (rs.next()) {
                response.getWriter().println("Error: Email already exists.");
                return;
            }

            // Insert into Customer table
            psCustomer = conn1.prepareStatement("INSERT INTO Customer(customer_name, email, mobile_number) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psCustomer.setString(1, name);
            psCustomer.setString(2, email);
            psCustomer.setString(3, mobile);
            psCustomer.executeUpdate();

            rs = psCustomer.getGeneratedKeys();
            int consumerId = 0;
            if (rs.next()) {
                consumerId = rs.getInt(1);
            }

            // Insert into Login table
            psLogin = conn1.prepareStatement("INSERT INTO login(consumer_id, email, password, status) VALUES (?, ?, ?, 'Active')");
            psLogin.setInt(1, consumerId);
            psLogin.setString(2, email);
            psLogin.setString(3, password);
            psLogin.executeUpdate();

            response.getWriter().println("Registration Successful.");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database Error: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (psCheck != null) psCheck.close(); } catch (Exception e) {}
            try { if (psCustomer != null) psCustomer.close(); } catch (Exception e) {}
            try { if (psLogin != null) psLogin.close(); } catch (Exception e) {}
        }
    }
}


