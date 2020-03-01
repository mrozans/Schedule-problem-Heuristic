//Marcin Różański Kręgielnia
package Algorithms;

import Data.Reservation;

import java.util.ArrayList;

public class Brutal implements Algorithm
{
    private ArrayList<Reservation> reservations;
    private int length;
    private int[][] schedule;
    private int[][] tmpSchedule;
    private int numberOfTracks;
    private int maxFill;

    public Brutal(int length, int numberOfTracks, ArrayList<Reservation> reservations, int[][] schedule)
    {
        this.length = length;
        this.numberOfTracks = numberOfTracks;
        this.reservations = reservations;
        this.schedule = schedule;
        tmpSchedule = new int[numberOfTracks][length];
        maxFill = 0;
    }

    public void calculate()
    {
        for (Reservation reservation : reservations)
        {
            if(!reservation.used)
            {
                reservation.used = true;
                boolean wasPlacedInEmptyTrack = false;
                for(int i = 0; i < numberOfTracks; i++)
                {
                    if(wasPlacedInEmptyTrack) break;
                    if(isEmpty(i)) wasPlacedInEmptyTrack = true;

                    for(int j = reservation.start; j <= reservation.end - reservation.length; j++)
                    {
                        tryToFitIn(reservation, j, i);
                        boolean all = setAll();
                        if(all) putToSchedule();
                        else calculate();
                        if(getTmpFill() == numberOfTracks * length) return;
                        remove(reservation.id);
                    }
                }
                reservation.used = false;
            }
        }
    }

    private int getTmpFill()
    {
        int tmp = 0;
        for(int i = 0; i < numberOfTracks ; i++)
        {
            for(int j = 0; j < length; j++)
            {
                if(tmpSchedule[i][j] != 0) tmp++;
            }
        }
        return tmp;
    }

    private void remove(int id)
    {
        for(int i = 0; i < numberOfTracks ; i++)
        {
            for(int j = 0; j < length; j++)
            {
                if(tmpSchedule[i][j] == id) tmpSchedule[i][j] = 0;
            }
        }
    }

    private boolean isEmpty(int track)
    {
        for(int i = 0; i < length; i++)
        {
            if(tmpSchedule[track][i] != 0)  return false;
        }
        return true;
    }

    private void tryToFitIn(Reservation reservation, int start, int track)
    {
        boolean canFitIn = true;
        for(int i = start; i < start + reservation.length; i++)
        {
            if(tmpSchedule[track][i] != 0)
            {
                canFitIn = false;
                break;
            }
        }
        if(canFitIn)
        {
            for(int i = start; i < start + reservation.length; i++)
            {
                tmpSchedule[track][i] = reservation.id;
            }
        }
    }

    private boolean setAll()
    {
        for (Reservation reservation : reservations)
        {
            if(!reservation.used)
            {
                return false;
            }
        }
        return true;
    }

    private void putToSchedule()
    {
        int fill = getTmpFill();
        if(maxFill < fill)
        {
            for(int it = 0; it < numberOfTracks; it++) schedule[it] = tmpSchedule[it].clone();
            maxFill = fill;
        }
    }
}