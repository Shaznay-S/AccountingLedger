package org.yup.accountingledger;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Screens {

    static Scanner userScanner = new Scanner(System.in);

//    public static String homeScreen() {
//
//        System.out.println("Please choose from these actions: ");
//        System.out.println("\tD * to make a DEPOSIT");
//        System.out.println("\tP * to make a PAYMENT");
//        System.out.println("\tL * to display the LEDGER screen");
//        System.out.println("\tX * to EXIT\n");
//
//        System.out.print("Please make your selection: ");
//        return userScanner.nextLine();
//
//    }

    public static String depositScreen() {

        float depositAmount;
        while (true) {
            System.out.print("Please enter the deposit AMOUNT: $");

            try {
                depositAmount = userScanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userScanner.next(); // clear the invalid input from the scanner
            }
        }
        userScanner.nextLine();
        System.out.print("Please enter the deposit VENDOR: ");
        String depositVendor = userScanner.nextLine();
        System.out.print("Please enter the deposit DESCRIPTION: ");
        String depositDescription = userScanner.nextLine();

        try {
            FileWriter transactionFile = new FileWriter("transactions.csv", true);
            BufferedWriter transactionsWriter = new BufferedWriter(transactionFile);

            Transaction transactions = new Transaction(depositDescription, depositVendor, depositAmount);

            String transactionEntry = String.format("%s | %s | %s | $%.2f\n", transactions.getDateTime(),
                    transactions.getDescription(), transactions.getVendor(), transactions.getAmount());

            transactionsWriter.write(transactionEntry);
            transactionsWriter.close();
            System.out.println("Deposit transaction recorded successfully!\n");


        } catch (IOException e) {
            System.out.println("ERROR: Deposit cannot be made.\n");
        }

        return "";

    }

    public static String paymentScreen() {

        float paymentAmount;
        while (true) {
            System.out.print("Please enter the payment AMOUNT: $");

            try {
                paymentAmount = userScanner.nextFloat();
                paymentAmount *= -1;
                userScanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userScanner.next(); // clear the invalid input from the scanner
            }
        }

        System.out.print("Please enter the payment VENDOR: ");
        String paymentVendor = userScanner.nextLine();
        System.out.print("Please enter the payment DESCRIPTION: ");
        String paymentDescription = userScanner.nextLine();

        try {

            FileWriter transactionFile = new FileWriter("transactions.csv", true);
            BufferedWriter transactionsWriter = new BufferedWriter(transactionFile);

            Transaction transactions = new Transaction(paymentDescription, paymentVendor, paymentAmount);

            String transactionEntry = String.format("%s | %s | %s | $%.2f\n", transactions.getDateTime(),
                    transactions.getDescription(), transactions.getVendor(), transactions.getAmount());

            transactionsWriter.write(transactionEntry);
            transactionsWriter.close();
            System.out.println("Payment transaction recorded successfully!\n");


        } catch (IOException e) {
            System.out.println("ERROR: Deposit cannot be made.");
        }

        return "";

    }

    public static String ledgerScreen() {

        System.out.println("A * to view ALL");
        System.out.println("D * to view DEPOSITS");
        System.out.println("P * to view PAYMENTS");
        System.out.println("R * to view SPECIFIC REPORTS");
        System.out.println("H * to go back to the HOME page\n");

        System.out.print("Please make your selection: ");
        String ledgerScreenChoice = userScanner.nextLine();

//        boolean ledgerRunning = true;
//
//        while (ledgerRunning) {

            switch (ledgerScreenChoice.toLowerCase()) {
                case "a":
                    try {
                        FileReader readTransactionFile = new FileReader("transactions.csv");
                        BufferedReader transactionReader = new BufferedReader(readTransactionFile);
                        transactionReader.readLine();

                        String transactionLine;

                        while ((transactionLine = transactionReader.readLine()) != null) {
                            System.out.println(transactionLine + "\n");
                        }

                        transactionReader.close();

                    } catch (IOException e) {
                        System.out.println("ERROR" + e.getMessage());
                    }

                    break;

                case "d":
                    try {
                        FileReader readTransactionFile = new FileReader("transactions.csv");
                        BufferedReader transactionReader = new BufferedReader(readTransactionFile);
                        transactionReader.readLine();

                        String transactionLine;

                        while ((transactionLine = transactionReader.readLine()) != null) {

                            String[] transactionData = transactionLine.split("\\|");
                            transactionData[4] = transactionData[4].replace("$", "");
                            float transactionAmount = Float.parseFloat(transactionData[4]);


                            if (transactionAmount > 0) {
                                System.out.println(transactionLine + "\n");

                            }

                        }

                        transactionReader.close();

                    } catch (IOException e) {
                        System.out.println("ERROR" + e.getMessage());
                    }

                    break;

                case "p":
                    try {
                        FileReader readTransactionFile = new FileReader("transactions.csv");
                        BufferedReader transactionReader = new BufferedReader(readTransactionFile);
                        transactionReader.readLine();

                        String transactionLine;

                        while ((transactionLine = transactionReader.readLine()) != null) {

                            String[] transactionData = transactionLine.split("\\|");
                            transactionData[4] = transactionData[4].replace("$", "");
                            float transactionAmount = Float.parseFloat(transactionData[4]);


                            if (transactionAmount < 0) {
                                System.out.println(transactionLine + "\n");

                            }

                        }

                        transactionReader.close();

                    } catch (IOException e) {
                        System.out.println("ERROR" + e.getMessage());
                    }
                    break;

                case "r":
                    reportsScreen();
                    break;

                case "h":
                    AccountingLedgerApp.homeScreen();
                    break;

                default:
                    System.out.println("Please choose a viable option.");
//                    ledgerRunning = false;
                    break;

            }


//        }

        return "";

    }

    public static String reportsScreen() {

        System.out.println("1 * to view MONTH to DATE");
        System.out.println("2 * to view PREVIOUS MONTH");
        System.out.println("3 * to view YEAR to DATE");
        System.out.println("4 * to view PREVIOUS YEAR");
        System.out.println("5 * to view by VENDOR");

        int reportsUserChoice;
        while (true) {
            System.out.println("Please make your selection: ");
            try {
                reportsUserChoice = userScanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userScanner.next(); // clear the invalid input from the scanner
            }

        }

            switch (reportsUserChoice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please choose a viable option.");
                    break;


            }

            return "";
        }

    }