package Data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Nested
    class chooseTime
    {
        private Reservation reservation;
        @BeforeEach
        void init() {
            reservation = new Reservation(1, 0, 4, 2);
        }
        @Test
        @DisplayName("Into empty")
        void test1() {
            int[] burden = new int[4];
            reservation.chooseTime(burden);
            assertEquals(reservation.chosenStart, 0);

        }

        @Test
        @DisplayName("Into not empty")
        void test2() {
            int[] burden = new int[4];
            burden[0] = 5;
            reservation.chooseTime(burden);
            assertEquals(reservation.chosenStart, 1);

        }

        @Test
        @DisplayName("Into not empty2")
        void test3() {
            int[] burden = new int[4];
            burden[0] = 5;
            burden[1] = 5;
            reservation.chooseTime(burden);
            assertEquals(reservation.chosenStart, 2);

        }
    }

    @Nested
    class updateBurden
    {
        private Reservation reservation;
        @BeforeEach
        void init() {
            reservation = new Reservation(1, 0, 4, 2);
        }
        @Test
        @DisplayName("Into empty")
        void test1() {
            int[] burden = new int[4];
            burden[0] = 1;
            burden[1] = 1;
            burden[2] = 1;
            burden[3] = 1;
            reservation.chosenStart = 0;
            reservation.chosenEnd = 2;
            reservation.updateBurden(burden);
            assertEquals(burden[3], 0);

        }
    }

}