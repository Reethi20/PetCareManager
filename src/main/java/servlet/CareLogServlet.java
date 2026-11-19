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

import dao.CareLogDAO;
import model.CareLog;

@WebServlet("/careLog")
public class CareLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CareLogDAO careLogDAO;

    @Override
    public void init() throws ServletException {
        careLogDAO = new CareLogDAO();
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
                editLog(request, response);
                break;
            case "delete":
                deleteLog(request, response);
                break;
            case "list":
            default:
                listLogs(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveLog(request, response);
        } else {
            response.sendRedirect("careLog?action=list");
        }
    }

    private void listLogs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<CareLog> logs = careLogDAO.getAllCareLogs();
        request.setAttribute("careLogs", logs);
        request.getRequestDispatcher("/WEB-INF/listCareLogs.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, CareLog log)
            throws ServletException, IOException {

        request.setAttribute("careLog", log);
        request.getRequestDispatcher("/WEB-INF/careLogForm.jsp").forward(request, response);
    }

    private void editLog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int logId = 0;
        try { logId = Integer.parseInt(idStr); } catch (NumberFormatException e) { logId = 0; }

        CareLog log = careLogDAO.getCareLogById(logId);
        showForm(request, response, log);
    }

    private void deleteLog(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idStr = request.getParameter("id");
        int logId = 0;
        try { logId = Integer.parseInt(idStr); } catch (NumberFormatException e) { logId = 0; }

        careLogDAO.deleteCareLog(logId);
        response.sendRedirect("careLog?action=list");
    }

    private void saveLog(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String logIdStr = request.getParameter("logId");
        String dogIdStr = request.getParameter("dogId");
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
        String activityType = request.getParameter("activityType");
        String description = request.getParameter("description");

        int logId = 0;
        int dogId = 0;

        try { logId = (logIdStr == null || logIdStr.isEmpty()) ? 0 : Integer.parseInt(logIdStr); } catch (Exception e) { logId = 0; }
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

        CareLog log = new CareLog();
        log.setLogId(logId);
        log.setDogId(dogId);
        log.setDate(date);
        log.setTime(time);
        log.setActivityType(activityType);
        log.setDescription(description);

        if (logId == 0) {
            careLogDAO.addCareLog(log);
        } else {
            careLogDAO.updateCareLog(log);
        }

        response.sendRedirect("careLog?action=list");
    }
}
