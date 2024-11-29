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
            System.out.println("2. Search by definition");
            System.out.println("3. Display search history");
            System.out.println("4. Add a new slang word");
            System.out.println("5. Edit a slang word");
            System.out.println("6. Delete a slang word");
            System.out.println("7. Reset slang words to default");
            System.out.println("8. On this day slang word");
            System.out.println("9. Random slang word with choices");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    slangDictionary.searchByWord(scanner);  // Tìm kiếm theo từ lóng
                    break;
                case 2:
                    slangDictionary.searchByDefinition(scanner);  // Tìm kiếm theo định nghĩa
                    break;
                case 3:
                    slangDictionary.displaySearchHistory();  // Hiển thị lịch sử tìm kiếm
                    break;
                case 4:
                    slangDictionary.addSlangWord(scanner);  // Thêm từ lóng mới
                    break;
                case 5:
                    slangDictionary.editSlangWord(scanner);  // Chỉnh sửa từ lóng
                    break;
                case 6:
                    slangDictionary.deleteSlangWord(scanner);  // Xóa từ lóng
                    break;
                case 7:
                    slangDictionary.resetSlangWords();  // Reset từ lóng về mặc định
                    break;
                case 8:
                    slangDictionary.randomSlangWord();  // Hiển thị từ lóng ngẫu nhiên
                    break;
                case 9:
                    slangDictionary.randomSlangWordWithChoices();  // Hiển thị từ lóng ngẫu nhiên với các lựa chọn
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
