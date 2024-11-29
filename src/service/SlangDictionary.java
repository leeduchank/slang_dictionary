package service;

import util.FileManager;

import java.util.Map;
import java.util.Scanner;



public class SlangDictionary {
    private Map<String, String> dictionary;


    public SlangDictionary() {
        dictionary = FileManager.loadData("src/slang.txt");

    }

    public void addSlangWord(Scanner scanner) {

    }

    public void searchByWord(Scanner scanner) {
        System.out.print("Enter slang word: ");
        String word = scanner.nextLine();
        String definition = dictionary.get(word);
        if (definition != null) {
            System.out.println("Definition: " + definition);
        } else {
            System.out.println("Slang word not found.");
        }
    }
    public void searchByDefinition(Scanner scanner) {
        System.out.print("Enter keyword to search in definitions: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().toLowerCase().contains(keyword)) {
                System.out.println(entry.getKey() + ": " + entry.getValue());  // In ra từ lóng và định nghĩa
                found = true;
            }
        }

        if (!found) {
            System.out.println("No slang words found for the given keyword.");
        } else {
            System.out.println("Search completed.");
        }
    }


    public void editSlangWord(Scanner scanner) {

    }
    public void displaySearchHistory() {

    }


}
