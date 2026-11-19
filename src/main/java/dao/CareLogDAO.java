package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.CareLog;
import util.DBConnection;

public class CareLogDAO {

    // INSERT: add new care log
    public boolean addCareLog(CareLog log) {
        String sql = "INSERT INTO care_logs (dog_id, date, time, activity_type, description) VALUES (?, ?, ?, ?, ?)";
        boolean inserted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, log.getDogId());
            ps.setDate(2, log.getDate());
            ps.setTime(3, log.getTime());
            ps.setString(4, log.getActivityType());
            ps.setString(5, log.getDescription());

            int rows = ps.executeUpdate();
            inserted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in addCareLog: " + e.getMessage());
        }

        return inserted;
    }

    // SELECT: get all care logs
    public List<CareLog> getAllCareLogs() {
        List<CareLog> list = new ArrayList<>();
        String sql = "SELECT log_id, dog_id, date, time, activity_type, description FROM care_logs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CareLog log = new CareLog();
                log.setLogId(rs.getInt("log_id"));
                log.setDogId(rs.getInt("dog_id"));
                log.setDate(rs.getDate("date"));
                log.setTime(rs.getTime("time"));
                log.setActivityType(rs.getString("activity_type"));
                log.setDescription(rs.getString("description"));

                list.add(log);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAllCareLogs: " + e.getMessage());
        }

        return list;
    }

    // SELECT: one care log
    public CareLog getCareLogById(int id) {
        String sql = "SELECT log_id, dog_id, date, time, activity_type, description FROM care_logs WHERE log_id = ?";
        CareLog log = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    log = new CareLog();
                    log.setLogId(rs.getInt("log_id"));
                    log.setDogId(rs.getInt("dog_id"));
                    log.setDate(rs.getDate("date"));
                    log.setTime(rs.getTime("time"));
                    log.setActivityType(rs.getString("activity_type"));
                    log.setDescription(rs.getString("description"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getCareLogById: " + e.getMessage());
        }

        return log;
    }

    // UPDATE: update care log
    public boolean updateCareLog(CareLog log) {
        String sql = "UPDATE care_logs SET dog_id = ?, date = ?, time = ?, activity_type = ?, description = ? "
                   + "WHERE log_id = ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, log.getDogId());
            ps.setDate(2, log.getDate());
            ps.setTime(3, log.getTime());
            ps.setString(4, log.getActivityType());
            ps.setString(5, log.getDescription());
            ps.setInt(6, log.getLogId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in updateCareLog: " + e.getMessage());
        }

        return updated;
    }

    // DELETE: delete care log
    public boolean deleteCareLog(int id) {
        String sql = "DELETE FROM care_logs WHERE log_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            deleted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in deleteCareLog: " + e.getMessage());
        }

        return deleted;
    }
}
