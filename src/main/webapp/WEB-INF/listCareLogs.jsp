<%@ page import="java.util.*, model.CareLog" %>
<!DOCTYPE html>
<html>
<head>
<title>Care Logs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2>Care Logs </h2>
<a href="careLog?action=add" class="btn btn-primary btn-sm mb-3">Add Care Log</a>

<%
List<CareLog> logs = (List<CareLog>) request.getAttribute("careLogs");
if (logs == null || logs.isEmpty()) {
%>
<div class="alert alert-warning">No care logs found</div>
<% } else { %>

<table class="table table-bordered table-striped">
<tr>
    <th>ID</th><th>Dog</th><th>Date</th><th>Time</th>
    <th>Activity</th><th>Description</th><th>Actions</th>
</tr>
<% for (CareLog c : logs) { %>
<tr>
    <td><%= c.getLogId() %></td>
    <td><%= c.getDogId() %></td>
    <td><%= c.getDate() %></td>
    <td><%= c.getTime() %></td>
    <td><%= c.getActivityType() %></td>
    <td><%= c.getDescription() %></td>
    <td>
        <a href="careLog?action=edit&id=<%= c.getLogId() %>" class="btn btn-sm btn-warning">Edit</a>
        <a href="careLog?action=delete&id=<%= c.getLogId() %>" class="btn btn-sm btn-danger"
           onclick="return confirm('Delete this care log?');">Delete</a>
    </td>
</tr>
<% } %>
</table>
<% } %>
</div>
</body>
</html>
