package JDBCDAO;

import JDBCDAO.Classes.DataInput;
import java.sql.SQLException;


public class Application {
    public static void main(String[] args) {

        DataInput dataInput = new DataInput();
        boolean condition = false;
        while (!condition) {
            DataInput dt = new DataInput();
            byte act = dt.info();
            switch (act) {
                case 0:
                    condition = true;
                    System.out.println("Exit");
                    break;
                case 1:
                    try {
                        dataInput.all();
                    } catch (SQLException e) {
                        System.out.println("Error print all users");
                    }

                    break;
                case 2:
                    try {
                        dataInput.search(false);
                    } catch (SQLException e) {
                        System.out.println("Error search user");
                    }
                    break;
                case 3:
                    dataInput.create(false);
                    break;
                case 4:
                    dataInput.update(false);
                    break;
                case 5:
                    dataInput.delete(false);
                    break;
            }
        }
    }
}
