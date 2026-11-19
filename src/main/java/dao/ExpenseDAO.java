package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Expense;
import util.DBConnection;

public class ExpenseDAO {

    // INSERT: add new expense
    public boolean addExpense(Expense exp) {
        String sql = "INSERT INTO expenses (dog_id, date, category, amount, notes) "
                   + "VALUES (?, ?, ?, ?, ?)";
        boolean inserted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, exp.getDogId());
            ps.setDate(2, exp.getDate());
            ps.setString(3, exp.getCategory());
            ps.setDouble(4, exp.getAmount());
            ps.setString(5, exp.getNotes());

            int rows = ps.executeUpdate();
            inserted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in addExpense: " + e.getMessage());
        }

        return inserted;
    }

    // SELECT: get all expenses
    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT expense_id, dog_id, date, category, amount, notes FROM expenses";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Expense exp = new Expense();
                exp.setExpenseId(rs.getInt("expense_id"));
                exp.setDogId(rs.getInt("dog_id"));
                exp.setDate(rs.getDate("date"));
                exp.setCategory(rs.getString("category"));
                exp.setAmount(rs.getDouble("amount"));
                exp.setNotes(rs.getString("notes"));

                list.add(exp);
            }

        } catch (SQLException e) {
            System.out.println("Error in getAllExpenses: " + e.getMessage());
        }

        return list;
    }

    // SELECT: one expense
    public Expense getExpenseById(int id) {
        String sql = "SELECT expense_id, dog_id, date, category, amount, notes FROM expenses WHERE expense_id = ?";
        Expense exp = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exp = new Expense();
                    exp.setExpenseId(rs.getInt("expense_id"));
                    exp.setDogId(rs.getInt("dog_id"));
                    exp.setDate(rs.getDate("date"));
                    exp.setCategory(rs.getString("category"));
                    exp.setAmount(rs.getDouble("amount"));
                    exp.setNotes(rs.getString("notes"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getExpenseById: " + e.getMessage());
        }

        return exp;
    }

    // UPDATE: update expense
    public boolean updateExpense(Expense exp) {
        String sql = "UPDATE expenses SET dog_id = ?, date = ?, category = ?, amount = ?, notes = ? "
                   + "WHERE expense_id = ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, exp.getDogId());
            ps.setDate(2, exp.getDate());
            ps.setString(3, exp.getCategory());
            ps.setDouble(4, exp.getAmount());
            ps.setString(5, exp.getNotes());
            ps.setInt(6, exp.getExpenseId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in updateExpense: " + e.getMessage());
        }

        return updated;
    }

    // DELETE: delete expense
    public boolean deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE expense_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            deleted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in deleteExpense: " + e.getMessage());
        }

        return deleted;
    }
}
