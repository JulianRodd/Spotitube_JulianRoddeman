package datasource.daos;

import java.util.List;

public interface DAO {
     List<Object> selectAll();
     Object select(int pk);
     void insert(Object object);
     void update(Object object);
     void delete(int pk);
}
