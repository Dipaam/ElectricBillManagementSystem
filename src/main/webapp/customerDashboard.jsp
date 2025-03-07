<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
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
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            display: block;
            padding: 10px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background 0.3s;
        }
        a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Dashboard</h2>
        <ul>
            <li><a href="ViewBillsServlet">View Bills</a></li>
            <li><a href="BillHistoryServlet">Bill History</a></li>
            <li><a href="registerComplaint.jsp">Register Complaint</a></li>
            <li><a href="searchComplaint.jsp">Search Complaint</a></li>
            <li><a href="ViewComplaintHistoryServlet">View Complaint History</a></li>
            <li><a href="updateCustomer.jsp">Update Profile</a></li>
            <li><a href="deactivateAccountServlet">Deactivate Account</a></li>
            <li><a href="logoutServlet">Logout</a></li>
        </ul>
    </div>
</body>
</html>
