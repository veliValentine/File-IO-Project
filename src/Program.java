
import java.util.Set;

import fileReader.FileInformationReader;
import interactions.UserInteractions;

// javac -d ../out ../src/*.java ../src/fileReader/*.java ../src/interactions/*.java ../src/logger/*.java
// jar cfe File-IO-program.jar Program *.class fileReader/*.class interactions/*.class logger/*.class
public class Program {
    public static void main(String[] args) {
        FileInformationReader files = new FileInformationReader();
        UserInteractions interact = new UserInteractions();

        printGreetings();
        while (true) {
            // print main actions and ask what to do
            printMainLoopActions();
            int userInput = interact.inputInt();

            if (userInput == 0) { // Quit main loop
                break;
            } else if (userInput == 1) { // print all file names in resource folder
                System.out.println("All files");
                files.printAllFileNames();

            } else if (userInput == 2) { // choose extension and print these file names
                System.out.println("Extensions");
                Set<String> allExtensions = files.listAllFileExtensions();
                System.out.println();
                String extension = interact.inputExtension(allExtensions);
                files.printAllFileNames(extension);

            } else if (userInput == 3) { // perform operations to specific file
                Set<String> allTextFiles = files.listAllUniqueFileNames("txt");
                // interct.inputFile requires a set of filenames to compare if the file exsists
                String fileName = interact.inputFile(allTextFiles);
                if (!fileName.equals("")) { // or else continue the main-loop
                    // File operation loop
                    while (true) {
                        // print possible actions and ask user what to do for the selected .txt file
                        printFileManipulationActions();
                        int action = interact.inputInt();

                        if (action == 0) {// exit file operations loop
                            break;

                        } else if (action == 1) { // print file name
                            System.out.println("  " + fileName);

                        } else if (action == 2) { // prints file size
                            files.size(fileName);

                        } else if (action == 3) { // amount of lines
                            files.amountOfLines(fileName);

                        } else if (action == 4) { // asks for a word and sees if that specific word is in the file
                            String word = interact.input("Enter word: ");
                            files.containsWord(word, fileName);

                        } else if (action == 5) { // asks for a word and sees how many times that word is in the file
                            String word = interact.input("Enter word: ");
                            files.countWord(word, fileName);
                        }
                        // to give user some time to read the result before adding the menu printout
                        interact.input("Press enter to continue");
                    }
                }
            }
            // to give user some time to read the result before adding the menu printout
            interact.input("Press enter to continue");
        }
        interact.close();
        System.out.println("Thank you for using File IO");
    }

    private static void printMainLoopActions() {
        System.out.println("-------------------");
        System.out.println("Codes for actions");
        System.out.println("  1: List all files");
        System.out.println("  2: Get files by extension");
        System.out.println("  3: Operate a .txt file");
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

    private static void printGreetings() {
        System.out.println("-------------------");
        System.out.println("Welcome to the File IO");
    }
}