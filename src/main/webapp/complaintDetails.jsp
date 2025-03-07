<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Details</title>
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
            width: 400px;
            text-align: left;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        p {
            font-size: 16px;
            color: #555;
            margin: 10px 0;
        }
        strong {
            color: #000;
        }
        .back-link {
            display: block;
            margin-top: 20px;
            padding: 10px;
            background: #007bff;
            color: white;
            text-align: center;
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
        <h2>Complaint Details</h2>

        <p><strong>Complaint ID:</strong> ${complaintId}</p>
        <p><strong>Consumer ID:</strong> ${consumerId}</p>
        <p><strong>Complaint Type:</strong> ${complaintType}</p>
        <p><strong>Category:</strong> ${category}</p>
        <p><strong>Description:</strong> ${description}</p>
        <p><strong>Status:</strong> ${status}</p>

        <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
