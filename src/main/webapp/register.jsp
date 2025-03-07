<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
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
        width: 400px;
        background: white;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
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
</style>
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="registerServlet" method="post">
            <label for="name">Customer Name</label>
            <input id="name" type="text" name="cName" placeholder="Enter your name" required>

            <label for="email">Email Id</label>
            <input id="email" type="email" name="cEmail" placeholder="Enter your email" required>

            <label for="phNo">Phone Number</label>
            <input id="phNo" type="tel" name="cPhNo" placeholder="Enter your phone number" required>

            <label for="password">Password</label>
            <input id="password" type="password" name="cPass" placeholder="Enter password" required>

            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
