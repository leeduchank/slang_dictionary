package main;

import service.SlangDictionary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SlangDictionary slangDictionary = new SlangDictionary();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Slang Dictionary Menu ---");
            System.out.println("1. Search by slang word");
            System.out.println("2. Add a new slang word");
            System.out.println("3. Edit a slang word");
            System.out.println("4. Display all slang words");
            System.out.println("5. Display search history");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    slangDictionary.searchByWord(scanner);
                    break;
                case 2:
                    slangDictionary.addSlangWord(scanner);
                    break;
                case 3:
                    slangDictionary.editSlangWord(scanner);
                    break;
                case 4:
                    slangDictionary.displayAllWords();
                    break;
                case 5:
                    slangDictionary.displaySearchHistory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
