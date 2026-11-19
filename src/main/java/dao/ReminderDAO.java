package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Reminder;
import util.DBConnection;

public class ReminderDAO {

    // INSERT: add new reminder
    public boolean addReminder(Reminder r) {
        String sql = "INSERT INTO reminders (owner_id, dog_id, reminder_type, title, message, "
                   + "reminder_date, reminder_time, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean inserted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getOwnerId());
            ps.setInt(2, r.getDogId());
            ps.setString(3, r.getReminderType());
            ps.setString(4, r.getTitle());
            ps.setString(5, r.getMessage());
            ps.setDate(6, r.getReminderDate());
            ps.setTime(7, r.getReminderTime());
            ps.setString(8, r.getStatus());

            int rows = ps.executeUpdate();
            inserted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in addReminder: " + e.getMessage());
        }

        return inserted;
    }

    // SELECT: get all reminders
    public List<Reminder> getAllReminders() {
        List<Reminder> list = new ArrayList<>();
        String sql = "SELECT reminder_id, owner_id, dog_id, reminder_type, title, message, "
                   + "reminder_date, reminder_time, status FROM reminders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reminder r = new Reminder();
                r.setReminderId(rs.getInt("reminder_id"));
                r.setOwnerId(rs.getInt("owner_id"));
                r.setDogId(rs.getInt("dog_id"));
                r.setReminderType(rs.getString("reminder_type"));
                r.setTitle(rs.getString("title"));
                r.setMessage(rs.getString("message"));
                r.setReminderDate(rs.getDate("reminder_date"));
                r.setReminderTime(rs.getTime("reminder_time"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error in getAllReminders: " + e.getMessage());
        }

        return list;
    }

    // SELECT: one reminder
    public Reminder getReminderById(int id) {
        String sql = "SELECT reminder_id, owner_id, dog_id, reminder_type, title, message, "
                   + "reminder_date, reminder_time, status FROM reminders WHERE reminder_id = ?";
        Reminder r = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    r = new Reminder();
                    r.setReminderId(rs.getInt("reminder_id"));
                    r.setOwnerId(rs.getInt("owner_id"));
                    r.setDogId(rs.getInt("dog_id"));
                    r.setReminderType(rs.getString("reminder_type"));
                    r.setTitle(rs.getString("title"));
                    r.setMessage(rs.getString("message"));
                    r.setReminderDate(rs.getDate("reminder_date"));
                    r.setReminderTime(rs.getTime("reminder_time"));
                    r.setStatus(rs.getString("status"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getReminderById: " + e.getMessage());
        }

        return r;
    }

    // UPDATE: update reminder
    public boolean updateReminder(Reminder r) {
        String sql = "UPDATE reminders SET owner_id = ?, dog_id = ?, reminder_type = ?, title = ?, "
                   + "message = ?, reminder_date = ?, reminder_time = ?, status = ? "
                   + "WHERE reminder_id = ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getOwnerId());
            ps.setInt(2, r.getDogId());
            ps.setString(3, r.getReminderType());
            ps.setString(4, r.getTitle());
            ps.setString(5, r.getMessage());
            ps.setDate(6, r.getReminderDate());
            ps.setTime(7, r.getReminderTime());
            ps.setString(8, r.getStatus());
            ps.setInt(9, r.getReminderId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in updateReminder: " + e.getMessage());
        }

        return updated;
    }

    // DELETE: delete reminder
    public boolean deleteReminder(int id) {
        String sql = "DELETE FROM reminders WHERE reminder_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            deleted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in deleteReminder: " + e.getMessage());
        }

        return deleted;
    }
}
