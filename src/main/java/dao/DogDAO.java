package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dog;
import util.DBConnection;

public class DogDAO {

    // INSERT: add a new dog
    public boolean addDog(Dog dog) {
        String sql = "INSERT INTO dogs (owner_id, name, breed, age, gender, weight, color) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean rowInserted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dog.getOwnerId());
            ps.setString(2, dog.getName());
            ps.setString(3, dog.getBreed());
            ps.setInt(4, dog.getAge());
            ps.setString(5, dog.getGender());
            ps.setDouble(6, dog.getWeight());
            ps.setString(7, dog.getColor());

            int rows = ps.executeUpdate();
            rowInserted = rows > 0;

            System.out.println("addDog rows = " + rows); // debug

        } catch (SQLException e) {
            System.out.println("Error in addDog: " + e.getMessage());
        }

        return rowInserted;
    }

    // SELECT: get all dogs
    public List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        String sql = "SELECT dog_id, owner_id, name, breed, age, gender, weight, color FROM dogs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dog dog = new Dog();
                dog.setDogId(rs.getInt("dog_id"));
                dog.setOwnerId(rs.getInt("owner_id"));
                dog.setName(rs.getString("name"));
                dog.setBreed(rs.getString("breed"));
                dog.setAge(rs.getInt("age"));
                dog.setGender(rs.getString("gender"));
                dog.setWeight(rs.getDouble("weight"));
                dog.setColor(rs.getString("color"));

                dogs.add(dog);
            }

            System.out.println("getAllDogs size = " + dogs.size()); // debug

        } catch (SQLException e) {
            System.out.println("Error in getAllDogs: " + e.getMessage());
        }

        return dogs;
    }

    // SELECT: get one dog by id
    public Dog getDogById(int id) {
        Dog dog = null;
        String sql = "SELECT dog_id, owner_id, name, breed, age, gender, weight, color "
                   + "FROM dogs WHERE dog_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dog = new Dog();
                    dog.setDogId(rs.getInt("dog_id"));
                    dog.setOwnerId(rs.getInt("owner_id"));
                    dog.setName(rs.getString("name"));
                    dog.setBreed(rs.getString("breed"));
                    dog.setAge(rs.getInt("age"));
                    dog.setGender(rs.getString("gender"));
                    dog.setWeight(rs.getDouble("weight"));
                    dog.setColor(rs.getString("color"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getDogById: " + e.getMessage());
        }

        return dog;
    }

    // UPDATE: modify existing dog
    public boolean updateDog(Dog dog) {
        String sql = "UPDATE dogs "
                   + "SET owner_id = ?, name = ?, breed = ?, age = ?, gender = ?, weight = ?, color = ? "
                   + "WHERE dog_id = ?";
        boolean updated = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dog.getOwnerId());
            ps.setString(2, dog.getName());
            ps.setString(3, dog.getBreed());
            ps.setInt(4, dog.getAge());
            ps.setString(5, dog.getGender());
            ps.setDouble(6, dog.getWeight());
            ps.setString(7, dog.getColor());
            ps.setInt(8, dog.getDogId());

            int rows = ps.executeUpdate();
            updated = rows > 0;

            System.out.println("updateDog rows = " + rows); // debug

        } catch (SQLException e) {
            System.out.println("Error in updateDog: " + e.getMessage());
        }

        return updated;
    }

    // DELETE: remove dog by id
    public boolean deleteDog(int id) {
        String sql = "DELETE FROM dogs WHERE dog_id = ?";
        boolean deleted = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            deleted = rows > 0;

            System.out.println("deleteDog rows = " + rows); // debug

        } catch (SQLException e) {
            System.out.println("Error in deleteDog: " + e.getMessage());
        }

        return deleted;
    }
}
