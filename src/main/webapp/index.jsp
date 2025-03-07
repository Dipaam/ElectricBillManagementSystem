<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Electricity Bill Management System</title>
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
        text-align: center;
    }
    .container {
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 350px;
    }
    h1 {
        color: #333;
    }
    .nav-links {
        margin-top: 20px;
    }
    a {
        display: block;
        padding: 10px;
        margin: 10px 0;
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
        <h1>Electricity Bill Management System</h1>
        <div class="nav-links">
            <a href="register.jsp">Register</a>
            <a href="login.jsp">Login</a>
            <a href="restoreAccount.jsp">Restore Your Account</a>
        </div>
    </div>
</body>
</html>
