package com.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    public void addGuest(Guest guest) throws SQLException {
        String sql = "INSERT INTO Guests (name, email, phone_number) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhoneNumber());
            stmt.executeUpdate();
        }
    }

    public List<Guest> getAllGuests() throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM Guests";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Guest guest = new Guest(
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone_number"));
                guests.add(guest);
            }
        }
        return guests;
    }

    public void updateGuest(Guest guest) throws SQLException {
        String sql = "UPDATE Guests SET name = ?, email = ?, phone_number = ? WHERE guest_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhoneNumber());
            stmt.setInt(4, guest.getGuestId());
            stmt.executeUpdate();
        }
    }

    public void deleteGuest(int guestId) throws SQLException {
        String sql = "DELETE FROM Guests WHERE guest_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, guestId);
            stmt.executeUpdate();
        }
    }
}


