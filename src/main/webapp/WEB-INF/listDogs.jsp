<%@ page import="java.util.*, model.Dog" %>
<!DOCTYPE html>
<html>
<head>
<title>Dogs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2>All Dogs </h2>
<a href="dog?action=add" class="btn btn-primary btn-sm mb-3">Add Dog</a>

<%
List<Dog> dogs = (List<Dog>) request.getAttribute("dogList");
if (dogs == null || dogs.isEmpty()) {
%>
<div class="alert alert-warning">No dogs found</div>
<% } else { %>

<table class="table table-bordered table-striped">
<tr>
    <th>ID</th><th>Owner</th><th>Name</th><th>Breed</th>
    <th>Age</th><th>Gender</th><th>Weight</th><th>Color</th><th>Actions</th>
</tr>
<% for (Dog d : dogs) { %>
<tr>
    <td><%= d.getDogId() %></td>
    <td><%= d.getOwnerId() %></td>
    <td><%= d.getName() %></td>
    <td><%= d.getBreed() %></td>
    <td><%= d.getAge() %></td>
    <td><%= d.getGender() %></td>
    <td><%= d.getWeight() %></td>
    <td><%= d.getColor() %></td>
    <td>
        <a href="dog?action=edit&id=<%= d.getDogId() %>" class="btn btn-sm btn-warning">Edit</a>
        <a href="dog?action=delete&id=<%= d.getDogId() %>" class="btn btn-sm btn-danger"
           onclick="return confirm('Delete this dog?');">Delete</a>
    </td>
</tr>
<% } %>
</table>
<% } %>
</div>
</body>
</html>
