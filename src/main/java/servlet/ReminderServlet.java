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

import dao.ReminderDAO;
import model.Reminder;

@WebServlet("/reminder")
public class ReminderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ReminderDAO reminderDAO;

    @Override
    public void init() throws ServletException {
        reminderDAO = new ReminderDAO();
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
                editReminder(request, response);
                break;
            case "delete":
                deleteReminder(request, response);
                break;
            case "list":
            default:
                listReminders(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveReminder(request, response);
        } else {
            response.sendRedirect("reminder?action=list");
        }
    }

    private void listReminders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Reminder> rem = reminderDAO.getAllReminders();
        request.setAttribute("reminders", rem);
        request.getRequestDispatcher("/WEB-INF/listReminders.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Reminder r)
            throws ServletException, IOException {

        request.setAttribute("reminder", r);
        request.getRequestDispatcher("/WEB-INF/reminderForm.jsp").forward(request, response);
    }

    private void editReminder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int reminderId = 0;
        try { reminderId = Integer.parseInt(idStr); } catch (NumberFormatException e) { reminderId = 0; }

        Reminder r = reminderDAO.getReminderById(reminderId);
        showForm(request, response, r);
    }

    private void deleteReminder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idStr = request.getParameter("id");
        int reminderId = 0;
        try { reminderId = Integer.parseInt(idStr); } catch (NumberFormatException e) { reminderId = 0; }

        reminderDAO.deleteReminder(reminderId);
        response.sendRedirect("reminder?action=list");
    }

    private void saveReminder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String reminderIdStr = request.getParameter("reminderId");
        String dogIdStr = request.getParameter("dogId");
        String reminderType = request.getParameter("reminderType");
        String title = request.getParameter("title");
        String msg = request.getParameter("msg");
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
        String status = request.getParameter("status");

        int reminderId = 0;
        int dogId = 0;
        int ownerId = 1; // static for now

        try { reminderId = (reminderIdStr == null || reminderIdStr.isEmpty()) ? 0 : Integer.parseInt(reminderIdStr); } catch (Exception e) { reminderId = 0; }
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
            // ignore
        }

        if (status == null || status.isEmpty()) {
            status = "PENDING";
        }

        Reminder r = new Reminder();
        r.setReminderId(reminderId);
        r.setOwnerId(ownerId);
        r.setDogId(dogId);
        r.setReminderType(reminderType);
        r.setTitle(title);
        r.setMessage(msg);
        r.setReminderDate(date);
        r.setReminderTime(time);
        r.setStatus(status);

        if (reminderId == 0) {
            reminderDAO.addReminder(r);
        } else {
            reminderDAO.updateReminder(r);
        }

        response.sendRedirect("reminder?action=list");
    }
}
