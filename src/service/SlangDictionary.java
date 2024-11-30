package service;

import util.FileManager;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class SlangDictionary {
    private Map<String, String> dictionary;
    private List<String> searchHistory;


    public SlangDictionary() {
        dictionary = FileManager.loadData("src/slang.txt");
        searchHistory = new ArrayList<>();
    }

    public void saveDefault(){
        FileManager.saveDefaultData(dictionary);

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
        System.out.print("Enter slang word to edit: ");
        String word = scanner.nextLine();
        if (dictionary.containsKey(word)) {
            System.out.print("Enter new definition: ");
            String newDefinition = scanner.nextLine();
            dictionary.put(word, newDefinition);
            System.out.println("Word updated successfully.");
            FileManager.saveData("src/slang.txt", dictionary);
        } else {
            System.out.println("Word not found.");
        }
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

    // Xóa một từ lóng
    public void deleteSlangWord(Scanner scanner) {
        System.out.print("Enter slang word to delete: ");
        String word = scanner.nextLine();

        if (dictionary.containsKey(word)) {
            System.out.println("Slang word '" + word + "' found. Do you want to delete it? (yes/no)");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("yes")) {
                dictionary.remove(word);
                System.out.println("Slang word '" + word + "' has been deleted.");
                FileManager.saveData("src/slang.txt", dictionary);
            } else {
                System.out.println("No action taken. Slang word not deleted.");
            }
        } else {
            System.out.println("Slang word not found.");
        }
    }
    public void resetSlangWords() {
        System.out.println("Resetting slang words to default...");

        dictionary = FileManager.loadDefaultData();

        FileManager.saveData("src/slang.txt", dictionary);

        System.out.println("Slang words have been reset to default.");
    }
    public void randomSlangWord() {
        if (dictionary.isEmpty()) {
            System.out.println("No slang words available.");
            return;
        }

        Random rand = new Random();
        Object[] slangWords = dictionary.keySet().toArray();
        String randomWord = (String) slangWords[rand.nextInt(slangWords.length)];

        String definition = dictionary.get(randomWord);

        System.out.println("On this day slang word: ");
        System.out.println(randomWord + ": " + definition);
    }
    public void randomSlangWordWithChoices() {
        if (dictionary.isEmpty()) {
            System.out.println("No slang words available.");
            return;
        }

        Random rand = new Random();

        Object[] slangWords = dictionary.keySet().toArray();
        String randomWord = (String) slangWords[rand.nextInt(slangWords.length)];

        String correctDefinition = dictionary.get(randomWord);

        List<String> incorrectDefinitions = new ArrayList<>();
        while (incorrectDefinitions.size() < 3) {
            String incorrectWord = (String) slangWords[rand.nextInt(slangWords.length)];
            String incorrectDefinition = dictionary.get(incorrectWord);

            if (!incorrectWord.equals(randomWord) && !incorrectDefinitions.contains(incorrectDefinition)) {
                incorrectDefinitions.add(incorrectDefinition);
            }
        }

        System.out.println("What does the slang word \"" + randomWord + "\" mean?");
        System.out.println("1. " + correctDefinition);
        System.out.println("2. " + incorrectDefinitions.get(0));
        System.out.println("3. " + incorrectDefinitions.get(1));
        System.out.println("4. " + incorrectDefinitions.get(2));

        System.out.print("Choose your answer (1-4): ");
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        if (userChoice == 1) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct definition is: " + correctDefinition);
        }
    }

    public void randomDefinitionWithChoices() {
        if (dictionary.isEmpty()) {
            System.out.println("No slang words available.");
            return;
        }

        Random rand = new Random();

        Object[] definitions = dictionary.values().toArray();
        String correctDefinition = (String) definitions[rand.nextInt(definitions.length)];

        List<String> incorrectSlangWords = new ArrayList<>();
        while (incorrectSlangWords.size() < 3) {
            String incorrectWord = (String) dictionary.keySet().toArray()[rand.nextInt(dictionary.size())];
            String incorrectDefinition = dictionary.get(incorrectWord);

            if (!incorrectDefinition.equals(correctDefinition) && !incorrectSlangWords.contains(incorrectWord)) {
                incorrectSlangWords.add(incorrectWord);
            }
        }

        System.out.println("What slang word matches the following definition?");
        System.out.println("Definition: " + correctDefinition);
        System.out.println("1. " + findKeyByValue(correctDefinition));
        System.out.println("2. " + incorrectSlangWords.get(0));
        System.out.println("3. " + incorrectSlangWords.get(1));
        System.out.println("4. " + incorrectSlangWords.get(2));

        System.out.print("Choose your answer (1-4): ");
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        if (userChoice == 1) {
            System.out.println("Correct! The slang word for the definition \"" + correctDefinition + "\" is: " + findKeyByValue(correctDefinition));
        } else {
            System.out.println("Incorrect. The correct slang word for the definition \"" + correctDefinition + "\" is: " + findKeyByValue(correctDefinition));
        }
    }

    private String findKeyByValue(String value) {
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
