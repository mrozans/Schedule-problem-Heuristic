//Marcin Różański Kręgielnia
package GenerateData;

import Data.Hour;
import Data.Reservation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Generator
{
    private int numberOfTracks;
    private int numberOfReservations;
    private Hour open;
    private Hour close;
    ArrayList<Integer>[] trackReservations;

    public Generator(int numberOfTracks, int numberOfReservations, Hour open, Hour close)
    {
        this.numberOfTracks = numberOfTracks;
        this.numberOfReservations = numberOfReservations;
        this.open = open;
        this.close = close;
        trackReservations = new ArrayList[numberOfTracks];
    }
    public void generate(ArrayList<Reservation> reservations) throws IOException
    {
        for(int i = 0; i < numberOfTracks; i++)
        {
            trackReservations[i] = new ArrayList<>();
            trackReservations[i].add(close.value);
        }
        determineReservations();
        extendReservations(reservations);
        Hour zero = new Hour("0:00", null, null);

        ArrayList<String> lines = new ArrayList<>();
        for (Reservation reservation : reservations)
        {
            String tmp = reservation.id + " " + open.calculateNextString(reservation.start) + " " +
                    open.calculateNextString(reservation.end) + " " + zero.calculateNextString(reservation.length);
            lines.add(tmp);
        }
        Path file = Paths.get("generatedData.txt");
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    private void determineReservations()
    {
        Random random = new Random();
        for(int i = 0; i < numberOfReservations - numberOfTracks; i++)
        {
            boolean change = false;
            while(!change)
            {
                int track = random.nextInt(numberOfTracks);
                int reservation = random.nextInt(trackReservations[track].size());
                if(trackReservations[track].get(reservation) != 1)
                {
                    change = true;
                    int length = random.nextInt(trackReservations[track].get(reservation) -1) + 1;
                    trackReservations[track].set(reservation, trackReservations[track].get(reservation) - length);
                    trackReservations[track].add(length);
                }
            }
        }
    }

    private void extendReservations(ArrayList<Reservation> reservations)
    {
        Random random = new Random();
        int id = 1;
        for(int i = 0; i < numberOfTracks; i++)
        {
            int time = 0;
            for(Integer integer : trackReservations[i])
            {
                int preExtend = random.nextInt(time + 1);
                int postExtend = random.nextInt(close.value - time - integer + 1);
                Reservation tmp = new Reservation(id, time - preExtend, time + integer + postExtend, integer);
                id++;
                reservations.add(tmp);
                time += integer;
            }
        }
    }
}
