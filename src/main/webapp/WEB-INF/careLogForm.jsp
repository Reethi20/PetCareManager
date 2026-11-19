<%@ page language="java" import="model.CareLog" %>
<%
CareLog log = (CareLog) request.getAttribute("careLog");
boolean isEdit = (log != null && log.getLogId() != 0);
%>
<!DOCTYPE html>
<html>
<head>
<title><%= isEdit ? "Edit Care Log" : "Add Care Log" %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2><%= isEdit ? "Edit Care Log" : "Add Care Log" %></h2>

<div class="card p-4">
<form action="careLog" method="post">
<input type="hidden" name="action" value="save">
<input type="hidden" name="logId" value="<%= isEdit ? log.getLogId() : 0 %>">

<div class="mb-2"><label>Dog ID</label>
<input type="number" name="dogId" class="form-control"
       value="<%= isEdit ? log.getDogId() : "" %>" required></div>

<div class="mb-2"><label>Date</label>
<input type="date" name="date" class="form-control"
       value="<%= isEdit && log.getDate()!=null ? log.getDate().toString() : "" %>" required></div>

<div class="mb-2"><label>Time</label>
<input type="time" name="time" class="form-control"
       value="<%= isEdit && log.getTime()!=null ? log.getTime().toString().substring(0,5) : "" %>" required></div>

<div class="mb-2"><label>Activity</label>
<select name="activityType" class="form-select">
    <%
    String act = isEdit ? log.getActivityType() : "";
    %>
    <option <%= "Walk".equals(act) ? "selected" : "" %>>Walk</option>
    <option <%= "Feed".equals(act) ? "selected" : "" %>>Feed</option>
    <option <%= "Bath".equals(act) ? "selected" : "" %>>Bath</option>
    <option <%= "Medicine".equals(act) ? "selected" : "" %>>Medicine</option>
    <option <%= "Play".equals(act) ? "selected" : "" %>>Play</option>
    <option <%= "Other".equals(act) ? "selected" : "" %>>Other</option>
</select></div>

<div class="mb-2"><label>Description</label>
<textarea name="description" class="form-control"><%= isEdit && log.getDescription()!=null ? log.getDescription() : "" %></textarea></div>

<button class="btn btn-success"><%= isEdit ? "Update" : "Save" %></button>
<a href="careLog?action=list" class="btn btn-secondary">Cancel</a>

</form>
</div>
</div>
</body>
</html>
