package JDBCDAO.DAO;

import java.util.ArrayList;

public interface DAO<Entity, Key> {
    boolean create(Entity model);
    ArrayList<Entity> readAll();
    Entity read(Integer id);
    boolean update(Entity model);
    boolean delete(Integer id);
}
