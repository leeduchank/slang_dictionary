package service;

import util.FileManager;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class SlangDictionary {
    private Map<String, String> dictionary;
    private List<String> searchHistory;


    public SlangDictionary() {
        dictionary = FileManager.loadData("src/slang.txt");
        searchHistory = new ArrayList<>();
    }

    public void addSlangWord(Scanner scanner) {
        System.out.print("Enter new slang word: ");
        String word = scanner.nextLine();
        System.out.print("Enter its definition: ");
        String definition = scanner.nextLine();

        if (dictionary.containsKey(word)) {
            System.out.println("Slang word already exists. Do you want to overwrite? (yes/no) Or create a new slang word? (new)");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("yes")) {
                dictionary.put(word, definition);
                System.out.println("Word overwritten.");
            } else if (response.equals("new")) {

                String newWord = word + "_new";
                dictionary.put(newWord, definition);
                System.out.println("New slang word added: " + newWord);
            } else {
                System.out.println("Word not added.");
            }
        } else {
            dictionary.put(word, definition);
            System.out.println("Word added successfully.");
        }

        FileManager.saveData("src/slang.txt", dictionary);
    }

    public void searchByWord(Scanner scanner) {
        System.out.print("Enter slang word: ");
        String word = scanner.nextLine();
        String definition = dictionary.get(word);
        if (definition != null) {
            System.out.println("Definition: " + definition);
            searchHistory.add(word);

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
                System.out.println(entry.getKey() + ": " + entry.getValue());
                found = true;
                searchHistory.add(entry.getKey());

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
        if (searchHistory.isEmpty()) {
            System.out.println("No search history.");
        } else {
            System.out.println("Search History:");
            for (String word : searchHistory) {
                System.out.println(word);
            }
        }
    }


}
