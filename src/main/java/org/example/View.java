package org.example;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);
    Predictor predictor = new Predictor();
    CleanedData cleaner = new CleanedData();

    public void getUserData() {
        String userPlate;
        String userDate;
        String userHour;
        do {
            userPlate = readString("Enter the licence plate: ");
            userDate = readString("Enter the date: ");
            userHour = readString("Enter the time: ");

        } while (!cleaner.isValid(userPlate, userDate, userHour));

        predictor.getCleanedData(cleaner);
    }

    private String readString(String message){
        System.out.print(message);
        return scanner.next();
    }

    public void imprimirResultado(boolean isRestrictRoad) {
        if (isRestrictRoad) {
            System.out.println("Restrict");
        } else {
            System.out.println("Allowed");
        }
    }
}
