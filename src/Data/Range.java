//Marcin Różański Kręgielnia
package Data;

class Range extends Hour
{
    Range(String numberFormat)
    {
        this.numberFormat = numberFormat;
        valid = validate();
        if(!valid) value = -1;
        else value = calculateValue();
    }

    private int calculateValue()
    {
        if(!valid) return -1;
        int minutes, hours;
        char a = numberFormat.charAt(numberFormat.length()-2);
        char b = numberFormat.charAt(numberFormat.length()-1);
        String tmp = "";
        tmp += a;
        tmp += b;
        minutes = super.calculateMinutes(tmp);
        tmp = "";
        tmp += numberFormat.charAt(0);
        if(numberFormat.length() == 5)
        {
            tmp += numberFormat.charAt(1);
        }
        hours = Integer.parseInt(tmp);
        int value = hours;
        value *= 4;
        value += minutes;
        return value;
    }
}
