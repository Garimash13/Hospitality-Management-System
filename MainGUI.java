package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainGUI extends JFrame {

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/hospitality_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Sonudeepa81#"; // Change this to your MySQL password

    public MainGUI() {
        // Set up the frame
        setTitle("Hotel Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create buttons
        JButton btnAddHotel = new JButton("Add Hotel");
        JButton btnAddRoom = new JButton("Add Room");
        JButton btnAddGuest = new JButton("Add Guest");
        JButton btnAddReservation = new JButton("Add Reservation");
        JButton btnRetrieveData = new JButton("Retrieve Data");

        // Set layout and add buttons to the frame
        setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, with gaps
        add(btnAddHotel);
        add(btnAddRoom);
        add(btnAddGuest);
        add(btnAddReservation);
        add(btnRetrieveData);

        // Action Listeners for each button
        btnAddHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(3, 2));
                JTextField txtHotelName = new JTextField();
                JTextField txtLocation = new JTextField();
                JTextArea txtAmenities = new JTextArea(3, 20);

                panel.add(new JLabel("Hotel Name:"));
                panel.add(txtHotelName);
                panel.add(new JLabel("Location:"));
                panel.add(txtLocation);
                panel.add(new JLabel("Amenities:"));
                panel.add(new JScrollPane(txtAmenities));

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Hotel", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String hotelName = txtHotelName.getText();
                    String location = txtLocation.getText();
                    String amenities = txtAmenities.getText();

                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "INSERT INTO hotels (name, location, amenities) VALUES (?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setString(1, hotelName);
                            stmt.setString(2, location);
                            stmt.setString(3, amenities);
                            stmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Hotel added successfully!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error adding hotel: " + ex.getMessage());
                    }
                }
            }
        });

        btnAddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField txtRoomNumber = new JTextField();
                JTextField txtRoomType = new JTextField();
                JTextField txtPrice = new JTextField();
                JTextField txtStatus = new JTextField();

                panel.add(new JLabel("Room Number:"));
                panel.add(txtRoomNumber);
                panel.add(new JLabel("Room Type:"));
                panel.add(txtRoomType);
                panel.add(new JLabel("Price:"));
                panel.add(txtPrice);
                panel.add(new JLabel("Status:"));
                panel.add(txtStatus);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Room", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String roomNumber = txtRoomNumber.getText();
                    String roomType = txtRoomType.getText();
                    String price = txtPrice.getText();
                    String status = txtStatus.getText();

                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "INSERT INTO rooms (room_number, room_type, price, status) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setString(1, roomNumber);
                            stmt.setString(2, roomType);
                            stmt.setDouble(3, Double.parseDouble(price));
                            stmt.setString(4, status);
                            stmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Room added successfully!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error adding room: " + ex.getMessage());
                    }
                }
            }
        });

        btnAddGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(3, 2));
                JTextField txtGuestName = new JTextField();
                JTextField txtEmail = new JTextField();
                JTextField txtPhoneNumber = new JTextField();

                panel.add(new JLabel("Guest Name:"));
                panel.add(txtGuestName);
                panel.add(new JLabel("Email:"));
                panel.add(txtEmail);
                panel.add(new JLabel("Phone Number:"));
                panel.add(txtPhoneNumber);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Guest", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String guestName = txtGuestName.getText();
                    String email = txtEmail.getText();
                    String phoneNumber = txtPhoneNumber.getText();

                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "INSERT INTO guests (name, email, phone_number) VALUES (?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setString(1, guestName);
                            stmt.setString(2, email);
                            stmt.setString(3, phoneNumber);
                            stmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Guest added successfully!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error adding guest: " + ex.getMessage());
                    }
                }
            }
        });

        btnAddReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(4, 2));
                JTextField txtGuestId = new JTextField();
                JTextField txtRoomId = new JTextField();
                JTextField txtCheckInDate = new JTextField();
                JTextField txtCheckOutDate = new JTextField();

                panel.add(new JLabel("Guest ID:"));
                panel.add(txtGuestId);
                panel.add(new JLabel("Room ID:"));
                panel.add(txtRoomId);
                panel.add(new JLabel("Check-In Date:"));
                panel.add(txtCheckInDate);
                panel.add(new JLabel("Check-Out Date:"));
                panel.add(txtCheckOutDate);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add Reservation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String guestId = txtGuestId.getText();
                    String roomId = txtRoomId.getText();
                    String checkInDate = txtCheckInDate.getText();
                    String checkOutDate = txtCheckOutDate.getText();

                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "INSERT INTO reservations (guest_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setInt(1, Integer.parseInt(guestId));
                            stmt.setInt(2, Integer.parseInt(roomId));
                            stmt.setDate(3, Date.valueOf(checkInDate));
                            stmt.setDate(4, Date.valueOf(checkOutDate));
                            stmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Reservation added successfully!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error adding reservation: " + ex.getMessage());
                    }
                }
            }
        });

        btnRetrieveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder data = new StringBuilder();

                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    // Retrieve and display hotels
                    data.append("Hotels:\n");
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM hotels")) {
                        while (rs.next()) {
                            data.append("ID: ").append(rs.getInt("hotel_id"))
                                .append(", Name: ").append(rs.getString("name"))
                                .append(", Location: ").append(rs.getString("location"))
                                .append(", Amenities: ").append(rs.getString("amenities"))
                                .append("\n");
                        }
                    }

                    // Retrieve and display rooms
                    data.append("\nRooms:\n");
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM rooms")) {
                        while (rs.next()) {
                            data.append("ID: ").append(rs.getInt("room_id"))
                                .append(", Room Number: ").append(rs.getString("room_number"))
                                .append(", Type: ").append(rs.getString("room_type"))
                                .append(", Price: ").append(rs.getDouble("price"))
                                .append(", Status: ").append(rs.getString("status"))
                                .append("\n");
                        }
                    }

                    // Retrieve and display guests
                    data.append("\nGuests:\n");
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM guests")) {
                        while (rs.next()) {
                            data.append("ID: ").append(rs.getInt("guest_id"))
                                .append(", Name: ").append(rs.getString("name"))
                                .append(", Email: ").append(rs.getString("email"))
                                .append(", Phone Number: ").append(rs.getString("phone_number"))
                                .append("\n");
                        }
                    }

                    // Retrieve and display reservations
                    data.append("\nReservations:\n");
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM reservations")) {
                        while (rs.next()) {
                            data.append("ID: ").append(rs.getInt("reservation_id"))
                                .append(", Guest ID: ").append(rs.getInt("guest_id"))
                                .append(", Room ID: ").append(rs.getInt("room_id"))
                                .append(", Check-In Date: ").append(rs.getDate("check_in_date"))
                                .append(", Check-Out Date: ").append(rs.getDate("check_out_date"))
                                .append("\n");
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage());
                }

                JTextArea textArea = new JTextArea(data.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(350, 200));

                JOptionPane.showMessageDialog(null, scrollPane, "Retrieved Data", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}


