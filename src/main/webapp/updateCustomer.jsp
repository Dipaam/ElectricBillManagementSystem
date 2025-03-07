<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.io.*"%>
<%@ page import="com.tcs.DataBaseConnection"%>

<%
    HttpSession session1 = request.getSession();
    String consumerId = (String) session1.getAttribute("consumerId");

    if (consumerId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String name = "", email = "", phone = "";

    try (Connection conn = DataBaseConnection.getConnection()) {
        String sql = "SELECT customer_name, email, mobile_number FROM customer WHERE consumer_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, consumerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("customer_name");
                    email = rs.getString("email");
                    phone = rs.getString("mobile_number");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Personal Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #007bff, #6610f2);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            text-align: left;
            font-weight: bold;
            margin-top: 10px;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.3s;
        }
        input[type="submit"]:hover {
            background: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background 0.3s;
        }
        .back-link:hover {
            background: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Your Details</h2>
        <form action="UpdateCustomerServlet" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= name %>" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= email %>" required>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="<%= phone %>" required>

            <input type="submit" value="Update">
        </form>
        <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
