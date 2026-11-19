package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AppointmentDAO;
import model.Appointment;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                showForm(request, response, null);
                break;
            case "edit":
                editAppointment(request, response);
                break;
            case "delete":
                deleteAppointment(request, response);
                break;
            case "list":
            default:
                listAppointments(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveAppointment(request, response);
        } else {
            response.sendRedirect("appointment?action=list");
        }
    }

    private void listAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Appointment> list = appointmentDAO.getAllAppointments();
        request.setAttribute("appointments", list);
        request.getRequestDispatcher("/WEB-INF/listAppointments.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Appointment appt)
            throws ServletException, IOException {

        request.setAttribute("appointment", appt);
        request.getRequestDispatcher("/WEB-INF/appointmentForm.jsp").forward(request, response);
    }

    private void editAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int apptId = 0;
        try { apptId = Integer.parseInt(idStr); } catch (NumberFormatException e) { apptId = 0; }

        Appointment appt = appointmentDAO.getAppointmentById(apptId);
        showForm(request, response, appt);
    }

    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idStr = request.getParameter("id");
        int apptId = 0;
        try { apptId = Integer.parseInt(idStr); } catch (NumberFormatException e) { apptId = 0; }

        appointmentDAO.deleteAppointment(apptId);
        response.sendRedirect("appointment?action=list");
    }

    private void saveAppointment(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String apptIdStr = request.getParameter("appointmentId");
        String dogIdStr = request.getParameter("dogId");
        String type = request.getParameter("type");
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
        String status = request.getParameter("status");
        String notes = request.getParameter("notes");

        int apptId = 0;
        int dogId = 0;
        try { apptId = (apptIdStr == null || apptIdStr.isEmpty()) ? 0 : Integer.parseInt(apptIdStr); } catch (Exception e) { apptId = 0; }
        try { dogId = Integer.parseInt(dogIdStr); } catch (Exception e) { dogId = 0; }

        Date date = null;
        Time time = null;
        try {
            if (dateStr != null && !dateStr.isEmpty()) {
                date = Date.valueOf(dateStr);
            }
            if (timeStr != null && !timeStr.isEmpty()) {
                time = Time.valueOf(timeStr + ":00");
            }
        } catch (IllegalArgumentException e) {
            // ignore bad format
        }

        if (status == null || status.isEmpty()) {
            status = "Upcoming";
        }

        Appointment appt = new Appointment();
        appt.setAppointmentId(apptId);
        appt.setDogId(dogId);
        appt.setType(type);
        appt.setDate(date);
        appt.setTime(time);
        appt.setStatus(status);
        appt.setNotes(notes);

        if (apptId == 0) {
            appointmentDAO.addAppointment(appt);
        } else {
            appointmentDAO.updateAppointment(appt);
        }

        response.sendRedirect("appointment?action=list");
    }
}
