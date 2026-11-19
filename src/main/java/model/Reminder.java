package model;

import java.sql.Date;
import java.sql.Time;

public class Reminder {

    private int reminderId;
    private int ownerId;
    private int dogId;
    private String reminderType;   // Vaccination, Vet Visit, etc.
    private String title;
    private String message;
    private Date reminderDate;
    private Time reminderTime;
    private String status;         // PENDING, DONE, MISSED

    public int getReminderId() { return reminderId; }
    public void setReminderId(int reminderId) { this.reminderId = reminderId; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public int getDogId() { return dogId; }
    public void setDogId(int dogId) { this.dogId = dogId; }

    public String getReminderType() { return reminderType; }
    public void setReminderType(String reminderType) { this.reminderType = reminderType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getReminderDate() { return reminderDate; }
    public void setReminderDate(Date reminderDate) { this.reminderDate = reminderDate; }

    public Time getReminderTime() { return reminderTime; }
    public void setReminderTime(Time reminderTime) { this.reminderTime = reminderTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
