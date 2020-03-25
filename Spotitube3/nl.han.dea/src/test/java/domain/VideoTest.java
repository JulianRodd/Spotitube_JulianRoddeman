package domain;

import org.junit.jupiter.api.BeforeEach;

class VideoTest {

    private Video videoUnderTest;

    @BeforeEach
    void setUp() {
        videoUnderTest = new Video(0, "titel", "url", 0, false, "performer", "publicatieDatum", "beschrijving", 1);
    }
}
