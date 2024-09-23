<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #77ba95;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .container {
            margin-top: 50px;
            max-width: 800px;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
        }
        th, td {
            text-align: center;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
        .view-data-btn {
            background-color: #007bff;
            color: white;
        }
        .view-data-btn:hover {
            background-color: #0056b3;
        }
    </style>
    <script type="text/javascript">
        function redirectToLogin() {
            window.location.href = 'userlogin.jsp'; 
        }

        <s:if test="#session.user1 == null">
            redirectToLogin();
        </s:if>
    </script>
</head>
<body>
    <div class="container">
        <s:if test="#session.user1 != null">
            <h2 class="text-center mb-4">User Dashboard</h2>
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><s:property value="#session.user1.id" /></td>
                        <td><s:property value="#session.user1.name" /></td>
                        <td><s:property value="#session.user1.email" /></td>
                        <td><s:property value="#session.user1.password" /></td>
                    </tr>
                </tbody>
            </table>
            
            <!-- Logout Button -->
            <div class="d-flex justify-content-center mt-4">
                <s:form action="logout" method="post">
                    <s:submit value="Logout" cssClass="btn logout-btn btn-block" style="width: 200px;" />
                </s:form>
            </div>
            
            <!-- View Data Button -->
            <div class="d-flex justify-content-center mt-3">
               <!--  <a href="getAllStudents?pageNumber=1&pageSize=10">View Users</a> -->
                
                 <a href="showdata.jsp" class="btn view-data-btn btn-block" onclick="event.preventDefault(); window.location.href='showdata';" style="width: 200px;">View Data</a> 
            </div>
        </s:if>

        <!-- If no session is found -->
        <s:else>
            <div class="alert alert-warning text-center" role="alert">
                No user data found. Please log in.
            </div>
        </s:else>
    </div>

    <!-- Bootstrap JS and dependencies (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
