<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PetCare Manager - Home</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
    background-image: url("images/download.png");
    background-size: cover;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center;
}

.card {
    background: rgba(255,255,255,0.75);
    backdrop-filter: blur(8px);
    border-radius: 12px;
}

/* Navbar Title Style */
.navbar-brand {
    font-size: 40px;
    font-weight: bold;
    text-shadow: 1px 1px 3px white;
}

/* Navbar transparency */
.navbar {
    background: rgba(255,255,255,0.70) !important;
    backdrop-filter: blur(6px);
}
</style>
</head>

<body>

<!-- Center Navbar Title -->
<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container-fluid d-flex justify-content-center">
      <a class="navbar-brand" href="index.jsp"> Pet-Care Manager </a>
  </div>
</nav>

<!-- Dashboard Cards Section -->
<div class="container mt-5">
    <div class="row g-4 justify-content-center">

        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5>Dogs</h5>
                <a href="dog?action=add" class="btn btn-primary btn-sm">Add Dog</a>
                <a href="dog?action=list" class="btn btn-outline-primary btn-sm">View Dogs</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5>Appointments</h5>
                <a href="appointment?action=add" class="btn btn-primary btn-sm">Add Appointment</a>
                <a href="appointment?action=list" class="btn btn-outline-primary btn-sm">View Appointments</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5>Care Logs</h5>
                <a href="careLog?action=add" class="btn btn-primary btn-sm">Add Care Log</a>
                <a href="careLog?action=list" class="btn btn-outline-primary btn-sm">View Care Logs</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5>Expenses</h5>
                <a href="expense?action=add" class="btn btn-primary btn-sm">Add Expense</a>
                <a href="expense?action=list" class="btn btn-outline-primary btn-sm">View Expenses</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h5>Reminders</h5>
                <a href="reminder?action=add" class="btn btn-primary btn-sm">Add Reminder</a>
                <a href="reminder?action=list" class="btn btn-outline-primary btn-sm">View Reminders</a>
            </div>
        </div>

    </div>
</div>

</body>
</html>
