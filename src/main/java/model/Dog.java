package model;

public class Dog {
    private int dogId;
    private int ownerId;
    private String name;
    private String breed;
    private int age;
    private String gender;
    private double weight;
    private String color;

    // Getters & Setters
    public int getDogId() { return dogId; }
    public void setDogId(int dogId) { this.dogId = dogId; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
