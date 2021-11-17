package JDBCDAO.Classes;

import JDBCDAO.DAO.DAO;
import JDBCDAO.models.Roles;
import JDBCDAO.models.Users;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO implements DAO<Users, String> {

    private final Connection connection;
    public UsersDAO(final Connection connection){
        this.connection = connection;
    }
    @Override
    public boolean create(@NotNull final Users user) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.INSERT.QUERY)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getPhone());
            statement.setDate(4, (Date) user.getDateregedit());
            statement.setInt(5, user.getRoleId());
            result = statement.executeQuery().next();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<Users> readAll() {
            final ArrayList<Users> userList = new ArrayList<>();
            try(PreparedStatement statement = connection.prepareStatement(SQLUser.GET.QUERY)){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){

                    Users user = new Users();
                    user.setId(Integer.parseInt(resultSet.getString("id")));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPhone(resultSet.getInt("phone"));
                    user.setDateregedit(resultSet.getDate("dateregist"));
                    user.setRoleId(Integer.parseInt(resultSet.getString("rol_id")));
                    user.setRole(new Roles(Integer.parseInt(resultSet.getString("rol_id")), resultSet.getString("name")));
                    userList.add(user);

                }

            } catch (Exception ex) {

                System.out.println("Error...");

                System.out.println(ex);

            }

            return userList;
    }

    @Override
    public Users read(Integer id) {
        Users result = new Users();
        result.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement(SQLUser.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet res = statement.executeQuery();
            if (res.next()){
                result.setId(Integer.parseInt(res.getString("id")));
                result.setName(res.getString("name"));
                result.setPassword(res.getString("password"));
                result.setPassword(res.getString("phone"));
                result.setDateregedit(res.getDate("dateregist"));
                result.setRoleId(Integer.parseInt(res.getString("rol_id")));
                result.setRole(new Roles( Integer.parseInt(res.getString("rol_id")), res.getString("name")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NotNull final Users user) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLUser.UPDATE.QUERY)) {
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NotNull final Integer id) {
        boolean result = false;

        if(read(id).getId() == -1){
            return result;
        }

        try(PreparedStatement statement = connection.prepareStatement(SQLUser.DELETE.QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
            result = true;
        } catch (Exception ex){
            System.out.println("Error...");
            System.out.println(ex);
        }

        return result;
    }

    enum SQLUser{
        GET("SELECT u.id, u.name, u.password, u.phone, u.dateregist, r.id AS rol_id, r.id AS rol_id, r.role AS name, r.role FROM users as u LEFT JOIN roles AS r ON u.role = r.id WHERE u.name = (?)"),
        INSERT("INSERT INTO users (id, name, password, phone, dateregist, role) VALUES(DEFAULT, (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM users WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE users SET password = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        SQLUser (String QUERY) {this.QUERY = QUERY;}
    }
}
