package Data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourTest {

    @Nested
    class isValid {
        private Hour open, close;

        @BeforeEach
        void init() {
            open= new Hour("8:00", null, null);
            close = new Hour("15:00", open, null);
        }

        @Test
        @DisplayName("Wrong value")
        void test1() {
            Hour test = new Hour("8:01", null, null);
            assertFalse(test.valid);
        }

        @Test
        @DisplayName("Correct value")
        void test2() {
            Hour test = new Hour("8:00", null, null);
            assertTrue(test.valid);
        }

        @Test
        @DisplayName("Incorrect value1")
        void test3() {
            Hour test = new Hour("7", null, null);
            assertFalse(test.valid);
        }

        @Test
        @DisplayName("Incorrect value2")
        void test4() {
            Hour test = new Hour("24:15", null, null);
            assertFalse(test.valid);
        }

        @Test
        @DisplayName("Incorrect value3")
        void test5() {
            Hour test = new Hour("2a:15", null, null);
            assertFalse(test.valid);
        }

        @Test
        @DisplayName("End after start")
        void test6() {
            Hour test = new Hour("8:15", open, null);
            assertTrue(test.valid);
        }

        @Test
        @DisplayName("Between")
        void test7() {
            Hour test = new Hour("8:15", open, close);
            assertTrue(test.valid);
        }
    }
    @Nested
    class calculateMinutes
    {
        private Hour test;
        @BeforeEach
        void init() {
            test = new Hour("8:00", null, null);
        }
        @Test
        @DisplayName("Equal 0")
        void test1() {
            assertEquals(test.calculateMinutes("00"), 0);
        }

        @Test
        @DisplayName("Equal 2")
        void test2() {
            assertEquals(test.calculateMinutes("30"), 2);
        }

        @Test
        @DisplayName("Invalid given")
        void test3() {
            assertEquals(test.calculateMinutes("31"), -1);
        }
    }
    @Nested
    class calculateNextString
    {
        private Hour test;
        @BeforeEach
        void init() {
            test = new Hour("8:00", null, null);
        }
        @Test
        @DisplayName("Increase by 0")
        void test1() {
            assertEquals(test.calculateNextString(0), "8:00");

        }

        @Test
        @DisplayName("Increase by 2")
        void test2() {
            assertEquals(test.calculateNextString(2), "8:30");

        }

        @Test
        @DisplayName("Increase by 6")
        void test3() {
            assertEquals(test.calculateNextString(6), "9:30");

        }
    }
}