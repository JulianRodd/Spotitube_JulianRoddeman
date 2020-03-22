package Datasource.DAOs;

import Domain.Eigenaar;

public interface EigenaarDAO {
    Eigenaar select(String pk);
    void update(Eigenaar eigenaar);
    Eigenaar getEigenaarMetToken(String token);
}
