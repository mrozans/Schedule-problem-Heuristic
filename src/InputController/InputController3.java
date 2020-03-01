//Marcin Różański Kręgielnia
package InputController;

import Data.Hour;
import Data.Reservation;
import Exits.Exits;

import java.util.ArrayList;

public class InputController3 extends InputController2
{
    public void checkInput(ArrayList<Reservation> reservations, String[] args, Hour open, Hour close)
    {
        Exits exits = new Exits();
        super.checkInput(reservations, args, open, close);
        super.checkIfPositiveInt(5, "Incorrect number of iterations", args);
        super.checkIfPositiveInt(6, "Incorrect number of steps", args);
        super.checkIfPositiveInt(7, "Incorrect number of instances", args);
        if(getNumberOfReservations(args) + getNumberOfStep(args) * (getNumberOfIterations(args) -1)  > getNumberOfTracks(args) * close.value)
            exits.exit("Number of reservations is too small for further iterations");
    }
    public int getNumberOfTracks(String[] args)
    {
        return Integer.parseInt(args[3]);
    }

    public int getNumberOfReservations(String[] args)
    {
        return Integer.parseInt(args[4]);
    }

    public boolean isHeuristic(String[] args)
    {
        return true;
    }

    public int getNumberOfIterations(String[] args)
    {
        return Integer.parseInt(args[5]);
    }

    public int getNumberOfStep(String[] args)
    {
        return Integer.parseInt(args[6]);
    }

    public int getNumberOfInstances(String[] args)
    {
        return Integer.parseInt(args[7]);
    }
}
