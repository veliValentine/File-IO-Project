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
        String input = scanner.nextLine().trim().toLowerCase();
        return input;
    }

    public int inputInt() {
        while (true) {
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
            String extension = input();
            if (extensions.contains(extension) || extension.equals("")) {
                return extension;
            }
            System.out.println("Not valid extension");
        }
    }
}
