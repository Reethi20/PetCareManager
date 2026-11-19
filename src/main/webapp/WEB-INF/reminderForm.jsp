<%@ page language="java" import="model.Reminder" %>
<%
Reminder r = (Reminder) request.getAttribute("reminder");
boolean isEdit = (r != null && r.getReminderId() != 0);
%>
<!DOCTYPE html>
<html>
<head>
<title><%= isEdit ? "Edit Reminder" : "Add Reminder" %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2><%= isEdit ? "Edit Reminder" : "Add Reminder" %></h2>

<div class="card p-4">
<form action="reminder" method="post">
<input type="hidden" name="action" value="save">
<input type="hidden" name="reminderId" value="<%= isEdit ? r.getReminderId() : 0 %>">

<div class="mb-2"><label>Dog ID</label>
<input type="number" name="dogId" class="form-control"
       value="<%= isEdit ? r.getDogId() : "" %>" required></div>

<div class="mb-2"><label>Type</label>
<select name="reminderType" class="form-select">
    <%
    String type = isEdit ? r.getReminderType() : "";
    %>
    <option <%= "Vaccination".equals(type) ? "selected" : "" %>>Vaccination</option>
    <option <%= "Vet Visit".equals(type) ? "selected" : "" %>>Vet Visit</option>
    <option <%= "Grooming".equals(type) ? "selected" : "" %>>Grooming</option>
    <option <%= "Medicine".equals(type) ? "selected" : "" %>>Medicine</option>
    <option <%= "Other".equals(type) ? "selected" : "" %>>Other</option>
</select></div>

<div class="mb-2"><label>Title</label>
<input type="text" name="title" class="form-control"
       value="<%= isEdit ? r.getTitle() : "" %>" required></div>

<div class="mb-2"><label>Message</label>
<textarea name="msg" class="form-control"><%= isEdit && r.getMessage()!=null ? r.getMessage() : "" %></textarea></div>

<div class="mb-2"><label>Date</label>
<input type="date" name="date" class="form-control"
       value="<%= isEdit && r.getReminderDate()!=null ? r.getReminderDate().toString() : "" %>" required></div>

<div class="mb-2"><label>Time</label>
<input type="time" name="time" class="form-control"
       value="<%= isEdit && r.getReminderTime()!=null ? r.getReminderTime().toString().substring(0,5) : "" %>" required></div>

<div class="mb-2"><label>Status</label>
<select name="status" class="form-select">
    <%
    String st = isEdit ? r.getStatus() : "PENDING";
    %>
    <option value="PENDING" <%= "PENDING".equals(st) ? "selected" : "" %>>PENDING</option>
    <option value="DONE" <%= "DONE".equals(st) ? "selected" : "" %>>DONE</option>
    <option value="MISSED" <%= "MISSED".equals(st) ? "selected" : "" %>>MISSED</option>
</select></div>

<button class="btn btn-success"><%= isEdit ? "Update" : "Save" %></button>
<a href="reminder?action=list" class="btn btn-secondary">Cancel</a>

</form>
</div>
</div>
</body>
</html>
