package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExpenseDAO;
import model.Expense;

@WebServlet("/expense")
public class ExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ExpenseDAO expenseDAO;

    @Override
    public void init() throws ServletException {
        expenseDAO = new ExpenseDAO();
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
                editExpense(request, response);
                break;
            case "delete":
                deleteExpense(request, response);
                break;
            case "list":
            default:
                listExpenses(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveExpense(request, response);
        } else {
            response.sendRedirect("expense?action=list");
        }
    }

    private void listExpenses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Expense> list = expenseDAO.getAllExpenses();
        request.setAttribute("expenses", list);
        request.getRequestDispatcher("/WEB-INF/listExpenses.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Expense exp)
            throws ServletException, IOException {

        request.setAttribute("expense", exp);
        request.getRequestDispatcher("/WEB-INF/expenseForm.jsp").forward(request, response);
    }

    private void editExpense(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int expId = 0;
        try { expId = Integer.parseInt(idStr); } catch (NumberFormatException e) { expId = 0; }

        Expense exp = expenseDAO.getExpenseById(expId);
        showForm(request, response, exp);
    }

    private void deleteExpense(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idStr = request.getParameter("id");
        int expId = 0;
        try { expId = Integer.parseInt(idStr); } catch (NumberFormatException e) { expId = 0; }

        expenseDAO.deleteExpense(expId);
        response.sendRedirect("expense?action=list");
    }

    private void saveExpense(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String expIdStr = request.getParameter("expenseId");
        String dogIdStr = request.getParameter("dogId");
        String dateStr = request.getParameter("date");
        String category = request.getParameter("category");
        String amountStr = request.getParameter("amount");
        String notes = request.getParameter("notes");

        int expId = 0;
        int dogId = 0;
        double amount = 0.0;

        try { expId = (expIdStr == null || expIdStr.isEmpty()) ? 0 : Integer.parseInt(expIdStr); } catch (Exception e) { expId = 0; }
        try { dogId = Integer.parseInt(dogIdStr); } catch (Exception e) { dogId = 0; }
        try { amount = Double.parseDouble(amountStr); } catch (Exception e) { amount = 0.0; }

        Date date = null;
        try {
            if (dateStr != null && !dateStr.isEmpty()) {
                date = Date.valueOf(dateStr);
            }
        } catch (IllegalArgumentException e) {
            // ignore
        }

        Expense exp = new Expense();
        exp.setExpenseId(expId);
        exp.setDogId(dogId);
        exp.setDate(date);
        exp.setCategory(category);
        exp.setAmount(amount);
        exp.setNotes(notes);

        if (expId == 0) {
            expenseDAO.addExpense(exp);
        } else {
            expenseDAO.updateExpense(exp);
        }

        response.sendRedirect("expense?action=list");
    }
}
