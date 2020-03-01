//Marcin Różański Kręgielnia
package DisplayData;

import javax.swing.*;
import java.awt.*;

public class TimeTable extends JFrame
{
    private Object[][] data;
    private int length;
    private long median, median2;

    public TimeTable(long[][] time, int n, int numberOfTracks, int step)
    {
        this.length = time.length;
        if(length % 2 == 1)
        {
            median = time[length / 2][0];
            median2 = time[length / 2][1];
        }
        else
        {
            median = (time[length / 2 - 1][0] + time[length / 2][0])/2;
            median2 = (time[length / 2 - 1][1] + time[length / 2][1])/2;
        }
        setLayout(new FlowLayout());
        String[] columnNames = new String[4];
        columnNames[0] = "n";
        columnNames[1] = "t";
        columnNames[2] = "t(n)[ms]";
        columnNames[3] = "q(n)";
        data = new Object[length][4];
        setData(time, n, numberOfTracks, step);
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 700));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void setData(long[][] time, int n, int numberOfTracks, int step)
    {
        for(int i = 0; i < length; i++)
        {
            data[i][0] = n + i * step;
            data[i][1] = numberOfTracks;
            data[i][2] = time[i][0];
            double tmp = (double)time[i][0] * (double)median2/((double)time[i][1] * (double)median);
            data[i][3] = tmp;
        }
    }
}
