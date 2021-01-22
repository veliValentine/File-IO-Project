
import java.util.Set;

import files.Files;
import interactions.UserInteractions;

// javac -d ../out ../src/*.java ../src/files/*.java ../src/interactions/*.java ../src/logger/*.java
public class Program {
    public static void main(String[] args) {
        Files files = new Files();
        UserInteractions interact = new UserInteractions();

        System.out.println("-------------------");
        System.out.println("Welcome to the File IO");
        while (true) {
            System.out.println("-------------------");
            printMainLoopActions();
            int userInput = interact.inputInt();
            System.out.println();
            if (userInput == 0) {
                break;
            } else if (userInput == 1) {
                System.out.println("All files");
                files.printAllFileNames();

            } else if (userInput == 2) {
                System.out.println("Extensions");
                Set<String> allExtensions = files.listAllFileExtensions();
                System.out.println();
                String extension = interact.inputExtension(allExtensions);
                System.out.println();
                files.printAllFileNames(extension);

            } else if (userInput == 3) {
                Set<String> allTextFiles = files.listAllUniqueFileNames("txt");
                String fileName = interact.inputFile(allTextFiles);
                System.out.println();
                if (!fileName.equals("")) { // or else continue the main-loop
                    // File manipulation loop
                    while (true) {
                        printFileManipulationActions();
                        int action = interact.inputInt();
                        System.out.println();
                        if (action == 0) {
                            break;

                        } else if (action == 1) {
                            System.out.println("  " + fileName);

                        } else if (action == 2) {
                            files.size(fileName, true);

                        } else if (action == 3) {
                            files.amountOfLines(fileName, true);

                        } else if (action == 4) {
                            String word = interact.input("Enter word: ");
                            System.out.println();
                            if (files.containsWord(word, fileName)) {
                                System.out.println("  File contains the word: " + word + "!");
                            } else {
                                System.out.println("  File doesn't contain the word: " + word);
                            }

                        } else if (action == 5) {
                            String word = interact.input("Enter word: ");
                            System.out.println();
                            files.countWord(word, fileName, true);
                        }
                        interact.input("Press enter to continue");
                    }
                }
            }

            interact.input("Press enter to continue");
        }
        interact.close();
        System.out.println("Thank you for using File IO");
    }

    private static void printMainLoopActions() {
        System.out.println("Codes for actions");
        System.out.println("  1: List all files");
        System.out.println("  2: Get files by extension");
        System.out.println("  3: Manipulate .txt file");
        System.out.println("  0: exit");
    }

    private static void printFileManipulationActions() {
        System.out.println("Choose action");
        System.out.println("  1:Print file name");
        System.out.println("  2:Print file size");
        System.out.println("  3:Print amount of lines");
        System.out.println("  4:See if specific word exists");
        System.out.println("  5:Count how many times a word is in the file");
        System.out.println("  0: Exit");
    }

}