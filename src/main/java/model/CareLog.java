package model;

import java.sql.Date;
import java.sql.Time;

public class CareLog {
    private int logId;
    private int dogId;
    private Date date;
    private Time time;
    private String activityType;   // Walk, Feed, Bath, Medicine, etc.
    private String description;

    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }

    public int getDogId() { return dogId; }
    public void setDogId(int dogId) { this.dogId = dogId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Time getTime() { return time; }
    public void setTime(Time time) { this.time = time; }

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
