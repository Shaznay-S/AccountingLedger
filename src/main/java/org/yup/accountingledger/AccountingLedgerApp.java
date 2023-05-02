package org.yup.accountingledger;

import java.util.Scanner;

public class AccountingLedgerApp {

    public static void main (String [] args){

        Scanner userScanner = new Scanner(System.in);

        boolean appRunning = true;

        while(appRunning){

            String homeScreenChoice = Screens.homeScreen();

            switch (homeScreenChoice) {
                case "D":
                    System.out.println("Please enter the deposit DESCRIPTION: ");
                    System.out.println("Please enter the deposit VENDOR: ");
                    System.out.println("Please enter the deposit AMOUNT: ");
                    break;

                case "P":
                    System.out.println("Please enter the payment DESCRIPTION: ");
                    System.out.println("Please enter the payment VENDOR: ");
                    System.out.println("Please enter the payment AMOUNT: ");
                    break;

                case "L":
                    break;

                case "X":
                    break;

                default:
                    break;


            }

        }

    }

}
