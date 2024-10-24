package org.example.Service;

import org.example.Entity.Contact;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ServiceContact {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Contact> contacts = new HashMap<>();
    private static final String FILE_PATH = "contacts.txt";  // Путь к файлу для сохранения/загрузки контактов

    // Метод для получения всех контактов в виде карты
    public static Map<String, Contact> getContacts() {
        return contacts;
    }

    // Метод для добавления контакта с вводом данных через консоль и валидацией
    public static void addContact() {
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();

        String passNumber;
        while (true) {
            System.out.print("Введите номер пропуска (длина пропуска 8 символов, буквы и цифры [0-9A-F]): ");
            passNumber = scanner.nextLine().toUpperCase();
            if (isValidPassNumber(passNumber) && !contacts.containsKey(passNumber)) {
                break;
            } else if (!isValidPassNumber(passNumber)) {
                System.out.println("Некорректный формат номера пропуска. Попробуйте снова.");
            } else {
                System.out.println("Номер пропуска уже существует.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("Введите номер телефона (+7XXXXXXXXXX): ");
            phoneNumber = scanner.nextLine();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Некорректный формат номера телефона. Попробуйте снова.");
            }
        }

        contacts.put(passNumber, new Contact(firstName, lastName, passNumber, phoneNumber));
        System.out.println("Контакт добавлен.");
    }

    // Метод для добавления контакта с параметрами
    public static void addContact(String firstName, String lastName, String passNumber, String phoneNumber) {
        if (!isValidPassNumber(passNumber) || contacts.containsKey(passNumber)) {
            System.out.println("Некорректный или уже существующий номер пропуска.");
            return;
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Некорректный номер телефона.");
            return;
        }

        // Добавление контакта в коллекцию
        contacts.put(passNumber, new Contact(firstName, lastName, passNumber, phoneNumber));
        System.out.println("Контакт добавлен.");
    }

    // Метод для удаления контакта по ID
    public static void deleteContact(int id) {
        Optional<Contact> contactToRemove = contacts.values().stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();

        if (contactToRemove.isPresent()) {
            contacts.remove(contactToRemove.get().getPassNumber());
            System.out.println("Контакт удален.");
        } else {
            System.out.println("Контакт с таким ID не найден.");
        }
    }

    // Метод для удаления контакта с вводом ID через консоль
    public static void deleteContact() {
        System.out.print("Введите ID контакта для удаления: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            deleteContact(id); // Вызов метода для удаления контакта по ID
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод ID. Пожалуйста, введите целое число.");
        }
    }

    // Метод для просмотра всех контактов
    public static void viewAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Нет добавленных контактов.");
        } else {
            System.out.println("\nСписок контактов:");
            for (Contact contact : contacts.values()) {
                System.out.println(contact);
            }
        }
    }

    // Метод для вывода всех номеров пропусков
    public static void viewAllPassNumbers() {
        if (contacts.isEmpty()) {
            System.out.println("Нет добавленных контактов.");
        } else {
            int i = 1;
            System.out.println("\nСписок номеров пропусков:");
            for (String passNumber : contacts.keySet()) {
                System.out.println("№" + i++ + " - " + passNumber);
            }
        }
    }

    // Метод для поиска контакта по номеру пропуска
    public static void searchContactByPass() {
        System.out.print("Введите номер пропуска для поиска: ");
        String passNumber = scanner.nextLine().toUpperCase();

        if (contacts.containsKey(passNumber)) {
            System.out.println("Контакт найден: " + contacts.get(passNumber));
        } else {
            System.out.println("Контакт с таким номером пропуска не найден.");
        }
    }

    // Метод для поиска контакта по номеру пропуска (для тестирования)
    public static Contact searchContactByPass(String passNumber) {
        return contacts.get(passNumber);
    }

    // Метод для загрузки контактов из файла
    public static void loadContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length == 5) {
                    Contact contact = new Contact(fields[1], fields[2], fields[3], fields[4]);
                    contacts.put(contact.getPassNumber(), contact);
                }
            }
            System.out.println("Контакты успешно загружены.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, загрузка пропущена.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
        }
    }

    // Метод для сохранения контактов в файл
    public static void saveContacts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts.values()) {
                pw.println(contact.getId() + "\t" + contact.firstname + "\t" + contact.lastname +
                        "\t" + contact.getPassNumber() + "\t" + contact.getPhoneNumber());
            }
            System.out.println("Контакты успешно сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла.");
        }
    }

    // Метод для проверки корректности номера пропуска
    public static boolean isValidPassNumber(String passNumber) {
        return passNumber.matches("[0-9A-F]{8}");
    }

    // Метод для проверки корректности номера телефона
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+7\\d{10}");
    }
}
