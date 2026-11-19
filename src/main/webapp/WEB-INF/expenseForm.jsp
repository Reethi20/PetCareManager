<%@ page language="java" import="model.Expense" %>
<%
Expense exp = (Expense) request.getAttribute("expense");
boolean isEdit = (exp != null && exp.getExpenseId() != 0);
%>
<!DOCTYPE html>
<html>
<head>
<title><%= isEdit ? "Edit Expense" : "Add Expense" %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2><%= isEdit ? "Edit Expense" : "Add Expense" %></h2>

<div class="card p-4">
<form action="expense" method="post">
<input type="hidden" name="action" value="save">
<input type="hidden" name="expenseId" value="<%= isEdit ? exp.getExpenseId() : 0 %>">

<div class="mb-2"><label>Dog ID</label>
<input type="number" name="dogId" class="form-control"
       value="<%= isEdit ? exp.getDogId() : "" %>" required></div>

<div class="mb-2"><label>Date</label>
<input type="date" name="date" class="form-control"
       value="<%= isEdit && exp.getDate()!=null ? exp.getDate().toString() : "" %>" required></div>

<div class="mb-2"><label>Category</label>
<select name="category" class="form-select">
    <%
    String cat = isEdit ? exp.getCategory() : "";
    %>
    <option <%= "Food".equals(cat) ? "selected" : "" %>>Food</option>
    <option <%= "Vet".equals(cat) ? "selected" : "" %>>Vet</option>
    <option <%= "Grooming".equals(cat) ? "selected" : "" %>>Grooming</option>
    <option <%= "Medicine".equals(cat) ? "selected" : "" %>>Medicine</option>
    <option <%= "Others".equals(cat) ? "selected" : "" %>>Others</option>
</select></div>

<div class="mb-2"><label>Amount</label>
<input type="number" step="0.01" name="amount" class="form-control"
       value="<%= isEdit ? exp.getAmount() : "" %>" required></div>

<div class="mb-2"><label>Notes</label>
<textarea name="notes" class="form-control"><%= isEdit && exp.getNotes()!=null ? exp.getNotes() : "" %></textarea></div>

<button class="btn btn-success"><%= isEdit ? "Update" : "Save" %></button>
<a href="expense?action=list" class="btn btn-secondary">Cancel</a>

</form>
</div>
</div>
</body>
</html>
