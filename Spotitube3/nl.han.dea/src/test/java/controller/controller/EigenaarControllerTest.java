package controller.controller;

import controller.dtos.EigenaarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

class EigenaarControllerTest {

    private EigenaarController eigenaarControllerUnderTest;

    @BeforeEach
    void setUp() {
        eigenaarControllerUnderTest = new EigenaarController();
    }

    @Test
    void testLogin() {
        // Setup
        final EigenaarDTO eigenaarDTO = new EigenaarDTO();
        eigenaarDTO.setUser("user");
        eigenaarDTO.setPassword("password");

        // Run the test
        final Response result = eigenaarControllerUnderTest.Login(eigenaarDTO);

        // Verify the results
    }
}
