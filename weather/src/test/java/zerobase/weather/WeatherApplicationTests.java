package zerobase.weather;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherApplicationTests {

    @DisplayName("01. equal test")
    @Test
    public void test_01() {
        assertEquals(1, 1);
    }

    @DisplayName("02. null test")
    @Test
    public void test_02(){
        assertNull(null);
    }

    @DisplayName("03. true test")
    @Test
    public void test_03(){
        assertTrue(true);
    }
}
