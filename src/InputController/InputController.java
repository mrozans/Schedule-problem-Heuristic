//Marcin Różański Kręgielnia
package InputController;

import Data.Hour;
import Data.Reservation;

import java.io.IOException;
import java.util.ArrayList;

public interface InputController
{
    void checkInput(ArrayList<Reservation> reservations, String[] args, Hour open, Hour close) throws IOException;
    int getNumberOfTracks(String[] args);
    int getNumberOfReservations(String[] args);
    boolean isHeuristic(String[] args);
    int getNumberOfIterations(String[] args);
    int getNumberOfStep(String[] args);
    int getNumberOfInstances(String[] args);
}
