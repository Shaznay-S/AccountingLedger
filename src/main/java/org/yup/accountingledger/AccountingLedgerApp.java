package org.yup.accountingledger;

import static org.yup.accountingledger.Screens.userScanner;

public class AccountingLedgerApp {

    public static void main (String [] args){

        System.out.println("Welcome to the PLUR ledger!\n");

        boolean appRunning = true;

        while(appRunning){

            String homeScreenChoice = homeScreen();

            switch (homeScreenChoice.toLowerCase()) {
                case "d":
                    System.out.print(Screens.depositScreen());
                    break;

                case "p":
                    System.out.print(Screens.paymentScreen());
                    break;

                case "l":
                    System.out.print(Screens.ledgerScreen());

                    break;

                case "x":
                    appRunning = false;
                    break;

                default:
                    System.out.println("Please choose a viable option.\n");
                    break;

            }


        }

        System.out.println("PLUR is life.");

    }

    public static String homeScreen() {

        System.out.println("Please choose from these actions: ");
        System.out.println("\tD * to make a DEPOSIT");
        System.out.println("\tP * to make a PAYMENT");
        System.out.println("\tL * to display the LEDGER screen");
        System.out.println("\tX * to EXIT\n");

        System.out.print("Please make your selection: ");
        return userScanner.nextLine();

    }

}
