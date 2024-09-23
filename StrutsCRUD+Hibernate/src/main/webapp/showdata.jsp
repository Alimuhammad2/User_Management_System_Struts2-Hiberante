<%@page import="Entity.user"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Data</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
    background-color: #77ba95;
}

.table-container {
    margin: 50px auto;
    max-width: 900px;
}

.button-update, .button-delete {
    margin: 5px;
}

.pagination {
    justify-content: center;
}
</style>
<script>
    // JavaScript function to filter the user list in real-time
    function filterUsers() {
        var input = document.getElementById('searchQuery');
        var filter = input.value.toLowerCase();
        var table = document.getElementById('userTable');
        var tr = table.getElementsByTagName('tr');
        
        for (var i = 1; i < tr.length; i++) { // Start from 1 to skip the table header
            var td = tr[i].getElementsByTagName('td')[1]; // Assuming the second column is the name
            if (td) {
                var textValue = td.textContent || td.innerText;
                if (textValue.toLowerCase().indexOf(filter) > -1) {
                    tr[i].style.display = ""; // Show row
                } else {
                    tr[i].style.display = "none"; // Hide row
                }
            }               
        }
    }
</script>
</head>
<body>

    <div class="table-container">
        <h2 class="text-center mb-4">User Data</h2>
        
        <!-- Search Input for Real-Time Filtering -->
        <div class="form-group mb-4">
            <input type="text" id="searchQuery" class="form-control" placeholder="Search by name..." onkeyup="filterUsers()">
        </div>

        <!-- User Table -->
        <table class="table table-bordered table-striped" id="userTable">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Password</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="userList">
                    <tr>
                        <td><s:property value="id" /></td>
                        <td><s:property value="name" /></td>
                        <td><s:property value="email" /></td>
                        <td><s:property value="password" /></td>
                        <td>
                            <!-- Update Button -->
							<form action="updatestudent.jsp" method="post" style="display:inline;">
    						<input type="hidden" name="id" value="<s:property value='id'/>">
    						<button type="submit" class="btn btn-warning button-update">Update</button>
							</form>

							<!-- Delete Button -->
							<form action="deletestudent" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this user?');">
   							<input type="hidden" name="id" value="<s:property value='id'/>">
    						<button type="submit" class="btn btn-danger button-delete">Delete</button>
							</form>                        
						</td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>

        <a href="userregister.jsp" class="btn btn-primary button-update">Add User</a>

        <!-- Pagination -->
        <nav aria-label="Page navigation">
    	<ul class="pagination">
        <!-- Previous button -->
        <li class="page-item <s:if test='pageNumber == 1'>disabled</s:if>'">
            <form action="getAllStudents" method="post" style="display: inline;">
                <input type="hidden" name="pageNumber" value="<s:property value='%{pageNumber - 1}'/>">
                <button class="page-link" type="submit" <s:if test='pageNumber == 1'>disabled</s:if>>Previous</button>
            </form>
        </li>

        <!-- Page numbers -->
        <s:iterator begin="1" end="totalPages" status="pageStatus">
            <li class="page-item <s:if test='%{pageStatus.index == pageNumber}'>active</s:if>'">
                <form action="getAllStudents" method="post" style="display: inline;">
                    <input type="hidden" name="pageNumber" value="<s:property value='%{pageStatus.index}'/>">
                    <button class="page-link" type="submit">
                        <s:property value="%{pageStatus.index}" />
                    </button>
                </form>
            </li>
        </s:iterator>

        <!-- Next button -->
        <li class="page-item <s:if test='%{pageNumber == totalPages}'>disabled</s:if>'">
            <form action="getAllStudents" method="post" style="display: inline;">
                <input type="hidden" name="pageNumber" value="<s:property value='%{pageNumber + 1}'/>">
                <button class="page-link" type="submit" <s:if test='%{pageNumber == totalPages}'>disabled</s:if>>Next</button>
            </form>
        </li>
    	</ul>
		</nav>

    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>















