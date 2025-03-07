<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint History</title>
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
            width: 600px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
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
        p {
            color: #666;
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
        <h2>Customer Complaint History</h2>

        <%
            List<String[]> complaintHistory = (List<String[]>) request.getAttribute("complaintHistory");
            if (complaintHistory == null || complaintHistory.isEmpty()) {
        %>
            <p>No complaint history available.</p>
        <%
            } else {
        %>
            <table>
                <tr>
                    <th>Complaint ID</th>
                    <th>Complaint Type</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
                <%
                    for (String[] com : complaintHistory) {
                %>
                    <tr>
                        <td><%= com[0] %></td>
                        <td><%= com[1] %></td>
                        <td><%= com[2] %></td>
                        <td><%= com[3] %></td>
                        <td><%= com[4] %></td>
                    </tr>
                <%
                    }
                %>
            </table>
        <%
            }
        %>

        <br>
        <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
