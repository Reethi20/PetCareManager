package model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointmentId;
    private int dogId;
    private String type;   // Vet, Grooming, Training, etc.
    private Date date;
    private Time time;
    private String status; // Upcoming, Completed, Cancelled
    private String notes;

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public int getDogId() { return dogId; }
    public void setDogId(int dogId) { this.dogId = dogId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Time getTime() { return time; }
    public void setTime(Time time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
