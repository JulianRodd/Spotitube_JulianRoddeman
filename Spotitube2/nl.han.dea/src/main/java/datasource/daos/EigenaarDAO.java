package datasource.daos;

import domain.Eigenaar;

public interface EigenaarDAO {
    Eigenaar select(String pk);
    void update(Eigenaar eigenaar);
    Eigenaar getEigenaarMetToken(String token);
}
