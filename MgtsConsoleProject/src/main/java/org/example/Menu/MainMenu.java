package org.example.Menu;

import org.example.Service.ServiceContact;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuApp() {
        ServiceContact.loadContacts();// Загрузка контактов при старте приложения

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ServiceContact.addContact();
                    break;
                case "2":
                    ServiceContact.viewAllContacts();
                    break;
                case "3":
                    ServiceContact.viewAllPassNumbers();
                    break;
                case "4":
                    ServiceContact.searchContactByPass();
                    break;
                case "5":
                    ServiceContact.deleteContact();
                    break;
                case "6":
                    ServiceContact.saveContacts();// Сохранение контактов перед выходом
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите пункт от 1 до 6.");
            }
        }
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

