//Marcin Różański Kręgielnia
import Data.Hour;
import Data.Reservation;
import Exits.Exits;
import InputController.InputController;
import InputController.InputController1;
import InputController.InputController2;
import InputController.InputController3;
import Run.Run;

import java.io.IOException;
import java.util.ArrayList;

public class Main
{
    private static InputController inputController;
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Exits exits = new Exits();
        if (args.length == 0) exits.exit("no arguments - check readme.txt");
        switch (args[0]) {
            case "m1":
                inputController = new InputController1();
                break;
            case "m2":
                inputController = new InputController2();
                break;
            case "m3":
                inputController = new InputController3();
                break;
            default:
                exits.exit("incorrect mode");
        }
        Hour open = new Hour();
        Hour close = new Hour();
        inputController.checkInput(reservations, args, open, close);
        int numberOfTracks = inputController.getNumberOfTracks(args);
        int[][] schedule = new int[numberOfTracks][close.value];
        new Run(inputController, numberOfTracks, args, open, close, schedule, reservations);
        if(inputController instanceof InputController1) Run.startm1();
        else if (inputController instanceof InputController3) Run.startm3();
        else Run.startm2();
    }


}
