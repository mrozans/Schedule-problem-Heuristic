package Run;

import Algorithms.Algorithm;
import Algorithms.Brutal;
import Algorithms.Heuristic;
import Data.Hour;
import Data.Reservation;
import DisplayData.Results;
import DisplayData.Table;
import DisplayData.TimeTable;
import GenerateData.Generator;
import InputController.InputController;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Run
{
    private static InputController inputController;
    private static Algorithm algorithm;
    private static Hour open;
    private static Hour close;
    private static int[][] schedule;
    private static int numberOfTracks;
    private static ArrayList<Reservation> reservations;
    private static String[] args;

    public Run(InputController inputController, int numberOfTracks, String[] args, Hour open, Hour close, int[][] schedule, ArrayList<Reservation> reservations)
    {
        Run.inputController = inputController;
        Run.open = open;
        Run.close = close;
        Run.schedule = schedule;
        Run.numberOfTracks = numberOfTracks;
        Run.reservations = reservations;
        Run.args = args;
    }
    private static void createTable()
    {
        Table table = new Table(numberOfTracks, close.value, schedule, open);
        table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table.setSize(1920, 1080);
        table.setVisible(true);
        table.setTitle("Schedule");
    }

    private static void createTimeTable(long[][] time)
    {
        TimeTable table = new TimeTable(time, inputController.getNumberOfReservations(args), numberOfTracks, inputController.getNumberOfStep(args));
        table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table.setSize(1920, 1080);
        table.setVisible(true);
        table.setTitle("Algorytm z asymptotą n^2 * t");
    }

    public static void startm1() throws IOException {
        boolean heuristic = inputController.isHeuristic(args);
        if(heuristic) algorithm = new Heuristic(close.value, numberOfTracks, reservations, schedule);
        else algorithm = new Brutal(close.value, numberOfTracks, reservations, schedule);
        algorithm.calculate();
        createTable();
        Results results = new Results();
        results.createFile(numberOfTracks, close, open, schedule);
    }

    public static void startm2() throws IOException {
        int numberOfReservations = inputController.getNumberOfReservations(args);
        Generator generator = new Generator(numberOfTracks, numberOfReservations, open, close);
        generator.generate(reservations);
        startm1();
    }

    public static void startm3() throws IOException {
        algorithm = new Heuristic(close.value, numberOfTracks, reservations, schedule);
        long[][] time = new long[inputController.getNumberOfIterations(args)][2];
        int numberOfReservations = inputController.getNumberOfReservations(args);
        warmUp(numberOfReservations);
        for(int i = 0; i < inputController.getNumberOfIterations(args); i++)
        {
            if(i != 0) numberOfReservations += inputController.getNumberOfStep(args);
            Generator generator = new Generator(numberOfTracks, numberOfReservations, open, close);
            for(int j = 0; j < inputController.getNumberOfInstances(args); j++)
            {
                generator.generate(reservations);
                time[i][0] += System.currentTimeMillis();
                algorithm.calculate();
                time[i][0] -= System.currentTimeMillis();
                reservations.clear();
                clear();
            }
            time[i][0] *= -1;
            time[i][0] /= inputController.getNumberOfInstances(args);
            time[i][1] = numberOfTracks + numberOfReservations * numberOfReservations;
        }
        createTimeTable(time);
    }

    private static void warmUp(int numberOfReservations) throws IOException {
        Generator generatorWarmup = new Generator(numberOfTracks, numberOfReservations, open, close);
        generatorWarmup.generate(reservations);
        algorithm.calculate();
        reservations.clear();
        clear();
    }

    private static void clear()
    {
        for(int i = 0; i < numberOfTracks; i++)
        {
            for(int j = 0; j < close.value; j++) schedule[i][j] = 0;
        }
    }
}
