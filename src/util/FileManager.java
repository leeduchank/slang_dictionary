package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileManager {

    public static Map<String, String> loadData(String filePath) {
        Map<String, String> dictionary = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split("`", 2);
                if (parts.length == 2) {
                    dictionary.put(parts[0].trim(), parts[1].trim());
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return dictionary;
    }


    public static void saveData(String filePath, Map<String, String> dictionary) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                bw.write(entry.getKey() + "`" + entry.getValue());
                bw.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    public static void saveDefaultData(Map<String, String> dictionary) {
        saveData("src/slang_default.txt", dictionary);
    }
    public static Map<String, String> loadDefaultData() {
        return loadData("src/slang_default.txt");
    }
}

