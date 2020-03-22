package Datasource.DAOs;

import java.util.List;
import java.util.Objects;

public interface DAO {
     List<Object> selectAll();
     Object select(int pk);
     void insert(Object object);
     void update(Object object);
     void delete(int pk);
}
