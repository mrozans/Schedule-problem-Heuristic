package Data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest
{
    @Nested
    class calculateValue
    {
        @Test
        @DisplayName("Invalid")
        void test1() {
            Range range = new Range("8:01");
            assertEquals(range.value, -1);

        }

        @Test
        @DisplayName("Correct value")
        void test2() {
            Range range = new Range("0:30");
            assertEquals(range.value, 2);

        }

        @Test
        @DisplayName("Correct value")
        void test3() {
            Range range = new Range("2:30");
            assertEquals(range.value, 10);

        }
    }
}