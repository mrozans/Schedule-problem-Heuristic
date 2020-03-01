//Marcin Różański Kręgielnia
package DisplayData;

import Data.Hour;

import javax.swing.*;
import java.awt.*;

public class Table extends JFrame
{
    private Object[][] data;
    private int length;

    public Table(int numberOfTracks, int length, int[][] schedule, Hour open)
    {
        this.length = length;
        setLayout(new FlowLayout());
        String[] columnNames = new String[numberOfTracks + 1];
        columnNames[0] = "Time";
        for(int i = 1; i <= numberOfTracks; i++)
        {
            columnNames[i] = "Track " + i;
        }
        data = new Object[length][numberOfTracks + 1];
        setData(schedule, open, numberOfTracks);
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 700));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void setData(int[][] schedule, Hour open, int numberOfTracks)
    {
        int startHour, startMinutes;
        startHour = open.numberFormat.charAt(0) - 48;
        if(open.numberFormat.length() == 5)
        {
            startHour *= 10;
            startHour += open.numberFormat.charAt(1) - 48;
        }
        startMinutes = open.numberFormat.charAt(open.numberFormat.length()-1) - 48 + (open.numberFormat.charAt(open.numberFormat.length()-2)  - 48) * 10;
        timeColumn(startHour, startMinutes);
        for(int i = 0; i < numberOfTracks; i++)
        {
            for(int j = 0; j < length; j++)
            {
                if(schedule[i][j] != 0) data[j][i + 1] = schedule[i][j];
                else data[j][i + 1] = " ";
            }
        }
    }

    private void timeColumn(int startHour, int startMinutes)
    {
        Integer startH = startHour, startM = startMinutes, nextH, nextM;
        for(int i = 0; i < length; i++)
        {
            if(startM != 45)
            {
                nextH = startH;
                nextM = startM + 15;
            }
            else
            {
                nextH = startH + 1;
                nextM = 0;
            }
            String tmp, tmp2;
            if(startM == 0) tmp = "00";
            else tmp = startM.toString();
            if(nextM == 0) tmp2 = "00";
            else tmp2 = nextM.toString();
            data[i][0] = startH.toString() + ":" + tmp + "-" + nextH.toString() + ":" + tmp2;
            startH = nextH;
            startM = nextM;
        }
    }
}
