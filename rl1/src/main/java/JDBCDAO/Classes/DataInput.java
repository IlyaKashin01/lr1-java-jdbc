package JDBCDAO.Classes;

import JDBCDAO.models.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Scanner;

public class DataInput {

    String URL = "jdbc:mysql://localhost:3306/db";
    String Username = "root";
    String Password = "root";

    Connection connection;
    {
        try {
            connection = DriverManager.getConnection(URL, Username, Password);
        } catch (SQLException e) {
            System.out.println("Error connection");
            System.out.println(e);
        }
    }
    final UsersDAO userDAO = new UsersDAO(connection);

    final Scanner sc = new Scanner(System.in);

    public byte info() {
        System.out.println("Click to execute function:");
        System.out.println("all users(1)");
        System.out.println("search user(2)");
        System.out.println("create user(3)");
        System.out.println("change user(4)");
        System.out.println("delete user(5)");
        System.out.println("exit(0)");
        byte act = sc.nextByte();
        return act;
    }

    public RandomAccess all() throws SQLException {

        System.out.println("All users: ");
        ArrayList<Users> users = userDAO.readAll();
        for (Users user : users) {
            System.out.println("Id: " + user.getId());
            System.out.println("name: " + user.getName());
            System.out.println("password: " + user.getPassword());
            System.out.println("phone: " + user.getPhone());
            System.out.println("registration date: " + user.getDateregedit());
            System.out.println("role: " + user.getRole().getName());
            System.out.println();
        }
        return users;
    }

    public boolean search(boolean end) throws SQLException {
        System.out.println("Search. Input id: ");
        int id = sc.nextInt();
        Users user = userDAO.read(id);
        System.out.println("name: " + user.getName());
        System.out.println("password: " + user.getPassword());
        System.out.println("role: " + user.getRole().getName());
        return end = true;
    }

    public boolean create(boolean end) {
        System.out.println("Create user: ");
        Users createuser = new Users();
        System.out.print("Input name: ");
        createuser.setName(sc.next());
        System.out.print("Input password: ");
        createuser.setPassword(sc.next());
        System.out.print("Input id role: ");
        createuser.setRoleId(sc.nextInt());
        System.out.println(userDAO.create(createuser) ? "user created" : "error create user");
        return end = true;
    }

    public boolean update(boolean end) {
        System.out.println("Change user");
        Users updateuser = new Users();
        System.out.print("Input id: ");
        updateuser.setId(sc.nextInt());
        System.out.print("Input name: ");
        updateuser.setName(sc.next());
        System.out.print("Input password: ");
        updateuser.setPassword(sc.next());
        System.out.print("Input id role: ");
        updateuser.setRoleId(sc.nextInt());
        System.out.println(userDAO.update(updateuser) ? "user changed" : "error change user");
        return end = true;
    }

    public boolean delete(boolean end) {
        System.out.println("Delete user. Input name user: ");
        int ID = sc.nextInt();
        System.out.println(userDAO.delete(ID) ? "user deleted" : "error delete user");
        return end = true;
    }
}