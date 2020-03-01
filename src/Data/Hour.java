//Marcin Różański Kręgielnia
package Data;

public class Hour
{
    public String numberFormat;
    public boolean valid;
    public int value;
    public Hour()
    {

    }
    public Hour(String numberFormat, Hour start, Hour end)
    {
        this.numberFormat = numberFormat;
        valid = validate();
        if(!valid) value = -1;
        else if(start == null) value = 0;
        else value = calculateValue(start);
        if(end != null && end.value < value) value = -1;
    }

    boolean validate()
    {
        if(!(numberFormat.length() == 4 && numberFormat.charAt(1) == ':') && !(numberFormat.length() == 5 && numberFormat.charAt(2) == ':')) return false;
        int numberOfColons = 0;
        for(int i = 0; i < numberFormat.length(); i++)
        {
            if(numberFormat.charAt(i) == ':') numberOfColons++;
            else if(numberFormat.charAt(i) < '0' || numberFormat.charAt(i) > '9') return false;
        }
        if(numberOfColons != 1) return false;
        char a = numberFormat.charAt(numberFormat.length()-2);
        char b = numberFormat.charAt(numberFormat.length()-1);
        String tmp = "";
        tmp += a;
        tmp += b;
        if(!tmp.equals("00") && !tmp.equals("15") && !tmp.equals("30") && !tmp.equals("45")) return  false;
        if(numberFormat.length() == 5 && (numberFormat.charAt(0) > '2' || numberFormat.charAt(0) == '0')) return false;
        if(numberFormat.length() == 5 && numberFormat.charAt(0) == '2' && numberFormat.charAt(1) > '4') return false;
        return numberFormat.length() != 5 || numberFormat.charAt(0) != '2' || numberFormat.charAt(1) != '4' || tmp.equals("00");
    }

    private int calculateValue(Hour start)
    {
        if(!valid) return -1;
        int minutes, hours, startMinutes, startHours;
        String tmp = getMinutes();
        minutes = calculateMinutes(tmp);
        tmp = start.getMinutes();
        startMinutes = calculateMinutes(tmp);
        tmp = getHours();
        hours = Integer.parseInt(tmp);
        tmp = start.getHours();
        startHours = Integer.parseInt(tmp);
        int value = hours - startHours;
        value *= 4;
        value += minutes;
        value -= startMinutes;
        return value;
    }

    int calculateMinutes(String minutes)
    {
        if(!valid) return -1;
        switch(minutes)
        {
            case "00":
                return 0;
            case "15":
                return 1;
            case "30":
                return 2;
            case "45":
                return 3;
            default:
                return -1;
        }
    }

    public String calculateNextString(int value)
    {
        if(value == 0) return numberFormat;
        int startHour, startMinutes;
        startHour = numberFormat.charAt(0) - 48;
        if(numberFormat.length() == 5)
        {
            startHour *= 10;
            startHour += numberFormat.charAt(1) - 48;
        }
        startMinutes = numberFormat.charAt(numberFormat.length()-1) - 48 + (numberFormat.charAt(numberFormat.length()-2) - 48) * 10;
        int startH = startHour, startM = startMinutes;
        for(int i = 0; i < value; i++)
        {
            if(startM != 45) startM  += 15;
            else
            {
                startH +=1;
                startM = 0;
            }
        }
        String tmp;
        if(startM == 0) tmp = "00";
        else tmp = Integer.toString(startM);
        return startH + ":" + tmp;
    }

    private String getMinutes()
    {
        if(!valid) return "";
        String tmp = "";
        char a = numberFormat.charAt(numberFormat.length()-2);
        char b = numberFormat.charAt(numberFormat.length()-1);
        tmp += a;
        tmp += b;
        return tmp;
    }

    private String getHours()
    {
        if(!valid) return "";
        String tmp = "";
        tmp += numberFormat.charAt(0);
        if(numberFormat.length() == 5)
        {
            tmp += numberFormat.charAt(1);
        }
        return tmp;
    }

    public void clone(Hour hour)
    {
        numberFormat = hour.numberFormat;
        value = hour.value;
        valid = hour.valid;
    }
}
