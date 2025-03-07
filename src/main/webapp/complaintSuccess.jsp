<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complaint Register</title>
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
        text-align: center;
        width: 400px;
    }
    h1 {
        color: #28a745;
        font-size: 22px;
    }
    .back-link {
        display: inline-block;
        margin-top: 15px;
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
        <h1>Complaint Registered Successfully!</h1>
        <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
