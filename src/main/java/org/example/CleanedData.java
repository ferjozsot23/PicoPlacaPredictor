package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanedData {
    int cleanedPlate;
    int cleanedDate;
    Date cleanedHour;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    public boolean clean(String userPlate, String userDate, String userHour) {
        // Validación de datos
        if (!isValidData(userPlate, userDate, userHour)) return false;

        try {
            // Formateo de datos válidos, como digito de placa y día de semana
            formatData(userPlate,userDate,userHour);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void formatData(String userPlate, String userDate, String userHour) throws ParseException {
        formatValidPlate(userPlate);
        formatValidDate(userDate);
    }

    private void formatValidDate(String userDate) throws ParseException {
        Date formatedDate = dateFormat.parse(userDate);
        cleanedDate = getDay(formatedDate);
    }
    private void formatValidPlate(String userPlate) {
        char cleanedPlateC = userPlate.charAt(userPlate.length() - 1);
        cleanedPlate = Character.getNumericValue(cleanedPlateC);
    }

    private boolean isValidData(String userPlate, String userDate, String userHour) {
        if (userPlate == null || userDate == null || userHour == null) return false;
        if (!(isValidPlate(userPlate) && isValidDate(userDate) && isValidHour(userHour))) return false;

        return true;
    }

    private boolean isValidPlate(String userPlate) {
        String pattern = "^[A-Z]{3}-[0-9]{3,4}$";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(userPlate);

        return matcher.matches();
    }
    private boolean isValidDate(String userDate) {

        try {
            dateFormat.parse(userDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    private boolean isValidHour(String userHour) {
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        try {
            cleanedHour = hourFormat.parse(userHour);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private int getDay(Date formatedDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatedDate);


        int numberDay = calendar.get(Calendar.DAY_OF_WEEK);
        numberDay = (numberDay + 5) % 7 + 1;

        return numberDay;
    }
}
