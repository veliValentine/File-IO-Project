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
    }

    public String input() {
        return input("");
    }

    public String input(String text) {
        System.out.print(text);
        String input = scanner.nextLine().trim();
        return input;
    }

    public int inputInt() {
        while (true) {
            System.out.print("Enter integer: ");
            String input = input();
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Input not an integer!");
            }
        }
    }

    public String inputExtension(Set<String> extensions) {
        System.out.println("To quit press enter");
        while (true) {
            System.out.print("Enter extension: ");
            String extension = input();
            if (extensions.contains(extension) || extension.equals("")) {
                return extension;
            }
            System.out.println("Not valid extension");
        }
    }

    public String inputFile(Set<String> fileNames) {
        System.out.println("Press enter to quit");
        while (true) {
            System.out.print("Enter file name: ");
            String file = input();
            if (file.equals("") || fileNames.contains(file)) {
                return file;
            }
            System.out.println("File not found");
        }
    }
}
