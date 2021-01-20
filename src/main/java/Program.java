package main.java;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to File IO");
        while (true) {
            System.out.println("-------------------");
            System.out.println("Type code for action");
            //System.out.println("-Print actions");
            System.out.println("  1: List all files");
            System.out.println("  2: Get files by extension");
            System.out.println("  3: Manipulate file");
            System.out.println("  0: exit");
            String userInput = scanner.nextLine();
            if (userInput.equals("0")) {
                break;
            }
            switch (userInput) {
                case "1":
                    System.out.println("Listing all files...");
                    break;
                case "2":
                    System.out.println("Ask for extension");
                    System.out.println("List all files by extension");
                    break;
                case "3":
                    System.out.println("Asks file name");
                    System.out.println("List all possible methods");
                    break;
                default:
                    break;
            }
        }
        scanner.close();
        System.out.println("Thank you for using File IO");
    }

}