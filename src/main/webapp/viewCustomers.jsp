<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Customers</title>
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
            flex-direction: column;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background 0.3s;
        }
        .back-link:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer List</h2>
        <table>
            <tr>
                <th>Consumer ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
            </tr>
            <% 
                java.util.List<String> customers = (java.util.List<String>) request.getAttribute("customers");
                if (customers != null && !customers.isEmpty()) {
                    for (String customer : customers) {
            %>
            <tr>
                <td><%= customer.split(", ")[0].split(": ")[1] %></td>
                <td><%= customer.split(", ")[1].split(": ")[1] %></td>
                <td><%= customer.split(", ")[2].split(": ")[1] %></td>
                <td><%= customer.split(", ")[3].split(": ")[1] %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr><td colspan="4">No customers found.</td></tr>
            <% } %>
        </table>
        <a href="adminDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
