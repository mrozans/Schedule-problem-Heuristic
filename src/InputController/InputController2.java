//Marcin Różański Kręgielnia
package InputController;

import Data.Hour;
import Data.Reservation;
import Exits.Exits;

import java.util.ArrayList;

public class InputController2 implements InputController
{
    private Exits exits;
    public void checkInput(ArrayList<Reservation> reservations, String[] args, Hour open, Hour close)
    {
        exits = new Exits();
        int i = 1;
        if(this instanceof InputController3) {
            if(args.length != 8) exits.exit("Incorrect number of arguments");
        }
        else {
            if(args.length != 6) exits.exit("Incorrect number of arguments");
            if(!args[1].equals("heuristic") && !args[1].equals("brutal")) exits.exit("Incorrect type of algorithm");
            i++;
        }
        Hour open1 = new Hour(args[i], null, null);
        if(!open1.valid) exits.exit("Incorrect open value");
        i++;
        Hour close1 = new Hour(args[i], open1, null);
        i++;
        if(!close1.valid || close1.value <= 0) exits.exit("Incorrect close value");
        checkIfPositiveInt(i, "Incorrect number of tracks", args);
        i++;
        checkIfPositiveInt(i, "Incorrect number of reservations", args);
        if(Integer.parseInt(args[i]) > Integer.parseInt(args[i - 1]) * close1.value) exits.exit("Number of reservations is too big");
        if(Integer.parseInt(args[i]) < Integer.parseInt(args[i - 1])) exits.exit("Number of reservations is too small");
        open.clone(open1);
        close.clone(close1);
    }

    public int getNumberOfTracks(String[] args)
    {
        return Integer.parseInt(args[4]);
    }

    public int getNumberOfReservations(String[] args)
    {
        return Integer.parseInt(args[5]);
    }

    void checkIfPositiveInt(int i, String message, String[] args)
    {
        int j = 0;
        try {
            j = Integer.parseInt(args[i]);
        }
        catch (NumberFormatException nfe) {
            exits.exit(message);
        }
        if(j <= 0) exits.exit(message);
    }

    public boolean isHeuristic(String[] args)
    {
        return args[1].equals("heuristic");
    }

    public int getNumberOfIterations(String[] args)
    {
        return 0;
    }

    public int getNumberOfStep(String[] args)
    {
        return 0;
    }

    public int getNumberOfInstances(String[] args)
    {
        return 0;
    }
}
