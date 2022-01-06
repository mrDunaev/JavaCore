package lesson4;

import java.util.HashMap;

public class PhoneBook {
    HashMap<String, String> phones;

    public PhoneBook() {
        phones = new HashMap<>();
    }

    public HashMap<String, String> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        final String[] book = new String[1];
        book[0] = "Телефонная книга:\n";
        getPhones().forEach((key, value) -> book[0] += key + " - " + value + "\n");
        return book[0];
    }

    public void add(String phone, String surname) {
        getPhones().put(phone, surname);
    }

    public void get(String surname) {
        getPhones().forEach((key, value) -> {
           if (value.equals(surname))
               System.out.println(key + " - " + value);
        });
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("89049927676", "Смирнов");
        phoneBook.add("89212457524", "Иванов");
        phoneBook.add("89110354973", "Петров");
        phoneBook.add("89065664977", "Смирнов");
        phoneBook.add("89042229630", "Сидоров");
        phoneBook.add("89213563426", "Иванов");
        phoneBook.add("89119113243", "Иванов");

        System.out.println(phoneBook);
        System.out.println("\nПоиск по фамилии:");
        phoneBook.get("Иванов");
    }
}
