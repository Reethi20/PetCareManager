package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Appointment;
import util.DBConnection;

public class AppointmentDAO {

    public boolean addAppointment(Appointment appt) {
        String sql = "INSERT INTO appointments (dog_id, type, date, time, status, notes) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        boolean inserted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appt.getDogId());
            ps.setString(2, appt.getType());
            ps.setDate(3, appt.getDate());
            ps.setTime(4, appt.getTime());
            ps.setString(5, appt.getStatus());
            ps.setString(6, appt.getNotes());

            int rows = ps.executeUpdate();
            inserted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in addAppointment: " + e.getMessage());
        }

        return inserted;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT appointment_id, dog_id, type, date, time, status, notes FROM appointments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setDogId(rs.getInt("dog_id"));
                a.setType(rs.getString("type"));
                a.setDate(rs.getDate("date"));
                a.setTime(rs.getTime("time"));
                a.setStatus(rs.getString("status"));
                a.setNotes(rs.getString("notes"));

                list.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error in getAllAppointments: " + e.getMessage());
        }

        return list;
    }

    public Appointment getAppointmentById(int id) {
        Appointment a = null;
        String sql = "SELECT appointment_id, dog_id, type, date, time, status, notes "
                   + "FROM appointments WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new Appointment();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setDogId(rs.getInt("dog_id"));
                    a.setType(rs.getString("type"));
                    a.setDate(rs.getDate("date"));
                    a.setTime(rs.getTime("time"));
                    a.setStatus(rs.getString("status"));
                    a.setNotes(rs.getString("notes"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getAppointmentById: " + e.getMessage());
        }

        return a;
    }

    public boolean updateAppointment(Appointment appt) {
        String sql = "UPDATE appointments "
                   + "SET dog_id = ?, type = ?, date = ?, time = ?, status = ?, notes = ? "
                   + "WHERE appointment_id = ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appt.getDogId());
            ps.setString(2, appt.getType());
            ps.setDate(3, appt.getDate());
            ps.setTime(4, appt.getTime());
            ps.setString(5, appt.getStatus());
            ps.setString(6, appt.getNotes());
            ps.setInt(7, appt.getAppointmentId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in updateAppointment: " + e.getMessage());
        }

        return updated;
    }

    public boolean deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            deleted = rows > 0;

        } catch (SQLException e) {
            System.out.println("Error in deleteAppointment: " + e.getMessage());
        }

        return deleted;
    }
}
