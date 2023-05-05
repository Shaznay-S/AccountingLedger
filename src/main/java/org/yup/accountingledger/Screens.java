package org.yup.accountingledger;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Screens {

    static Scanner userScanner = new Scanner(System.in);

    public static String homeScreen() {

        //displays the main screen of the app
        System.out.println("Please choose from these actions: ");
        System.out.println("\tD * to add to make a DEPOSIT");
        System.out.println("\tP * to make a PAYMENT");
        System.out.println("\tL * to display the LEDGER screen\n");
        System.out.println("\tX * to EXIT\n");

        System.out.print("Please enter your selection: ");
        return userScanner.nextLine();

    }

    public static String depositScreen() {
        //code to make sure that the user inputs a valid number and not a string
        System.out.println("Please enter deposit information below: ");
        float depositAmount;
        while (true) {
            System.out.print("\tEnter the deposit AMOUNT: $");

            try {
                depositAmount = userScanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userScanner.next(); // clear the invalid input from the scanner
            }
        }
        userScanner.nextLine();
        System.out.print("\tEnter the deposit SOURCE: ");
        String depositVendor = userScanner.nextLine();
        System.out.print("\tEnter the deposit DESCRIPTION: ");
        String depositDescription = userScanner.nextLine();

        try {

            Transaction depositTransaction = new Transaction(formattedDate(), formattedTime(),
                    depositDescription, depositVendor, depositAmount);
            //put my writer, and my format in their own respective methods which I am calling in below
            transactionWriter(transactionEntryFormat(depositTransaction));

            System.out.println("MONEYYYYYY is recorded successfully!\n");


        } catch (IOException e) {
            System.out.println("ERROR: Deposit cannot be made.\n");
        }

        return "";

    }

    public static String paymentScreen() {

        System.out.println("Please enter payment information below:");
        float paymentAmount;
        while (true) {
            System.out.print("\tEnter the payment AMOUNT: $");
            //same thing as the one on my deposit screen but this time it makes sure that
            // whatever input the user gives it will always be negative
            try {
                paymentAmount = userScanner.nextFloat();
                paymentAmount *= -1;
                userScanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userScanner.next();
            }
        }

        System.out.print("\tEnter the payment RECIPIENT: ");
        String paymentVendor = userScanner.nextLine();
        System.out.print("\tEnter the payment DESCRIPTION: ");
        String paymentDescription = userScanner.nextLine();

        try {

            Transaction paymentTransaction = new Transaction(formattedDate(), formattedTime(),
                    paymentDescription, paymentVendor, paymentAmount);

            transactionWriter(transactionEntryFormat(paymentTransaction));

            System.out.println("Payment transaction recorded successfully! Try not to go into debt\n");


        } catch (IOException e) {
            System.out.println("ERROR: Deposit cannot be made.");
        }

        return "";

    }

    public static String ledgerScreen() {

        boolean ledgerRunning = true;

        while (ledgerRunning) {
            System.out.println("Please choose from these OPTIONS:");
            System.out.println("\tA * to view ALL");
            System.out.println("\tD * to view DEPOSITS");
            System.out.println("\tP * to view PAYMENTS");
            System.out.println("\tR * to view SPECIFIC REPORTS");
            System.out.println("\tH * to go back to the HOME page\n");
            System.out.println("\tX * to EXIT\n");

            System.out.print("Please enter your selection: ");
            String ledgerScreenChoice = userScanner.nextLine();

            switch (ledgerScreenChoice.toLowerCase()) {
                case "a":
                    try {
                        BufferedReader bufferedReader = transactionReader();
                        ArrayList<Transaction> transactionLines = new ArrayList<>();
                        String transactionLine;

                        while ((transactionLine = bufferedReader.readLine()) != null) {
//                            String[] transactionData = transactionLine.split("\\|");
//                            transactionData[4] = transactionData[4].replace("$", "");
//                            Transaction transaction = new Transaction(transactionData[0], transactionData[1],
//                                    transactionData[2], transactionData[3], Float.parseFloat(transactionData[4]));
//                            transactionLines.add(transaction);

                            System.out.println(transactionLine + "\n");
                        }

//                        for (int t = transactionLines.size() - 1; t < transactionLines.size() && t > 0; t--) {
//                            Transaction transaction = transactionLines.get(t);
//                            System.out.printf(transactionEntryFormat(transaction));
//                        }

                        bufferedReader.close();

                    } catch (IOException e) {
                        System.out.println("ERROR" + e.getMessage());
                    }
                    break;

                case "d":
                    try {
                        BufferedReader bufferedReader = transactionReader();

                        String transactionLine;

                        while ((transactionLine = bufferedReader.readLine()) != null) {

                            String[] transactionData = transactionLine.split("\\|");
                            transactionData[4] = transactionData[4].replace("$", "");
                            float transactionAmount = Float.parseFloat(transactionData[4]);


                            if (transactionAmount > 0) {
                                System.out.println(transactionLine + "\n");
                            }

                        }
                        bufferedReader.close();
                    } catch (IOException e) {
                        System.out.println("ERROR" + e.getMessage());
                    }
//                    List<Transaction> transactions = readTransactionsFromFile("transactions.csv");
//
//                    for (Transaction transaction : transactions) {
//                        if (transaction.getAmount() > 0) {
//                            System.out.println(transactionEntryFormat(transaction) + "\n");
//                        }
//                    }
                    break;

                case "p":
                    try {
                        BufferedReader bufferedReader = transactionReader();

                        String transactionLine;

                        while ((transactionLine = bufferedReader.readLine()) != null) {

                            String[] transactionData = transactionLine.split("\\|");
                            transactionData[4] = transactionData[4].replace("$", "");
                            float transactionAmount = Float.parseFloat(transactionData[4]);


                            if (transactionAmount < 0) {
                                System.out.println(transactionLine + "\n");

                            }
                        }

                        bufferedReader.close();

                    } catch (IOException e) {
                        System.out.println("ERROR" + e.getMessage());

                    }
                    break;

                case "r":
                    try {
                        reportsScreen();
                    } catch (IOException e) {
                        System.out.println("ERROR: Cannot load Reports Screen.");
                    }
                    ledgerRunning = false;
                    break;

                case "h":
                    ledgerRunning = false;
                    AccountingLedgerApp.runLedger();
                    break;

                case "x":
                    System.out.println("Manifesting more money for you.");
                    ledgerRunning = false;
                    break;

                default:
                    System.out.println("Please choose a viable option.");
                    break;

            }
        }
        return "";
    }

    public static String reportsScreen() throws IOException {

        System.out.println("Please choose from these OPTIONS:");
        System.out.println("\t1 * to view MONTH to DATE");
        System.out.println("\t2 * to view PREVIOUS MONTH");
        System.out.println("\t3 * to view YEAR to DATE");
        System.out.println("\t4 * to view PREVIOUS YEAR");
        System.out.println("\t5 * to view by VENDOR");
        System.out.println("\t6 * to go back to the LEDGER menu\n");

        int reportsUserChoice;
        while (true) {
            System.out.print("Please enter your selection: ");
            try {
                reportsUserChoice = userScanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                userScanner.next();
            }

        }

        switch (reportsUserChoice) {
            case 1:
//                int caseOneCurrentYear = LocalDate.now().getYear();
//                int caseOneCurrentMonth = LocalDate.now().getMonthValue();
//                BufferedReader caseOneReader = new BufferedReader(new FileReader("transactions"));
//                printTransactionMonthHistory(caseOneReader, caseOneCurrentYear, caseOneCurrentMonth, caseOneCurrentMonth, "Month-to-Date", true);
                break;
            case 2:
//                int caseTwoCurrentYear = LocalDate.now().getYear();
//                int caseTwoCurrentMonth = LocalDate.now().getMonthValue();
//                int previousMonth = caseTwoCurrentMonth == 1 ? 12 : caseTwoCurrentMonth - 1;
//                BufferedReader caseTwoReader = new BufferedReader(new FileReader("transactions"));
//                printTransactionMonthHistory(caseTwoReader, caseTwoCurrentYear, caseTwoCurrentMonth, previousMonth, "Previous Month", false);
                break;
            case 3:
//                LocalDate case3Date = LocalDate.now();
//                int currentYear = case3Date.getYear();
//                BufferedReader caseThreeReader = new BufferedReader(new FileReader("transactions"));
//                printTransactionYearHistory(caseThreeReader, currentYear, "Transaction History for Year-to-Date");
                break;
            case 4:
//                LocalDate case4Date = LocalDate.now();
//                int previousYear = case4Date.minusYears(1).getYear();
//                BufferedReader caseFourReader = new BufferedReader(new FileReader("transactions"));
//                printTransactionYearHistory(caseFourReader, previousYear, "Transaction History for Previous Year");
                break;
            case 5:
                System.out.println(searchByVendor());
                break;
            case 6:
                ledgerScreen();
                break;
            default:
                System.out.println("Please choose a viable option.");
                break;

        }

        return "";
    }

    public static void transactionMonthHistory() throws IOException {
        BufferedReader bufferedReader = transactionReader();
        String transactionLine;
        ArrayList<Transaction> transactionList = new ArrayList<>();

        while ((transactionLine = bufferedReader.readLine()) != null) {

            String[] transactionData = transactionLine.split("\\|");
            transactionData[4] = transactionData[4].replace("$", "");
            float transactionAmount = Float.parseFloat(transactionData[4]);
            Transaction transaction = new Transaction(transactionData[0], transactionData[1],
                    transactionData[2], transactionData[3], transactionAmount);
            transactionList.add(transaction);
        }

    }

    public static String searchByVendor () throws IOException {

        System.out.println("Please enter the vendor: ");
        String vendor = userScanner.nextLine();

        BufferedReader bufferedReader = transactionReader();
        String transactionLine;
        ArrayList<Transaction> transactionList = new ArrayList<>();

        while ((transactionLine = bufferedReader.readLine()) != null) {

            String[] transactionData = transactionLine.split("\\|");
            transactionData[4] = transactionData[4].replace("$", "");
            float transactionAmount = Float.parseFloat(transactionData[4]);
            Transaction transaction = new Transaction(transactionData[0], transactionData[1],
                    transactionData[2], transactionData[3], transactionAmount);
            transactionList.add(transaction);
        }

        for (int l = transactionList.size() - 1; l < transactionList.size() && l > 0; l--) {
            Transaction transaction = transactionList.get(l);
            if (vendor.equalsIgnoreCase(transaction.getVendor().trim())) {
                System.out.printf(transactionEntryFormat(transaction));
            }
        }
        return"";
    }

    public static void transactionWriter(String transaction) throws IOException {
        FileWriter fileWriter = new FileWriter("transactions.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(transaction);
        bufferedWriter.close();
        fileWriter.close();

    }

    public static String transactionEntryFormat(Transaction transaction) {
        String transactionEntry = String.format("%s | %s | %s | %s | $%.2f\n",
                transaction.getDate(), transaction.getTime(), transaction.getDescription(),
                transaction.getVendor(), transaction.getAmount());
        return transactionEntry;
    }

    public static List<Transaction> readTransactionsFromFile(String file) {
        List<Transaction> transactions = new ArrayList<>();

        try (FileReader fileReader = new FileReader("transactions.csv");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            bufferedReader.readLine();

            String transactionLine;
            while ((transactionLine = bufferedReader.readLine()) != null) {
                String[] transactionData = transactionLine.split("\\|");
                transactionData[4] = transactionData[4].replace("$", "");
                float transactionAmount = Float.parseFloat(transactionData[4]);

                Transaction transaction = new Transaction(transactionData[0], transactionData[1],
                        transactionData[2], transactionData[3], transactionAmount);
                transactions.add(transaction);
            }

        } catch (IOException e) {
            System.out.println("Error reading transaction file: " + e.getMessage());
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Error parsing transaction data: " + e.getMessage());
        }

        return transactions;
    }

    public static BufferedReader transactionReader() throws IOException {
        FileReader fileReader = new FileReader("transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine(); // skip the header row
        return bufferedReader;
    }

    public static String formattedTime(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }

    public static String formattedDate(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

}



//    public static void printTransactionYearHistory(BufferedReader transactionReader, int year, String header) throws IOException {
//        System.out.printf("%s (%d):\n", header, year);
//        String transactionLine;
//        while ((transactionLine = transactionReader.readLine()) != null) {
//            String[] transactionData = transactionLine.split("\\|");
//            String[] dateParts = transactionData[0].split("-");
//            int transactionYear = Integer.parseInt(dateParts[0]);
//            if (transactionYear == year) {
//                Transaction transaction = new Transaction(transactionData[2],
//                        transactionData[3], Float.parseFloat(transactionData[4])
//                );
//                String transactionEntry = transactionEntryFormat(transaction);
//                System.out.print(transactionEntry);
//            }
//        }
//    }
//
//    public static void printTransactionMonthHistory(BufferedReader transactionReader, int currentYear, int currentMonth, int previousMonth, String label, boolean excludeCurrentMonth) throws IOException {
//        if (!excludeCurrentMonth) {
//            System.out.printf("Transaction History for %s (%d-%02d):\n", label, currentYear, currentMonth);
//        } else {
//            System.out.printf("Transaction History for %s (%d-%02d; through %d-%02d):\n", label, currentYear, previousMonth, currentYear, currentMonth);
//        }
//
//        String transactionLine;
//        while ((transactionLine = transactionReader.readLine()) != null) {
//            String[] transactionData = transactionLine.split(",");
//            String[] dateParts = transactionData[0].split("-");
//            int year = Integer.parseInt(dateParts[0]);
//            int month = Integer.parseInt(dateParts[1]);
//            if (year == currentYear && (excludeCurrentMonth ? month == previousMonth : month == currentMonth)) {
//                Transaction transaction = new Transaction(transactionData[2],
//                        transactionData[3], Float.parseFloat(transactionData[4]));
//                String transactionEntry = transactionEntryFormat(transaction);
//                System.out.print(transactionEntry);
//            }
//        }
//    }




