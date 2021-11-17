package JDBCDAO.Classes;

import JDBCDAO.DAO.DAO;
import JDBCDAO.models.Roles;
import JDBCDAO.models.Users;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

public class RolesDAO implements DAO<Roles, String> {
    private final Connection connection;
    public RolesDAO(final Connection connection){
        this.connection = connection;
    }

    private boolean isExist(@NotNull final Integer id) {
        return read(id).getId() != -1;
    }

    @Override
    public boolean create(@NotNull final Roles role) {
        if (isExist(role.getId())) return false;

        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRoles.ADD.QUERY)) {
            statement.setString(1, role.getName());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Roles> readAll() {
        final ArrayList<Roles> roleList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SQLRoles.GET.QUERY)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                Roles role = new Roles();
                role.setId(Integer.parseInt(resultSet.getString("id")));
                role.setName(resultSet.getString("name"));
                roleList.add(role);
            }

        } catch (Exception ex) {
            System.out.println("Error...");
            System.out.println(ex);
        }

        return roleList;
    }

    @Override
    public Roles read(@NotNull final Integer id) {
        final Roles result = new Roles();
        result.setId(id);

        try (PreparedStatement statement = connection.prepareStatement(SQLRoles.GET.QUERY)) {

            statement.setInt(1, id);

            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(rs.getInt("id"));
            } else {
                result.setName("entity not exist in phone_models");
                result.setId(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NotNull final Roles role) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLRoles.UPDATE.QUERY)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
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

        try(PreparedStatement statement = connection.prepareStatement(SQLRoles.DELETE.QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
            result = true;
        } catch (Exception ex){
            System.out.println("Error...");
            System.out.println(ex);
        }

        return result;
    }

    enum SQLRoles{
        GET("SELECT id FROM roles WHERE name = (?)"),
        DELETE("DELETE FROM roles WHERE name = (?) RETURNING id"),
        ADD("INSERT INTO roles (id, name) VALUES (DEFAULT, (?)) RETURNING id"),
        UPDATE("UPDATE roles SET name = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        SQLRoles (String QUERY) {this.QUERY = QUERY;}
    }
}
