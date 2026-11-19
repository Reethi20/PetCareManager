<%@ page import="java.util.*, model.Expense" %>
<!DOCTYPE html>
<html>
<head>
<title>Expenses</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2>Expenses </h2>
<a href="expense?action=add" class="btn btn-primary btn-sm mb-3">Add Expense</a>

<%
List<Expense> ex = (List<Expense>) request.getAttribute("expenses");
if (ex == null || ex.isEmpty()) {
%>
<div class="alert alert-warning">No expenses found</div>
<% } else { %>

<table class="table table-bordered table-striped">
<tr>
    <th>ID</th><th>Dog</th><th>Date</th><th>Category</th>
    <th>Amount</th><th>Notes</th><th>Actions</th>
</tr>
<% for (Expense e : ex) { %>
<tr>
    <td><%= e.getExpenseId() %></td>
    <td><%= e.getDogId() %></td>
    <td><%= e.getDate() %></td>
    <td><%= e.getCategory() %></td>
    <td><%= e.getAmount() %></td>
    <td><%= e.getNotes() %></td>
    <td>
        <a href="expense?action=edit&id=<%= e.getExpenseId() %>" class="btn btn-sm btn-warning">Edit</a>
        <a href="expense?action=delete&id=<%= e.getExpenseId() %>" class="btn btn-sm btn-danger"
           onclick="return confirm('Delete this expense?');">Delete</a>
    </td>
</tr>
<% } %>
</table>
<% } %>
</div>
</body>
</html>
