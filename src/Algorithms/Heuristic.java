//Marcin Różański Kręgielnia
package Algorithms;

import Data.Reservation;

import java.util.ArrayList;
import java.util.Comparator;

public class Heuristic implements Algorithm
{
    private ArrayList<Reservation> reservations;
    private int[] burden;
    private int length;
    private int[][] schedule;
    private int numberOfTracks;
    public Heuristic(int length, int numberOfTracks, ArrayList<Reservation> reservations, int[][] schedule)
    {
        this.length = length;
        burden = new int[length];
        this.numberOfTracks = numberOfTracks;
        this.reservations = reservations;
        this.schedule = schedule;
    }
    public void calculate()
    {
        countBurden();
        reservations.sort(Comparator.comparingInt(d -> d.looseness));
        chooseTimeRanges();
        reservations.sort(Comparator.comparingInt(d -> -d.length));
        createSchedule(numberOfTracks, length);
        tryToFitIn();
    }

    private void countBurden()
    {
        for (Reservation reservation : reservations)
        {
            for(int i = reservation.start; i < reservation.end; i++)
            {
                burden[i]++;
            }
        }
    }

    private void chooseTimeRanges()
    {
        for (Reservation reservation : reservations)
        {
            reservation.chooseTime(burden);
            reservation.updateBurden(burden);
        }
    }

    private void createSchedule(int numberOfTracks, int length)
    {
        for(int i = 0; i < numberOfTracks; i++)
        {
            int maxLength = 0;
            for (Reservation reservation : reservations)
            {
                int[] tmp = new int[length];
                if(reservation.used) continue;
                addToTmpSchedule(tmp, reservation);
                for (Reservation reservation2 : reservations)
                {
                    if(reservation == reservation2 || reservation2.used) continue;
                    addToTmpSchedule(tmp, reservation2);
                }
                int totalLength = 0;
                for (int j = 0; j < length; j++)
                {
                    if(tmp[j] != 0) totalLength++;
                }

                if(totalLength > maxLength)
                {
                    schedule[i] = tmp.clone();
                    maxLength = totalLength;
                }
            }
            if(maxLength != 0) markAsUsed(i);
        }
    }

    private void addToTmpSchedule(int[] tmp, Reservation reservation)
    {
        for(int i = reservation.chosenStart; i < reservation.chosenEnd; i++)
        {
            if(tmp[i] != 0) return;
        }
        for(int i = reservation.chosenStart; i < reservation.chosenEnd; i++)
        {
            tmp[i] = reservation.id;
        }
    }

    private void tryToFitIn()
    {
        for (Reservation reservation : reservations)
        {
            if(reservation.used) continue;
            for(int i = 0; i < numberOfTracks; i++)
            {
                for(int j = reservation.start; j <= reservation.end - reservation.length; j++)
                {
                    boolean canFitIn = true;
                    for(int k = j; k < j + reservation.length; k++)
                    {
                        if(schedule[i][k] != 0) canFitIn = false;
                    }
                    if(!canFitIn) continue;
                    reservation.used = true;
                    for(int k = j; k < j + reservation.length; k++)
                    {
                        schedule[i][k] = reservation.id;
                    }
                    break;
                }
                if(reservation.used) break;
            }
        }
    }

    private void markAsUsed(int track)
    {
        reservations.sort(Comparator.comparingInt(d -> d.id));
        for(int j = 0; j < length; j++)
        {
            if(schedule[track][j] != 0)
            {
                reservations.get(schedule[track][j] - 1).used = true;
            }
        }
        reservations.sort(Comparator.comparingInt(d -> -d.length));
    }
}
