package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Predictor {
    int digitPlate;
    int numberDay;
    Date hour;
    View view;
    boolean isRestrictRoad;

    public Predictor(View view) {
        this.view = view;
    }

    public void getCleanedData(CleanedData cleaner) {
        digitPlate = cleaner.digitPlate;
        numberDay = cleaner.numberDay;
        hour = cleaner.formatedHour;

        calculateRestrictedRoad();
    }

    private void calculateRestrictedRoad() {
        isRestrictRoad = isRestrictedDay(digitPlate, numberDay) && isRestrictedHour(hour);
        sendResult();
    }

    private void sendResult() {
        view.printResult(isRestrictRoad);
    }

    public boolean isRestrictedDay(int digitPlate, int day) {
        if (day == 5 && digitPlate == 0) return true;
        if (day * 2 == digitPlate || day * 2 - 1 == digitPlate) return true;
        return false;
    }

    public boolean isRestrictedHour(Date hour) {
        Date startInterval1, endInterval1, startInterval2, endInterval2;

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            startInterval1 = timeFormat.parse("06:00");
            endInterval1 = timeFormat.parse("09:30");
            startInterval2 = timeFormat.parse("16:00");
            endInterval2 = timeFormat.parse("20:00");
        } catch (ParseException e) {
           return  false;
        }

        return isInInterval(hour, startInterval1, endInterval1) || isInInterval(hour, startInterval2, endInterval2);
    }

    private boolean isInInterval(Date hour, Date start, Date end) {
        return ((hour.equals(start) || (hour.after(start)) && (hour.equals(end) || hour.before(end))));
    }


}
