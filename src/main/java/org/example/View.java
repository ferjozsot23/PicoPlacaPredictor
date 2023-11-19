package org.example;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);
    Predictor predictor = new Predictor(this);
    CleanedData cleaner = new CleanedData();

    public void getUserData() {
        String userPlate;
        String userDate;
        String userHour;
        do {
            userPlate = readString("Enter the licence plate (ex: PBU-1234): ");
            userDate = readString("Enter the date (ex: 11-18-2023): ");
            userHour = readString("Enter the time: (19:00): ");

        } while (!cleaner.clean(userPlate, userDate, userHour));

        sendCleanedData(cleaner);
    }

    private void sendCleanedData(CleanedData cleaner) {
        predictor.getCleanedData(cleaner);
    }

    public void printResult(boolean isRestrictRoad) {
        if (isRestrictRoad) {
            System.out.println("Restrict");
        } else {
            System.out.println("Allowed");
        }
    }

    private String readString(String message){
        System.out.print(message);
        return scanner.next();
    }

}
