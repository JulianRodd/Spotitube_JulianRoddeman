package domain;

import org.junit.jupiter.api.BeforeEach;

class LiedTest {

    private Lied liedUnderTest;

    @BeforeEach
    void setUp() {
        liedUnderTest = new Lied(0, "titel", "url", 0, false, "performer", "album");
    }
}
