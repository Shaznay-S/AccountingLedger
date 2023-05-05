package org.yup.accountingledger;

public class AccountingLedgerApp {

    public static void main(String[] args) {

        runLedger();

    }

    public static void runLedger() {

        System.out.println("WELCOME to your personal ledger!\n");

        boolean appRunning = true;

        while (appRunning) {

            String homeScreenChoice = Screens.homeScreen();

            switch (homeScreenChoice.toLowerCase()) {
                case "d":
                    System.out.print(Screens.depositScreen());
                    break;

                case "p":
                    System.out.print(Screens.paymentScreen());
                    break;

                case "l":
                    System.out.print(Screens.ledgerScreen());
                    appRunning = false;
                    break;

                case "x":
                    System.out.println("Manifesting more money for you.");
                    appRunning = false;
                    break;

                default:
                    System.out.println("Please choose a viable option.\n");
                    break;

            }
        }
    }
}




