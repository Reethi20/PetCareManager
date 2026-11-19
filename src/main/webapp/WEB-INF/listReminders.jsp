<%@ page import="java.util.*, model.Reminder" %>
<!DOCTYPE html>
<html>
<head>
<title>Reminders</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2>Reminders </h2>
<a href="reminder?action=add" class="btn btn-primary btn-sm mb-3">Add Reminder</a>

<%
List<Reminder> rem = (List<Reminder>) request.getAttribute("reminders");
if (rem == null || rem.isEmpty()) {
%>
<div class="alert alert-warning">No reminders found</div>
<% } else { %>

<table class="table table-bordered table-striped">
<tr>
    <th>ID</th><th>Owner</th><th>Dog</th><th>Type</th>
    <th>Title</th><th>Message</th><th>Date</th><th>Time</th>
    <th>Status</th><th>Actions</th>
</tr>
<% for (Reminder r : rem) { %>
<tr>
    <td><%= r.getReminderId() %></td>
    <td><%= r.getOwnerId() %></td>
    <td><%= r.getDogId() %></td>
    <td><%= r.getReminderType() %></td>
    <td><%= r.getTitle() %></td>
    <td><%= r.getMessage() %></td>
    <td><%= r.getReminderDate() %></td>
    <td><%= r.getReminderTime() %></td>
    <td><%= r.getStatus() %></td>
    <td>
        <a href="reminder?action=edit&id=<%= r.getReminderId() %>" class="btn btn-sm btn-warning">Edit</a>
        <a href="reminder?action=delete&id=<%= r.getReminderId() %>" class="btn btn-sm btn-danger"
           onclick="return confirm('Delete this reminder?');">Delete</a>
    </td>
</tr>
<% } %>
</table>
<% } %>
</div>
</body>
</html>
