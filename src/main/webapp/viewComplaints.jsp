<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tcs.Complaint" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Complaints</title>
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
        .status-resolved {
            color: green;
            font-weight: bold;
        }
        .status-pending {
            color: red;
            font-weight: bold;
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
        <h2>Complaint List</h2>
        <table>
            <tr>
                <th>Complaint ID</th>
                <th>Consumer ID</th>
                <th>Type</th>
                <th>Category</th>
                <th>Description</th>
                <th>Status</th>
            </tr>
            <%
                ArrayList<Complaint> complaints = (ArrayList<Complaint>) request.getAttribute("complaints");
                if (complaints != null && !complaints.isEmpty()) {
                    for (Complaint complaint : complaints) {
            %>
            <tr>
                <td><%= complaint.getComplaintId() %></td>
                <td><%= complaint.getConsumerId() %></td>
                <td><%= complaint.getComplaintType() %></td>
                <td><%= complaint.getCategory() %></td>
                <td><%= complaint.getDescription() %></td>
                <td class="<%= "Resolved".equalsIgnoreCase(complaint.getStatus()) ? "status-resolved" : "status-pending" %>">
                    <%= complaint.getStatus() %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No complaints found.</td>
            </tr>
            <% } %>
        </table>
        <a href="adminDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
