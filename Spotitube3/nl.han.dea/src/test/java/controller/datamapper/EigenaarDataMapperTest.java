package controller.datamapper;

import controller.dtos.EigenaarDTO;
import controller.dtos.LoginDTO;
import domain.Eigenaar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EigenaarDataMapperTest {

    private EigenaarDataMapper eigenaarDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        eigenaarDataMapperUnderTest = new EigenaarDataMapper();
    }

    @Test
    void testMapToDTO() {
        // Setup
        final Eigenaar eigenaar = new Eigenaar();

        // Run the test
        final LoginDTO result = eigenaarDataMapperUnderTest.mapToDTO(eigenaar);

        // Verify the results
    }

    @Test
    void testMapToDomain() {
        // Setup
        final EigenaarDTO eigenaarDTO = new EigenaarDTO();
        eigenaarDTO.setUser("user");
        eigenaarDTO.setPassword("password");

        // Run the test
        final Eigenaar result = eigenaarDataMapperUnderTest.mapToDomain(eigenaarDTO);

        // Verify the results
    }
}
