//Marcin Różański Kręgielnia
package DisplayData;

import Data.Hour;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Results
{
    public void createFile(int numberOfTracks, Hour close, Hour open, int[][] schedule) throws IOException {
        Hour zero = new Hour("0:00", null, null);
        ArrayList<String> lines = new ArrayList<>();
        for(int i = 0; i < numberOfTracks; i++)
        {
            String tmp = "Track: " + (i + 1);
            lines.add(tmp);
            int start = 0;
            for(int j = 1; j <= close.value; j++)
            {
                if(j == close.value)
                {
                    if(schedule[i][start] != 0)
                    {
                        String tmp2 = schedule[i][start]+ " " + open.calculateNextString(start) + " " +
                                open.calculateNextString(j) + " " + zero.calculateNextString(j - start);
                        lines.add(tmp2);
                        start = 0;
                    }
                    continue;
                }
                if(schedule[i][j] != schedule[i][start])
                {
                    if(schedule[i][start] != 0)
                    {
                        String tmp2 = schedule[i][start] + " " + open.calculateNextString(start) + " " +
                                open.calculateNextString(j) + " " + zero.calculateNextString(j - start);
                        lines.add(tmp2);
                    }
                    start = j;
                }
            }
        }

        Path file = Paths.get("result.txt");
        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}
