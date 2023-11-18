package org.example;

public class Predictor {
    int digitPlate;
    int day;
    String time;

    public void getCleanedData(String userPlate, String userDate, String userTime) {
        digitPlate = Integer.parseInt(userPlate);
        day = Integer.parseInt(userDate);
        time = userTime;
        restrictPlateDay(digitPlate, day);
    }


    private boolean restrictPlateDay(int digitPlate, int day) {


    }


}
