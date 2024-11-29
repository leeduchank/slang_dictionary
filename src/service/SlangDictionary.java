package service;

import model.SlangWord;
import util.FileManager;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class SlangDictionary {
    private Map<String, String> dictionary;
    private List<String> searchHistory; // Lưu lịch sử tìm kiếm từ lóng


    public SlangDictionary() {
        dictionary = FileManager.loadData("src/slang.txt");
        searchHistory = new ArrayList<>(); // Khởi tạo danh sách lịch sử tìm kiếm

    }

    public void addSlangWord(Scanner scanner) {

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
                System.out.println(entry.getKey() + ": " + entry.getValue());  // In ra từ lóng và định nghĩa
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
