<%@ page import="DAO.crudOperations"%>
<%@ page import="Entity.user"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Student</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #77ba95;
            font-family: Arial, sans-serif;
        }

        .form-container {
            margin: 50px auto;
            max-width: 500px;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            margin-bottom: 20px;
        }

        .btn-block {
            width: 100%;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="text-center">Update Student</h2>

        <%
            int id = Integer.parseInt(request.getParameter("id"));
            user u = crudOperations.getUserByID(id);
        %>

        <form action="updatedStudent" method="post">
            <div class="form-group">
                <label for="name">Enter Name</label>
                <input type="text" class="form-control" id="name" name="name" value="<%=u.getName()%>" required>
            </div>
            <div class="form-group">
                <label for="email">Enter Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%=u.getEmail()%>" required>
            </div>
            <div class="form-group">
                <label for="password">Enter Password</label>
                <input type="password" class="form-control" id="password" name="password" value="<%=u.getPassword()%>" required>
            </div>
            <input type="hidden" name="id" value="<%=u.getId()%>">
            <button type="submit" class="btn btn-primary btn-block">Update</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
