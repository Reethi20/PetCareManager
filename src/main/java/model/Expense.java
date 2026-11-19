package model;

import java.sql.Date;

public class Expense {
    private int expenseId;
    private int dogId;
    private Date date;
    private String category;   // Food, Vet, Grooming, Others
    private double amount;
    private String notes;

    public int getExpenseId() { return expenseId; }
    public void setExpenseId(int expenseId) { this.expenseId = expenseId; }

    public int getDogId() { return dogId; }
    public void setDogId(int dogId) { this.dogId = dogId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
