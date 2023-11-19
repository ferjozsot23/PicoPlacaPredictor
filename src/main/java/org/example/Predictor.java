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
        System.out.println(digitPlate);
        numberDay = cleaner.numberDay;
        System.out.println(numberDay);
        hour = cleaner.formatedHour;
        System.out.println(hour);

        calculateRestrictedRoad();
    }

    private void calculateRestrictedRoad() {
        System.out.println(isRestrictedDay(digitPlate, numberDay));
        System.out.println(isRestrictedHour(hour));
        isRestrictRoad = isRestrictedDay(digitPlate, numberDay) && isRestrictedHour(hour);
        sendResult();

    }

    private void sendResult() {
        view.printResult(isRestrictRoad);
    }

    private boolean isRestrictedDay(int digitPlate, int day) {
        if (day == 5 && digitPlate == 0) return true;
        if (day * 2 == digitPlate || day * 2 - 1 == digitPlate) return true;
        return false;


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

        return (hour.after(startInterval1) && hour.before(endInterval1)) || (hour.after(startInterval2) && hour.before(endInterval2));

    }


}
