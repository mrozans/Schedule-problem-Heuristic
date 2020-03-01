//Marcin Różański Kręgielnia
package InputController;

import Data.DataLoader;
import Data.Hour;
import Data.Reservation;
import Exits.Exits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputController1 implements InputController
{
    private String[] tmp;
    private Exits exits = new Exits();
    public void checkInput(ArrayList<Reservation> reservations, String[] args, Hour open, Hour close)
            throws IOException {
        if(System.in.available() == 0) exits.exit("No input stream or file is empty");
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        tmp = firstLine.split(" ");
        checkFirstLine(tmp, open, close);
        DataLoader dl = new DataLoader(scanner, open, close, reservations);
        if(!dl.valid) exits.exit(String.format("Incorrect line: %d in file", dl.errorId));
    }

    private void checkFirstLine(String[] tmp, Hour open, Hour close)
    {
        if(tmp.length != 4) exits.exit("Incorrect number of parameters in first line");
        if(!tmp[0].equals("heuristic") && !tmp[0].equals("brutal")) exits.exit("Incorrect type of algorithm");
        Hour open1= new Hour(tmp[1], null, null);
        if(!open1.valid) exits.exit("Incorrect open value");
        Hour close1 = new Hour(tmp[2], open1, null);
        if(!close1.valid || close1.value <= 0) exits.exit("Incorrect close value");
        int numberOfTracks = 0;
        try
        {
            numberOfTracks = Integer.parseInt(tmp[3]);
        }
        catch (NumberFormatException nfe)
        {
            exits.exit("Incorrect number of tracks");
        }
        if(numberOfTracks <= 0) exits.exit("Incorrect number of tracks");
        open.clone(open1);
        close.clone(close1);
    }

    public int getNumberOfTracks(String[] args)
    {
        if(tmp[3] == null) return -1;
        return Integer.parseInt(tmp[3]);
    }

    public int getNumberOfReservations(String[] args)
    {
        return 0;
    }

    public boolean isHeuristic(String[] args)
    {
        return tmp[0].equals("heuristic");
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
