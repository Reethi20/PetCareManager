<%@ page language="java" import="model.Dog" %>
<%
Dog dog = (Dog) request.getAttribute("dog"); // null for Add
boolean isEdit = (dog != null && dog.getDogId() != 0);
%>
<!DOCTYPE html>
<html>
<head>
<title><%= isEdit ? "Edit Dog" : "Add Dog" %></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
<h2><%= isEdit ? "Edit Dog" : "Add Dog" %></h2>

<div class="card p-4">
<form action="dog" method="post">
    <input type="hidden" name="action" value="save">
    <input type="hidden" name="dogId" value="<%= isEdit ? dog.getDogId() : 0 %>">

    <div class="mb-2">
        <label>Name</label>
        <input type="text" name="name" class="form-control"
               value="<%= isEdit ? dog.getName() : "" %>" required>
    </div>

    <div class="mb-2">
        <label>Breed</label>
        <input type="text" name="breed" class="form-control"
               value="<%= isEdit ? dog.getBreed() : "" %>">
    </div>

    <div class="mb-2">
        <label>Age</label>
        <input type="number" name="age" class="form-control"
               value="<%= isEdit ? dog.getAge() : "" %>">
    </div>

    <div class="mb-2">
        <label>Gender</label>
        <select name="gender" class="form-select">
            <option <%= isEdit && "Male".equals(dog.getGender()) ? "selected" : "" %>>Male</option>
            <option <%= isEdit && "Female".equals(dog.getGender()) ? "selected" : "" %>>Female</option>
        </select>
    </div>

    <div class="mb-2">
        <label>Weight</label>
        <input type="number" step="0.1" name="weight" class="form-control"
               value="<%= isEdit ? dog.getWeight() : "" %>">
    </div>

    <div class="mb-2">
        <label>Color</label>
        <input type="text" name="color" class="form-control"
               value="<%= isEdit ? dog.getColor() : "" %>">
    </div>

    <button class="btn btn-success"><%= isEdit ? "Update" : "Save" %></button>
    <a href="dog?action=list" class="btn btn-secondary">Cancel</a>
</form>
</div>
</div>
</body>
</html>
