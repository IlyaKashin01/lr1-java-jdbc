package JDBC.database;

import java.sql.*;

public class MySQLClient {
    private String URL = "jdbc:mysql://localhost:3306/";
    private String Username;
    private String Password;
    private String db;
    private Connection connection;
    private Statement statement;

    public String request = null;

    public MySQLClient(String user, String password, String db) {
        this.Username = user;
        this.Password = password;
        this.db = db;
    }

    public Connection connect() throws SQLException {

        connection = DriverManager.getConnection((URL + db), Username, Password);
        statement = connection.createStatement();
        if (!connection.isClosed()) {
            System.out.println("Connection");
        }

        return connection;
    }

    public void disconnect() throws SQLException {
        statement.close();
        connection.close();

        if (connection.isClosed()) {
            System.out.println("Disconnection");
        }
    }

    public void createTable(String tb_name, String[] columns) throws SQLException {

        for (int i = 0; i < columns.length; i++) {
            if (i == 0) {
                request = " " + columns[i] + ",";
            } else if (i == columns.length - 1) {
                request += " " + columns[i];
            } else {
                request += " " + columns[i] + ",";
            }
        }

        String sqlcommand = "CREATE TABLE " + tb_name + " (ID INT PRIMARY KEY AUTO_INCREMENT," + request + ");";
        System.out.println(sqlcommand);
        statement.execute(sqlcommand);
        request = null;
    }

    public void insert(String tb_name, String[] columns, String[][] records) throws SQLException {

        String col = null;
        for (int c = 0; c < columns.length; c++) {
            if (c == 0) {
                if(c == columns.length - 1){ col = " " + columns[c]; }
                else col = " " + columns[c] + ",";
            } else if (c == columns.length - 1) {
                col += " " + columns[c];
            } else {
                col += " " + columns[c] + ",";
            }
        }

        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < records[i].length; j++) {
                if (j == 0) {
                    if (j == records.length - 1){request = " " + records[i][j];}
                    else  request = " " + records[i][j] + ",";
                } else if (j == records[i].length - 1) {
                    request += " " + records[i][j];
                } else {
                    request += " " + records[i][j] + ",";
                }
            }
            String sqlcommand = "INSERT INTO " + tb_name + " (" + col + ")" + " VALUES (" + request + ")";
            System.out.println(sqlcommand);
            statement.executeUpdate(sqlcommand);
        }
        request = null;
    }

    public void update(String tb_name, String[] columns, String[] records, String selection) throws SQLException {
        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < records.length; j++) {
                if (j == i) {
                    if (i == 0 && j == 0) {
                        request = " " + columns[i] + " = " + records[j] + ",";
                    } else if (i == columns.length - 1 && j == records.length - 1) {
                        request += " " + columns[i] + " = " + records[j];
                    } else {
                        request += " " + columns[i] + " = " + records[j] + ",";
                    }
                }
            }
        }

        String sqlcommand = "UPDATE " + tb_name + " SET" + request + " WHERE " + selection;
        System.out.println(sqlcommand);
        statement.executeUpdate(sqlcommand);

        request = null;
    }

    public void delete(String tb_name, String selection) throws SQLException {
        String sqlcommand = "DELETE FROM " + tb_name + " WHERE " + selection;
        System.out.println(sqlcommand);
        statement.execute(sqlcommand);
    }

    public void selectUsers(String tb_name) throws SQLException {

        try {
            String command = "SELECT * FROM " + tb_name;
            ResultSet result = statement.executeQuery(command);
            System.out.println(command);
            while (result.next()) {
                int ID = result.getInt("ID");
                String name = result.getNString("name");
                int password = result.getInt("password");
                int phone = result.getInt("phone");
                Date dateregist = result.getDate("dateregist");
                System.out.println(ID + " " + name + " " + password + " " + phone + " " + dateregist);
            }
        } catch (SQLException e) {
            System.out.println("error print");
        }


    }
    public void selectRoles(String tb_name) throws SQLException {
        try {
            String command = "SELECT * FROM " + tb_name;
            ResultSet result = statement.executeQuery(command);
            System.out.println(command);
            while (result.next()) {
                int ID = result.getInt("ID");
                String name = result.getString("name");
                System.out.println(ID + " " + name);
            }
    } catch (SQLException e){
            System.out.println("error print");
        }
    }


}
