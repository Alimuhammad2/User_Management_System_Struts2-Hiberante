<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #77ba95;
        }
        .form-container {
            margin: 50px auto;
            max-width: 400px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
    <script>
        function validateForm() {
            const name = document.getElementById("name").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            if (name === "" || email === "" || password === "") {
                alert("Please fill in all fields.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission
        }
    </script>
</head>
<body>

<div class="form-container">
    <h2 class="text-center mb-4">User Registration</h2>
    <form action="userregister" method="post" onsubmit="return validateForm();">
        <div class="form-group">
            <label for="name">Enter Name</label>
            <input type="text" name="name" id="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="email">Enter Email</label>
            <input type="email" name="email" id="email" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="password">Enter Password</label>
            <input type="password" name="password" id="password" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Register</button>
        <br>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
