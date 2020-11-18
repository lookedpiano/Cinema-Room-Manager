import java.util.Scanner;

public class Cinema {

    private static int bought = 0;
    private static int income = 0;
    private static int totalIncome = 0;
    private static int ticketPrice;
    private static double percentage = 0;

    public static void printArrangement(char[][] seats, int rows, int cols) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit\n");
    }

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + bought);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void buyTicket(char[][] seats, int rows, int cols) {
        Scanner sc = new Scanner(System.in);
        int rowNum = 0;
        int colNum = 0;
        boolean free = false;
        while (!free) {
            System.out.println("Enter a row number:");
            rowNum = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            colNum = sc.nextInt();

            if (rowNum > rows || rowNum < 1 || colNum > cols || colNum < 1) {
                System.out.println("\nWrong input!\n");
            } else if (seats[rowNum][colNum] == 'B') {
                System.out.println("\nThat ticket has already been purchased!\n");
            } else {
                free = true;
            }
        }

        int totalSeats = rows * cols;
        if (totalSeats <= 60) {
            ticketPrice = 10;
            income += ticketPrice;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            ticketPrice = rows / 2 >= rowNum ? 10 : 8;
            income += ticketPrice;
        }
        System.out.printf("Ticket price: $%d\n", ticketPrice);
        seats[rowNum][colNum] = 'B';

        bought++;
        percentage = (double) 100 / (double) totalSeats * bought;
    }

    public static void makeSeatArrangement() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = sc.nextInt();

        char[][] seats = new char[rows + 1][cols + 1];
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                if (i == 0 && j == 0) {
                    seats[i][j] = ' ';
                } else if (i == 0) {
                    String s = Integer.toString(j);
                    seats[i][j] = s.charAt(0);
                } else if (j == 0) {
                    String s = Integer.toString(i);
                    seats[i][j] = s.charAt(0);
                } else {
                    seats[i][j] = 'S';
                }
            }
        }
        totalIncome = getTotalIncome(rows, cols);
        controller(seats, rows, cols);
    }

    public static int getTotalIncome(int rows, int cols) {
        int totalSeats = rows * cols;
        if (totalSeats <= 60) {
            ticketPrice = 10;
            totalIncome = totalSeats * ticketPrice;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            totalIncome = frontRows * cols * 10 + backRows * cols * 8;
        }
        return totalIncome;
    }

    public static void controller(char[][] seats, int rows, int cols) {
        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        while (!terminate) {
            printMenu();
            int decision = sc.nextInt();
            switch (decision) {
                case 1: printArrangement(seats, rows, cols); break;
                case 2: buyTicket(seats, rows, cols); break;
                case 3: statistics(); break;
                case 0: terminate = true; break;
            }
        }
    }

    public static void main(String[] args) {
        makeSeatArrangement();
    }
}