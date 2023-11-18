package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Predictor {
    int digitPlate;
    int day;
    Date hour;
    View view = new View();
    public void getCleanedData(CleanedData cleaner) {
        digitPlate = cleaner.cleanedPlate;
        day = cleaner.cleanedDate;
        hour = cleaner.cleanedHour;

        calculateRestrictRoad();
    }
    private void calculateRestrictRoad() {
        boolean isRestrictRoad = isRestrictedDay(digitPlate, day) && isRestrictedHour(hour);
        view.imprimirResultado(isRestrictRoad);

    }
    private boolean isRestrictedDay(int digitPlate, int day) {
        if (day == 5 && digitPlate == 10) return false;
        if (day * 2 == digitPlate || day * 2 - 1 == digitPlate) return false;
        return true;

    }

    private boolean isRestrictedHour(Date hour) {
        Date startInterval1, endInterval1, startInterval2, endInterval2;

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            startInterval1 = timeFormat.parse("07:30");
            endInterval1 = timeFormat.parse("09:30");
            startInterval2 = timeFormat.parse("17:00");
            endInterval2 = timeFormat.parse("20:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return (hour.after(startInterval1) && hour.before(endInterval1)) ||
                (hour.after(startInterval2) && hour.before(endInterval2));

    }




}
