
import JDBCDAO.Classes.UsersDAO;
import JDBCDAO.models.Users;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class UsersDAOTest {
    UsersDAO usersDAO;

    Connection connection;

    @Before

    public void before() throws ClassNotFoundException, SQLException {

        String userName = "root";
        String userPassword = "root";
        String url = "jdbc:mysql://localhost:3306/db";
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url, userName, userPassword);
        usersDAO = new UsersDAO(connection);
    }

    @After
    public void after(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        final Users user = usersDAO.read(100);

        Assert.assertEquals(user.getId(), -1);

    }

    @Test

    public void delete() {

        Assert.assertEquals(false, usersDAO.delete(0));

    }

    @Test

    public void update() {

        Assert.assertEquals(usersDAO.update(new Users()), false);

    }
}
