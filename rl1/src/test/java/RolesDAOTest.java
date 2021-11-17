import JDBCDAO.Classes.RolesDAO;
import JDBCDAO.models.Roles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RolesDAOTest {
    RolesDAO roleDAO;
    Connection connection;

    @Before

    public void before() throws ClassNotFoundException, SQLException {

        String userName = "root";
        String userPassword = "root";
        String url = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url, userName, userPassword);
        roleDAO = new RolesDAO(connection);
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
        final Roles role = roleDAO.read(100);
        Assert.assertEquals(role.getId(), -1);
    }

    @Test
    public void delete() {
        Assert.assertEquals(false, roleDAO.delete(0));
    }

    @Test
    public void update() {
        Assert.assertEquals(roleDAO.update(new Roles()), false);
    }
}
