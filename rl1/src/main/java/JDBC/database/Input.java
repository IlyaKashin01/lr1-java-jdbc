package JDBC.database;

import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);

    public static byte info(){
        System.out.println("Click to execute function:");
        System.out.println("create table(1)");
        System.out.println("insert(2)");
        System.out.println("update(3)");
        System.out.println("delete(4)");
        System.out.println("select(5)");
        System.out.println("exit(0)");
        byte act = sc.nextByte();
        return act;
    }

    public static String tableName() {
        //table_name
        System.out.print("Table name: ");
        String table_name = sc.nextLine();
        return table_name;
    }

    public static String[] structureTable() {
        //array name columns and data type
        System.out.print("Count columns: ");
        int size = sc.nextInt();
        sc.nextLine();
        String arr[] = new String[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Name column and data type:");
            arr[i] = sc.nextLine();
        }
        return arr;
    }

    public static String[] tableColumns () {
        //array name columns
        System.out.print("Count columns: ");
        int size = sc.nextInt();
        sc.nextLine();
        String columns[] = new String[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Name column:");
            columns[i] = sc.nextLine();
        }
        return columns;
    }

    public static String[][] arrInsertRecords() {
        //array records
        System.out.print("Count records: ");
        int length1 = sc.nextInt();
        System.out.print("Count values: ");
        int length2 = sc.nextInt();
        sc.nextLine();
        String records[][] = new String[length1][length2];
        for (int i = 0; i < length1; i++) {
            System.out.print("Values for recording:");
            for (int j = 0; j < length2; j++) {
                records[i][j] = sc.nextLine();
            }
        }
        return records;
    }

    public static String[] arrUpdateRecords() {
        //array records
        System.out.print("Count records: ");
        int length = sc.nextInt();
        sc.nextLine();
        String records[] = new String[length];
        for (int i = 0; i < length; i++) {
            System.out.print("Values for recording:");
            records[i] = sc.nextLine();
        }
        return records;
    }

    public static String selection() {
        //"WHERE" in request
        System.out.print("Selection: ");
        String selection = sc.nextLine();
        return selection;
    }
}
