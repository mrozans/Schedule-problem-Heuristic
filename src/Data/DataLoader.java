//Marcin Różański Kręgielnia
package Data;

import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader
{
    private Scanner scanner;
    public boolean valid;
    public int errorId;
    public DataLoader(Scanner scanner, Hour open, Hour close, ArrayList<Reservation> reservations)
    {
        this.scanner = scanner;
        valid = true;
        read(open, close, reservations);
    }
    private void read(Hour open, Hour close, ArrayList<Reservation> reservations)
    {
        errorId = 1;
        while(scanner.hasNext())
        {
            String s = scanner.nextLine();
            String[] tmp = s.split(" ");
            if(tmp.length != 4)
            {
                valid = false;
                break;
            }
            int id;
            try
            {
                id = Integer.parseInt(tmp[0]);
            }
            catch (NumberFormatException nfe)
            {
                valid = false;
                break;
            }
            Hour start = new Hour(tmp[1], open, close);
            Hour end = new Hour(tmp[2], open, close);
            Range range = new Range(tmp[3]);
            valid = checkConstrains(id, start, end, close, range);
            if(!valid) break;
            Reservation tmp2 = new Reservation(id, start.value, end.value, range.value);
            reservations.add(tmp2);
            errorId++;
        }
    }

    private boolean checkConstrains(int id, Hour start, Hour end, Hour close, Range range)
    {
        if(id != errorId || !start.valid || !end.valid || !range.valid)
        {
            return false;
        }

        return start.value < end.value && start.value >= 0 && range.value > 0 && end.value <= close.value && range.value <= end.value - start.value;
    }
}
