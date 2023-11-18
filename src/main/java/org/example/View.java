package org.example;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);
    Predictor predictor = new Predictor();
    CleanerData cleaner = new CleanerData();

    public void getUserData() {
        String userPlate;
        String userDate;
        String userTime;
        do {
            userPlate = readString("Enter the licence plate: ");
            userDate = readString("Enter the date: ");
            userTime = readString("Enter the time: ");

        } while (!cleaner.isValid(userPlate, userDate, userTime));

        predictor.getCleanedData(userPlate, userDate, userTime);
    }

    private String readString(String message){
        System.out.print(message);
        return scanner.next();
    }
}
