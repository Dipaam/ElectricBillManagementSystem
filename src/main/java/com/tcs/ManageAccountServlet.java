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

@WebServlet("/ManageAccountsServlet")
public class ManageAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");

        if (userType == null || !userType.equals("Admin")) {
            response.sendRedirect("login.jsp");
            return;
        }

        ArrayList<Customer> customerList = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "SELECT c.consumer_id, c.customer_name, c.email, c.mobile_number, l.status " +
                         "FROM Customer c JOIN Login l ON c.consumer_id = l.consumer_id";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Customer customer = new Customer(
                        rs.getInt("consumer_id"),
                        rs.getString("customer_name"),
                        rs.getString("email"),
                        rs.getString("mobile_number"),
                        rs.getString("status")
                    );
                    customerList.add(customer);
                }

                request.setAttribute("customers", customerList);
                request.getRequestDispatcher("manageAccounts.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error while fetching customer accounts");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");

        if (userType == null || !userType.equals("Admin")) {
            response.sendRedirect("login.jsp");
            return;
        }

        int consumerId = Integer.parseInt(request.getParameter("consumerId"));
        String action = request.getParameter("action");
        String newStatus = action.equals("activate") ? "Active" : "Inactive";

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "UPDATE Login SET status = ? WHERE consumer_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newStatus);
                ps.setInt(2, consumerId);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    response.sendRedirect("ManageAccountsServlet?message=Account updated successfully");
                } else {
                    response.sendRedirect("ManageAccountsServlet?message=Error updating account");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error while updating account");
        }
    }
}
