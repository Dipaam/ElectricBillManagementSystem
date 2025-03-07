<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        .container {
            width: 50%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
        }
        .message {
            color: green;
            font-weight: bold;
            font-size: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            text-decoration: none;
            color: white;
            background-color: #28a745;
            border-radius: 5px;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Update Successful ✅</h2>
        <p class="message">Your details have been successfully updated!</p>

        <a href="customerDashboard.jsp" class="btn">Back to Dashboard</a>
        <br><br>
        <a href="updateCustomer.jsp" class="btn" style="background-color: #007bff;">Update Again</a>
    </div>

</body>
</html>
