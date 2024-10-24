package org.example.Entity;

public class Contact {
    private static int idCounter = 1; //Счетчик идентификатор пользователя
    private final int id;
    public String firstname;
    public String lastname;
    public String passNumber;
    public String phoneNumber;

    public Contact(String firstname, String lastname, String passNumber, String phoneNumber) {
        this.id = idCounter++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.passNumber = passNumber;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nИмя: " + firstname + "\nФамилия: " + lastname +
                "\nНомер пропуска: " + passNumber + "\nТелефон: " + phoneNumber + "\n";
    }
}