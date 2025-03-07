<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tcs.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Customer Accounts</title>
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
            max-width: 900px;
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
        .status-active {
            color: green;
            font-weight: bold;
        }
        .status-inactive {
            color: red;
            font-weight: bold;
        }
        button {
            padding: 8px 12px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }
        .deactivate {
            background: red;
            color: white;
        }
        .deactivate:hover {
            background: darkred;
        }
        .activate {
            background: green;
            color: white;
        }
        .activate:hover {
            background: darkgreen;
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
        <h2>Manage Customer Accounts</h2>
        <table>
            <tr>
                <th>Consumer ID</th>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <%
                ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
                if (customers != null && !customers.isEmpty()) {
                    for (Customer customer : customers) {
            %>
            <tr>
                <td><%= customer.getConsumerId() %></td>
                <td><%= customer.getCustomerName() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getMobileNumber() %></td>
                <td class="<%= "Active".equals(customer.getStatus()) ? "status-active" : "status-inactive" %>">
                    <%= customer.getStatus() %>
                </td>
                <td>
                    <form action="ManageAccountsServlet" method="post">
                        <input type="hidden" name="consumerId" value="<%= customer.getConsumerId() %>">
                        <% if ("Active".equals(customer.getStatus())) { %>
                            <button type="submit" name="action" value="deactivate" class="deactivate">Deactivate</button>
                        <% } else { %>
                            <button type="submit" name="action" value="activate" class="activate">Activate</button>
                        <% } %>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No customer accounts found.</td>
            </tr>
            <%
                }
            %>
        </table>
        <a href="adminDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
