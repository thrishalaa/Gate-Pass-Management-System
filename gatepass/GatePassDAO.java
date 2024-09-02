package gatepass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class GatePassDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gatepass";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234567890";

    public static void createTables() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String createPendingTableQuery = "CREATE TABLE IF NOT EXISTS pending_requests (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "rollno VARCHAR(50) NOT NULL," +
                    "year VARCHAR(10) NOT NULL," +
                    "class_name VARCHAR(50) NOT NULL," +
                    "section VARCHAR(10) NOT NULL," +
                    "reason VARCHAR(500) NOT NULL" +
                    ")";
            statement.execute(createPendingTableQuery);

            String createAcceptedTableQuery = "CREATE TABLE IF NOT EXISTS accepted_requests (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "rollno VARCHAR(50) NOT NULL," +
                    "year VARCHAR(20) NOT NULL," +
                    "class_name VARCHAR(50) NOT NULL," +
                    "section VARCHAR(10) NOT NULL," +
                    "reason VARCHAR(255) NOT NULL" +
                    ")";
            statement.execute(createAcceptedTableQuery);

            String createRejectedTableQuery = "CREATE TABLE IF NOT EXISTS rejected_requests (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "rollno VARCHAR(50) NOT NULL," +
                    "year VARCHAR(20) NOT NULL," +
                    "class_name VARCHAR(50) NOT NULL," +
                    "section VARCHAR(10) NOT NULL," +
                    "reason VARCHAR(255) NOT NULL" +
                    ")";
            statement.execute(createRejectedTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePendingRequest(GatePassRequest request) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO pending_requests (name, rollno, year, class_name, section, reason) VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, request.getName());
            statement.setString(2, request.getRollNo());
            statement.setString(3, request.getYear());
            statement.setString(4, request.getClassName());
            statement.setString(5, request.getSection());
            statement.setString(6, request.getReason());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAcceptedRequest(GatePassRequest request) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO accepted_requests (name, rollno, year, class_name, section, reason) VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, request.getName());
            statement.setString(2, request.getRollNo());
            statement.setString(3, request.getYear());
            statement.setString(4, request.getClassName());
            statement.setString(5, request.getSection());
            statement.setString(6, request.getReason());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveRejectedRequest(GatePassRequest request) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO rejected_requests (name, rollno, year, class_name, section, reason) VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, request.getName());
            statement.setString(2, request.getRollNo());
            statement.setString(3, request.getYear());
            statement.setString(4, request.getClassName());
            statement.setString(5, request.getSection());
            statement.setString(6, request.getReason());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<GatePassRequest> getPendingRequests() {
        List<GatePassRequest> pendingRequests = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM pending_requests")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String rollno = resultSet.getString("rollno");
                String year = resultSet.getString("year");
                String className = resultSet.getString("class_name");
                String section = resultSet.getString("section");
                String reason = resultSet.getString("reason");

                pendingRequests.add(new GatePassRequest(name, rollno, year, className, section, reason));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingRequests;
    }

    public static void update_pending_requests(String n) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM pending_requests WHERE rollno='" + n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}