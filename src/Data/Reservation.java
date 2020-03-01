//Marcin Różański Kręgielnia
package Data;

public class Reservation
{
    public int id;
    public int start;
    public int end;
    public int length;
    public int chosenStart;
    public int chosenEnd;
    public int looseness;
    public boolean used;

    public Reservation(int id, int start, int end, int length)
    {
        this.id = id;
        this.start = start;
        this.end = end;
        this.length = length;
        looseness = end - start - length;
        used = false;
    }

    public void chooseTime(int[] burden)
    {
        int minBurden = 0;
        for(int i = start; i + length <= end; i++)
        {
            int rangeBurden = 0;
            for(int j = i; j < i + length; j++)
            {
                rangeBurden += burden[j];
            }
            if(i == start)
            {
                minBurden = rangeBurden;
                chosenStart = start;
                chosenEnd = start + length;
            }
            else
            {
                if(minBurden > rangeBurden)
                {
                    minBurden = rangeBurden;
                    chosenStart = i;
                    chosenEnd = chosenStart + length;
                }
            }
        }
    }

    public void updateBurden(int[] burden)
    {
        for(int i = start; i < chosenStart; i++)
        {
            burden[i]--;
        }

        for(int i = chosenStart + length; i < end; i++)
        {
            burden[i]--;
        }
    }
}
