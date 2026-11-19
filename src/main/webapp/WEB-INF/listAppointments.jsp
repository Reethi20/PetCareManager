<%@ page import="java.util.*, model.Appointment" %>
<!DOCTYPE html>
<html>
<head>
<title>Appointments</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2>Appointments </h2>
<a href="appointment?action=add" class="btn btn-primary btn-sm mb-3">Add Appointment</a>

<%
List<Appointment> list = (List<Appointment>) request.getAttribute("appointments");
if (list == null || list.isEmpty()) {
%>
<div class="alert alert-warning">No appointments found</div>
<% } else { %>

<table class="table table-bordered table-striped">
<tr>
    <th>ID</th><th>Dog</th><th>Type</th><th>Date</th><th>Time</th>
    <th>Status</th><th>Notes</th><th>Actions</th>
</tr>
<% for (Appointment ap : list) { %>
<tr>
    <td><%= ap.getAppointmentId() %></td>
    <td><%= ap.getDogId() %></td>
    <td><%= ap.getType() %></td>
    <td><%= ap.getDate() %></td>
    <td><%= ap.getTime() %></td>
    <td><%= ap.getStatus() %></td>
    <td><%= ap.getNotes() %></td>
    <td>
        <a href="appointment?action=edit&id=<%= ap.getAppointmentId() %>" class="btn btn-sm btn-warning">Edit</a>
        <a href="appointment?action=delete&id=<%= ap.getAppointmentId() %>" class="btn btn-sm btn-danger"
           onclick="return confirm('Delete this appointment?');">Delete</a>
    </td>
</tr>
<% } %>
</table>
<% } %>
</div>
</body>
</html>
