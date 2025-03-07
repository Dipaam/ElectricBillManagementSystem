<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
        }
        h2 {
            color: red;
        }
        p {
            font-size: 16px;
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            background-color: red;
            border-radius: 5px;
            font-weight: bold;
        }
        a:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Oops! Something went wrong.</h2>
        <p>${errorMessage}</p>
        <a href="index.jsp">Go to Home</a>
    </div>
</body>
</html>
