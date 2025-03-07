package com.tcs;

import java.sql.*;

public class AdminRegistration {
    public static void main(String[] args) {
        String email = "admin@ems.com";
        String password = "admin123";

        try (Connection conn=DataBaseConnection.getConnection()){
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Check if Admin already exists
            String checkAdminQuery = "SELECT * FROM Login WHERE email = ?";
            PreparedStatement psCheck = conn.prepareStatement(checkAdminQuery);
            psCheck.setString(1, email);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                System.out.println("Admin already exists!");
                return;
            }

            // Insert into Login Table
            String insertAdmin = "INSERT INTO Login (email, password, status, userType) VALUES (?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(insertAdmin);
            psInsert.setString(1, email);
            psInsert.setString(2, password);
            psInsert.setString(3, "Active");
            psInsert.setString(4, "Admin");
            psInsert.executeUpdate();

            System.out.println("Admin Registered Successfully!");

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
