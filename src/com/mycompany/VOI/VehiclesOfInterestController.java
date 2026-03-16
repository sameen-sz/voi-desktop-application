package com.mycompany.VOI;

import java.sql.*;
import java.util.ArrayList;

public class VehiclesOfInterestController {
    private Connection conn;

    public VehiclesOfInterestController() {
    try {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        conn = DriverManager.getConnection(
            "jdbc:derby:/Users/sameen_shahbaz/NetBeansProjects/VOI_WebApp/VOI_FULLBACKUP_17JULY/DerbyDB_Backup/VehiclesOfInterest;create=false",
            "VehiclesOfInterest",
            "voi"
        );

        System.out.println("Database connected successfully.");

    } catch (Exception e) {
        System.out.println("Database connection failed:");
        e.printStackTrace();
        conn = null;
    }
}
    
   

    @Override
    protected void finalize() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    private void ensureConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            throw new SQLException("Database connection is not available.");
        }
    }

    public String[][] getAllReasonsForInterest() {
        ArrayList<ArrayList<String>> reasonOfInterestList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reason_for_interest ORDER BY reason");

            while (rs.next()) {
                ArrayList<String> tempRow = new ArrayList<>();
                tempRow.add(rs.getString("reason"));
                tempRow.add(rs.getString("description"));
                reasonOfInterestList.add(tempRow);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading reasons for interest:");
            e.printStackTrace();
        }

        String[][] allReasonOfInterestArr = new String[reasonOfInterestList.size()][2];

        for (int x = 0; x < reasonOfInterestList.size(); x++) {
            ArrayList<String> tempList = reasonOfInterestList.get(x);
            for (int y = 0; y < 2; y++) {
                allReasonOfInterestArr[x][y] = tempList.get(y);
            }
        }

        return allReasonOfInterestArr;
    }

    public String[][] getAllVehicleMakes() {
        ArrayList<String[]> makeList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT make FROM vehicle_make ORDER BY make");

            while (rs.next()) {
                makeList.add(new String[]{rs.getString("make")});
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error retrieving makes:");
            e.printStackTrace();
        }

        return makeList.toArray(new String[0][0]);
    }

    public String[] allVehicleMake() {
        ArrayList<String> makeList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle_make ORDER BY make");

            while (rs.next()) {
                makeList.add(rs.getString("make"));
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading vehicle makes:");
            e.printStackTrace();
        }

        return makeList.toArray(new String[0]);
    }

    public String[][] allVehicleModel() {
        ArrayList<ArrayList<String>> modelList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle_model ORDER BY model, make");

            while (rs.next()) {
                ArrayList<String> tempRow = new ArrayList<>();
                tempRow.add(rs.getString("model"));
                tempRow.add(rs.getString("make"));
                modelList.add(tempRow);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading vehicle models:");
            e.printStackTrace();
        }

        String[][] allModelListArr = new String[modelList.size()][2];

        for (int x = 0; x < modelList.size(); x++) {
            ArrayList<String> tempList = modelList.get(x);
            for (int y = 0; y < 2; y++) {
                allModelListArr[x][y] = tempList.get(y);
            }
        }

        return allModelListArr;
    }

    public String[][] allVehicleOfInterest() {
        ArrayList<ArrayList<String>> vehicleOfInterestList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle_of_interest ORDER BY license_plate");

            while (rs.next()) {
                ArrayList<String> tempRow = new ArrayList<>();
                tempRow.add(rs.getString("license_plate"));
                tempRow.add(rs.getString("reason"));
                tempRow.add(rs.getString("make"));
                tempRow.add(rs.getString("model"));
                tempRow.add(rs.getString("veh_year"));
                tempRow.add(rs.getString("color"));
                tempRow.add(rs.getString("owners_name"));
                tempRow.add(rs.getString("owners_phone"));
                vehicleOfInterestList.add(tempRow);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading vehicles of interest:");
            e.printStackTrace();
        }

        String[][] allVehicleOfInterestListArr = new String[vehicleOfInterestList.size()][8];

        for (int x = 0; x < vehicleOfInterestList.size(); x++) {
            ArrayList<String> tempList = vehicleOfInterestList.get(x);
            for (int y = 0; y < 8; y++) {
                allVehicleOfInterestListArr[x][y] = tempList.get(y);
            }
        }

        return allVehicleOfInterestListArr;
    }

    public String[] allReasonsForInterestKey() {
        ArrayList<String> reasonsForInterestList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT reason FROM reason_for_interest ORDER BY reason");

            while (rs.next()) {
                reasonsForInterestList.add(rs.getString("reason"));
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading reason keys:");
            e.printStackTrace();
        }

        return reasonsForInterestList.toArray(new String[0]);
    }

    public String[] allVehicleMakeKey() {
        ArrayList<String> vehicleMakeList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT make FROM vehicle_make ORDER BY make");

            while (rs.next()) {
                vehicleMakeList.add(rs.getString("make"));
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading make keys:");
            e.printStackTrace();
        }

        return vehicleMakeList.toArray(new String[0]);
    }

    public String[] allVehicleModelKey() {
        ArrayList<String> vehicleModelList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT model FROM vehicle_model ORDER BY model");

            while (rs.next()) {
                vehicleModelList.add(rs.getString("model"));
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading model keys:");
            e.printStackTrace();
        }

        return vehicleModelList.toArray(new String[0]);
    }

    public String[] allVehicleOfInterestKey() {
        ArrayList<String> vehicleOfInterestList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT license_plate FROM vehicle_of_interest ORDER BY license_plate");

            while (rs.next()) {
                vehicleOfInterestList.add(rs.getString("license_plate"));
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error loading vehicle keys:");
            e.printStackTrace();
        }

        return vehicleOfInterestList.toArray(new String[0]);
    }

    public String[] getVehicleOfInterestByLicense(String licensePlate) {
        ArrayList<String> vehicleOfInterest = new ArrayList<>();

        try {
            ensureConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM vehicle_of_interest WHERE license_plate = ?"
            );
            pstmt.setString(1, licensePlate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                vehicleOfInterest.add(rs.getString("license_plate"));
                vehicleOfInterest.add(rs.getString("reason"));
                vehicleOfInterest.add(rs.getString("make"));
                vehicleOfInterest.add(rs.getString("model"));
                vehicleOfInterest.add(rs.getString("veh_year"));
                vehicleOfInterest.add(rs.getString("color"));
                vehicleOfInterest.add(rs.getString("owners_name"));
                vehicleOfInterest.add(rs.getString("owners_phone"));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            System.out.println("Error getting vehicle by license:");
            e.printStackTrace();
        }

        return vehicleOfInterest.toArray(new String[0]);
    }

    public void updateReasonForInterest(String reason, String description) {
        try {
            ensureConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE reason_for_interest SET description = ? WHERE reason = ?"
            );
            pstmt.setString(1, description);
            pstmt.setString(2, reason);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error updating reason for interest:");
            e.printStackTrace();
        }
    }

    public void updateVehicleOfInterest(String licensePlate, String reason, String make,
                                        String model, String veh_year, String color,
                                        String ownersName, String ownersPhone) {
        try {
            ensureConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE vehicle_of_interest SET reason=?, make=?, model=?, veh_year=?, color=?, owners_name=?, owners_phone=? WHERE license_plate=?"
            );

            pstmt.setString(1, reason);
            pstmt.setString(2, make);
            pstmt.setString(3, model);
            pstmt.setString(4, veh_year);
            pstmt.setString(5, color);
            pstmt.setString(6, ownersName);
            pstmt.setString(7, ownersPhone);
            pstmt.setString(8, licensePlate);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error updating vehicle of interest:");
            e.printStackTrace();
        }
    }

    public void createVehicleOfInterest(String licensePlate, String reason, String make,
                                        String model, String veh_year, String color,
                                        String ownersName, String ownersPhone) {
        try {
            ensureConnection();

            String sql = "INSERT INTO vehicle_of_interest " +
                         "(license_plate, reason, make, model, veh_year, color, owners_name, owners_phone) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, licensePlate);
            pstmt.setString(2, reason);
            pstmt.setString(3, make);
            pstmt.setString(4, model);
            pstmt.setString(5, veh_year);
            pstmt.setString(6, color);
            pstmt.setString(7, ownersName);
            pstmt.setString(8, ownersPhone);

            pstmt.executeUpdate();
            pstmt.close();

            System.out.println("Vehicle added successfully.");

        } catch (Exception e) {
            System.out.println("Error creating vehicle of interest:");
            e.printStackTrace();
        }
    }

    public void deleteVehicleOfInterest(String licensePlate) {
        try {
            ensureConnection();
            String sql = "DELETE FROM vehicle_of_interest WHERE license_plate = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, licensePlate);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error deleting vehicle of interest:");
            e.printStackTrace();
        }
    }

    public void insertReasonForInterest(String reason, String description) {
        try {
            ensureConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO reason_for_interest (reason, description) VALUES (?, ?)"
            );
            pstmt.setString(1, reason);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error inserting reason:");
            e.printStackTrace();
        }
    }

    public void deleteReasonForInterest(String reason) {
        try {
            ensureConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM reason_for_interest WHERE reason = ?"
            );
            pstmt.setString(1, reason);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.out.println("Error deleting reason:");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void deleteVehicleModel(String model, String make) throws SQLException {
        ensureConnection();
        String sql = "DELETE FROM vehicle_model WHERE model = ? AND make = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model);
            pstmt.setString(2, make);
            pstmt.executeUpdate();
        }
    }

    public void insertVehicleMake(String make) throws SQLException {
        ensureConnection();
        String sql = "INSERT INTO vehicle_make (make) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, make);
            pstmt.executeUpdate();
        }
    }

    public void insertVehicleModel(String model, String make) throws SQLException {
        ensureConnection();
        String sql = "INSERT INTO vehicle_model (model, make) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model);
            pstmt.setString(2, make);
            pstmt.executeUpdate();
        }
    }

    public void deleteVehicleMake(String make) throws SQLException {
        ensureConnection();
        String sql = "DELETE FROM vehicle_make WHERE make = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, make);
            pstmt.executeUpdate();
        }
    }

    public String[][] getAllVehicleModelsWithMakes() {
        ArrayList<String[]> modelMakeList = new ArrayList<>();

        try {
            ensureConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT model, make FROM vehicle_model ORDER BY make, model");

            while (rs.next()) {
                String model = rs.getString("model");
                String make = rs.getString("make");
                modelMakeList.add(new String[] { model, make });
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error fetching vehicle models with makes:");
            e.printStackTrace();
        }

        String[][] result = new String[modelMakeList.size()][2];
        for (int i = 0; i < modelMakeList.size(); i++) {
            result[i] = modelMakeList.get(i);
        }
        return result;
    }
}