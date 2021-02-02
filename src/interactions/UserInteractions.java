package interactions;

import java.util.Scanner;
import java.util.Set;

public class UserInteractions {

    private Scanner scanner;

    public UserInteractions() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
        ;
    }

    //input without text
    public String input() {
        return input("");
    }

    // input
    public String input(String text) {
        System.out.print(text);
        String input = scanner.nextLine().trim();
        System.out.println();
        return input;
    }

    // input integer
    public int inputInt() {
        while (true) {
            String input = input("Enter integer: ");
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Input not an integer!");
            }
        }
    }

    // input for file extension. Compares that the file extension is found in the files
    public String inputExtension(Set<String> extensions) {
        System.out.println("To quit press enter");
        while (true) {
            String extension = input("Enter extension: ");
            if (extensions.contains(extension) || extension.equals("")) {
                return extension;
            }
            System.out.println("Not valid extension");
        }
    }

    // input file. Filenames makes sure that user enters file in the folder
    public String inputFile(Set<String> fileNames) {
        System.out.println("Press enter to quit");
        while (true) {
            String file = input("Enter file name: ");
            if (file.equals("") || fileNames.contains(file)) {
                return file;
            }
            System.out.println("File not found");
        }
    }
}
