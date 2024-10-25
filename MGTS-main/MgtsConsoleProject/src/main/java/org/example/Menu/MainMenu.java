package org.example.Menu;

import org.example.Entity.Contact;
import org.example.Service.ServiceContact;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuApp() {
        ServiceContact.loadContacts(); // Загрузка контактов при старте приложения

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addContactFromInput();
                    break;
                case "2":
                    ServiceContact.viewAllContacts();
                    break;
                case "3":
                    ServiceContact.viewAllPassNumbers();
                    break;
                case "4":
                    searchContactFromInput();
                    break;
                case "5":
                    deleteContactFromInput();
                    break;
                case "6":
                    ServiceContact.saveContacts(); // Сохранение контактов перед выходом
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите пункт от 1 до 6.");
            }
        }
    }

    private static void addContactFromInput() {
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();

        String passNumber;
        while (true) {
            System.out.print("Введите номер пропуска (длина пропуска 8 символов, буквы и цифры [0-9A-F]): ");
            passNumber = scanner.nextLine().toUpperCase();
            if (ServiceContact.isValidPassNumber(passNumber) && !ServiceContact.getContacts().containsKey(passNumber)) {
                break;
            } else if (!ServiceContact.isValidPassNumber(passNumber)) {
                System.out.println("Некорректный формат номера пропуска. Попробуйте снова.");
            } else {
                System.out.println("Номер пропуска уже существует.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("Введите номер телефона (+7XXXXXXXXXX): ");
            phoneNumber = scanner.nextLine();
            if (ServiceContact.isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Некорректный формат номера телефона. Попробуйте снова.");
            }
        }

        ServiceContact.addContact(firstName, lastName, passNumber, phoneNumber);
    }

    private static void deleteContactFromInput() {
        System.out.print("Введите ID контакта для удаления: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            ServiceContact.deleteContact(id);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод ID. Пожалуйста, введите целое число.");
        }
    }

    private static void searchContactFromInput() {
        System.out.print("Введите номер пропуска для поиска: ");
        String passNumber = scanner.nextLine().toUpperCase();
        ServiceContact.searchContactByPass(passNumber);
    }


    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Просмотреть все контакты");
        System.out.println("3. Просмотреть только номера пропусков");
        System.out.println("4. Поиск контакта по номеру пропуска");
        System.out.println("5. Удалить контакт");
        System.out.println("6. Выход");
        System.out.print("Выберите пункт: ");
    }
}

