package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanedData {
    int digitPlate;
    int numberDay;
    Date formatedHour;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

    private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");

    public boolean clean(String userPlate, String userDate, String userHour) {
        if (!isValidData(userPlate, userDate, userHour)) {
            return false;
        }

        try {
            digitPlate = formatPlate(userPlate);
            numberDay = formatDate(userDate);
            formatedHour = formatHour(userHour);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public int formatPlate(String userPlate) {
        char charDigitPlate = userPlate.charAt(userPlate.length() - 1);
        return Character.getNumericValue(charDigitPlate);
    }
    public int formatDate(String userDate) throws ParseException {
        Date formatedDate = DATE_FORMAT.parse(userDate);
        return getDay(formatedDate);
    }
    public Date formatHour(String userHour) throws ParseException {
        HOUR_FORMAT.setLenient(false);
        return HOUR_FORMAT.parse(userHour);
    }


    private boolean isValidData(String userPlate, String userDate, String userHour) {
        return isValidPlate(userPlate) && isValidDate(userDate) && isValidHour(userHour);
    }

    public boolean isValidPlate(String userPlate) {
        if (userPlate == null) return false;

        String pattern = "^[A-Z]{3}-[0-9]{3,4}$";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(userPlate);

        return matcher.matches();
    }
    public boolean isValidDate(String userDate) {
        DATE_FORMAT.setLenient(false);
        if (userDate == null) return false;
        try {
            DATE_FORMAT.parse(userDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public boolean isValidHour(String userHour) {
        if (userHour == null) return false;
        HOUR_FORMAT.setLenient(false);
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        try {
            formatedHour = hourFormat.parse(userHour);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public int getDay(Date formatedDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatedDate);
        int numberDay = calendar.get(Calendar.DAY_OF_WEEK);
        return (numberDay + 5) % 7 + 1;
    }
}
