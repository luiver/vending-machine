package com.codecool.UI;

import java.util.Scanner;

public class IO {
    public final Scanner sc;

    public IO() {
        sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        System.out.println(message);
        String userInput = "";
        boolean validInput = false;
        while (!validInput) {
            userInput = sc.nextLine();
            validInput = isNumberInRange(userInput, rangeMin, rangeMax);
        }
        return Integer.parseInt(userInput);
    }

    private boolean isNumberInRange(String userInput, int rangeMin, int rangeMax) {
        int userInt;
        if (!userInput.equals("")) {
            if (userInput.matches("^[0-9]*$")) {
                userInt = Integer.parseInt(userInput);
                return userInt >= rangeMin && userInt <= rangeMax;
            }
        }
        System.out.println("Invalid input, please try again: ");
        return false;
    }
}
