import java.util.Set;

import files.Files;
import interactions.UserInteractions;

public class Program {
    public static void main(String[] args) {
        program();
    }

    public static void program() {

        Files files = new Files();
        UserInteractions interact = new UserInteractions();

        System.out.println("\nWelcome to File IO");
        while (true) {
            System.out.println("-------------------");
            System.out.println("Type code for action");
            System.out.println("  1: List all files");
            System.out.println("  2: Get files by extension");
            System.out.println("  3: Manipulate file");
            System.out.println("  0: exit");
            int userInput = interact.inputInt();
            if (userInput == 0) {
                break;
            }
            switch (userInput) {
                case 1:
                    System.out.println("All files");
                    files.listAllFileNames();
                    break;
                case 2:
                    System.out.println("Extensions");
                    Set<String> allExtensions = files.listAllFileExtensions();
                    System.out.println("Choose one...");
                    String extension = interact.inputExtension(allExtensions);
                    files.listAllFileNames(extension);
                    break;
                case 3:
                    System.out.println("Asks file name");
                    System.out.println("List all possible methods");
                    break;
                default:
                    System.out.println("Command not listed");
                    break;
            }
            System.out.println("Press enter to continue");
            interact.input();
        }
        interact.close();
        System.out.println("Thank you for using File IO");
    }

}