
import java.util.Scanner;

import files.Files;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Files files = new Files("../resources");
        Files files = new Files();
        System.out.println("\nWelcome to File IO");
        while (true) {
            System.out.println("-------------------");
            System.out.println("Type code for action");
            // System.out.println("-Print actions");
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
                    files.listAllFileNames();
                    break;
                case "2":
                    System.out.println("Extensions");
                    files.listAllFileExtensions();
                    System.out.println("Choose one to list all the files");
                    String extension = scanner.nextLine();
                    files.listAllFileNames(extension);
                    break;
                case "3":
                    System.out.println("Asks file name");
                    System.out.println("List all possible methods");
                    break;
                default:
                    break;
            }
            System.out.println("Press anything to continue");
            scanner.nextLine();
        }
        scanner.close();
        System.out.println("Thank you for using File IO");
    }

}