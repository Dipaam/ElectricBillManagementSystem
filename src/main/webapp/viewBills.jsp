<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Bills</title>
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
        .status-paid {
            color: green;
            font-weight: bold;
        }
        .status-pending {
            color: red;
            font-weight: bold;
        }
        .pay-button {
            padding: 8px 12px;
            border: none;
            background: #28a745;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.3s;
        }
        .pay-button:hover {
            background: #218838;
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
        <h2>Bill Details</h2>
        <table>
            <tr>
                <th>Bill ID</th>
                <th>Amount</th>
                <th>Due Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <% 
                java.util.List<String> bills = (java.util.List<String>) request.getAttribute("bills");
                if (bills != null && !bills.isEmpty()) {
                    for (String bill : bills) {
                        String billId = bill.split(", ")[0].split(": ")[1];
                        String status = bill.split(", ")[3].split(": ")[1];
            %>
            <tr>
                <td><%= billId %></td>
                <td>$<%= bill.split(", ")[1].split(": ")[1] %></td>
                <td><%= bill.split(", ")[2].split(": ")[1] %></td>
                <td class="<%= "Paid".equalsIgnoreCase(status) ? "status-paid" : "status-pending" %>">
                    <%= status %>
                </td>
                <td>
                    <% if (!"Paid".equalsIgnoreCase(status)) { %>
                        <form action="PayBillServlet" method="post">
                            <input type="hidden" name="billId" value="<%= billId %>">
                            <button type="submit" class="pay-button">Pay</button>
                        </form>
                    <% } else { %>
                        Paid
                    <% } %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr><td colspan="5">No bills found.</td></tr>
            <% } %>
        </table>
        <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
