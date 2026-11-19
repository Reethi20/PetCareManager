<%@ page language="java" import="model.Appointment" %>
<%
Appointment a = (Appointment) request.getAttribute("appointment");
boolean isEdit = (a != null && a.getAppointmentId() != 0);
%>
<!DOCTYPE html>
<html>
<head>
<title><%= isEdit ? "Edit Appointment" : "Add Appointment" %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2><%= isEdit ? "Edit Appointment" : "Add Appointment" %></h2>

<div class="card p-4">
<form action="appointment" method="post">
<input type="hidden" name="action" value="save">
<input type="hidden" name="appointmentId" value="<%= isEdit ? a.getAppointmentId() : 0 %>">

<div class="mb-2"><label>Dog ID</label>
<input type="number" name="dogId" class="form-control"
       value="<%= isEdit ? a.getDogId() : "" %>" required></div>

<div class="mb-2"><label>Type</label>
<select name="type" class="form-select">
    <option <%= isEdit && "Vet".equals(a.getType()) ? "selected" : "" %>>Vet</option>
    <option <%= isEdit && "Grooming".equals(a.getType()) ? "selected" : "" %>>Grooming</option>
    <option <%= isEdit && "Training".equals(a.getType()) ? "selected" : "" %>>Training</option>
    <option <%= isEdit && "Other".equals(a.getType()) ? "selected" : "" %>>Other</option>
</select></div>

<div class="mb-2"><label>Date</label>
<input type="date" name="date" class="form-control"
       value="<%= isEdit && a.getDate()!=null ? a.getDate().toString() : "" %>" required></div>

<div class="mb-2"><label>Time</label>
<input type="time" name="time" class="form-control"
       value="<%= isEdit && a.getTime()!=null ? a.getTime().toString().substring(0,5) : "" %>" required></div>

<div class="mb-2"><label>Status</label>
<select name="status" class="form-select">
    <%
    String st = isEdit ? a.getStatus() : "Upcoming";
    %>
    <option value="Upcoming" <%= "Upcoming".equals(st) ? "selected" : "" %>>Upcoming</option>
    <option value="Completed" <%= "Completed".equals(st) ? "selected" : "" %>>Completed</option>
    <option value="Cancelled" <%= "Cancelled".equals(st) ? "selected" : "" %>>Cancelled</option>
</select></div>

<div class="mb-2"><label>Notes</label>
<textarea name="notes" class="form-control"><%= isEdit && a.getNotes()!=null ? a.getNotes() : "" %></textarea></div>

<button class="btn btn-success"><%= isEdit ? "Update" : "Save" %></button>
<a href="appointment?action=list" class="btn btn-secondary">Cancel</a>

</form>
</div>
</div>
</body>
</html>
