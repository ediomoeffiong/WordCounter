import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to count words or characters? ");
        String countChoice = scanner.nextLine().trim().toLowerCase();

        System.out.print("Do you want to count from a file or from your input? ");
        String sourceChoice = scanner.nextLine().trim().toLowerCase();

        if ("file".equals(sourceChoice)) {
            System.out.print("Enter the filename or directory (followed by the file extension): ");
            String filename = scanner.nextLine().trim();
            if ("words".equals(countChoice)) {
                countWordsInFile(filename);
            } else if ("characters".equals(countChoice)) {
                countCharactersInFile(filename);
            } else {
                System.out.print("Invalid count choice. Please enter 'words' or 'characters'.");
            }
        } else if ("input".equals(sourceChoice)) {
            System.out.print("Enter your text:");
            String userInput = scanner.nextLine();
            if ("words".equals(countChoice)) {
                countWordsInString(userInput);
            } else if ("characters".equals(countChoice)) {
                countCharactersInString(userInput);
            } else {
                System.out.print("Invalid count choice. Please enter 'words' or 'characters'.");
            }
        } else {
            System.out.print("Invalid source choice. Please enter 'file' or 'input'.");
        }

        scanner.close();
    }

    private static void countWordsInFile(String filename) {
        int wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Word count: " + wordCount);
    }

    private static void countCharactersInFile(String filename) {
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                if (!Character.isWhitespace(ch)) {
                    charCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Character count: " + charCount);
    }

    private static void countWordsInString(String input) {
        String[] words = input.split("\\s+");
        int wordCount = words.length;
        System.out.println("Word count: " + wordCount);
    }

    private static void countCharactersInString(String input) {
        int charCount = 0;
        for (char ch : input.toCharArray()) {
            if (!Character.isWhitespace(ch)) {
                charCount++;
            }
        }
        System.out.println("Character count: " + charCount);
    }
}
