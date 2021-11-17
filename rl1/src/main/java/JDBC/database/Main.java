package JDBC.database;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MySQLClient client = new MySQLClient("root", "root", "db");
        try {
            client.connect();
        } catch (SQLException e) {
            System.out.println("No connect");
        }

        boolean condition = false;
        while (!condition) {
            byte act = Input.info();

            switch (act) {
                case 1:
                    try {
                        String table_name = Input.tableName();
                        String arr[] = Input.structureTable();

                        client.createTable(table_name, arr);
                    } catch (SQLException e) {
                        System.out.println("No table data");
                    }
                    break;
                case 2:
                    try {
                        String table_name = Input.tableName();
                        String columns[] = Input.tableColumns();
                        String records[][] = Input.arrInsertRecords();

                        client.insert(table_name, columns, records);
                    } catch (SQLException e) {
                        System.out.println("No request data");
                    }
                    break;
                case 3:
                    try {
                        String table_name = Input.tableName();
                        String columns[] = Input.tableColumns();
                        String records[] = Input.arrUpdateRecords();
                        String selection = Input.selection();

                        client.update(table_name, columns, records, selection);
                    } catch (SQLException e) {
                        System.out.println("No request data");
                    }
                    break;
                case 4:
                    try {
                        String table_name = Input.tableName();
                        String selection = Input.selection();

                        client.delete(table_name, selection);
                    } catch (SQLException e) {
                        System.out.println("No request data");
                    }
                    break;
                case 5:
                    try {
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Show full table \"users\"(1) or show full table \"roles\"(2): ");
                        int option = sc.nextInt();

                        if (option == 1) {
                            client.selectUsers("users");
                        } else if (option == 2) {
                            client.selectRoles("roles");
                        }
                    } catch (SQLException e) {
                        System.out.println("No request data");
                    }
                    break;
            }
        }
        try {
            client.disconnect();
        } catch (SQLException e) {
            System.out.println("No disconnect");
        }
    }
}
