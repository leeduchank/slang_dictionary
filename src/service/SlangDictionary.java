package service;

import util.FileManager;

import java.util.Map;
import java.util.Scanner;



public class SlangDictionary {
    private Map<String, String> dictionary;


    public SlangDictionary() {
        dictionary = FileManager.loadData("src/slang.txt");

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


}
