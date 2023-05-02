package org.yup.accountingledger;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Screens {

    public static String homeScreen(){

        Scanner userScanner = new Scanner(System.in);

        System.out.println("Welcome to PLUR bank!");

        System.out.println("Please choose from these actions: ");
        System.out.println("D * to make a DEPOSIT");
        System.out.println("P * to make a PAYMENT");
        System.out.println("L * to display the LEDGER screen");
        System.out.println("X * to EXIT");

        System.out.print("Please make your selection: ");

        return userScanner.nextLine();


    }

    public static String ledgerScreen(){


    }

}
