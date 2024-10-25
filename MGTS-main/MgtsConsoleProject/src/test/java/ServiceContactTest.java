
import org.example.Entity.Contact;
import org.example.Service.ServiceContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceContactTest {

    @BeforeEach
    void setUp() {
        // Перед каждым тестом очищаем контакты
        Map<String, Contact> contacts = ServiceContact.getContacts();
        contacts.clear();
    }

    // Тест для метода валидации номера пропуска
    @Test
    void testValidPassNumber() {
        assertTrue(ServiceContact.isValidPassNumber("A1B2C3D4"));
        assertFalse(ServiceContact.isValidPassNumber("1234567"));  // Меньше 8 символов
        assertFalse(ServiceContact.isValidPassNumber("A1B2C3D45")); // Больше 8 символов
        assertFalse(ServiceContact.isValidPassNumber("A1B2C3DG"));  // Содержит недопустимые символы
    }

    // Тест для метода валидации номера телефона
    @Test
    void testValidPhoneNumber() {
        assertTrue(ServiceContact.isValidPhoneNumber("+71234567890"));
        assertFalse(ServiceContact.isValidPhoneNumber("81234567890")); // Нет +7
        assertFalse(ServiceContact.isValidPhoneNumber("+7123456789"));  // Меньше 10 цифр
        assertFalse(ServiceContact.isValidPhoneNumber("+712345678901")); // Больше 10 цифр
    }

    // Тест для добавления контакта
    @Test
    void testAddContact() {
        ServiceContact.addContact("Иван", "Иванов", "A1B2C3D4", "+71234567890");
        assertEquals(1, ServiceContact.getContacts().size());
        assertNotNull(ServiceContact.getContacts().get("A1B2C3D4"));

        // Проверка добавленного контакта
        Contact contact = ServiceContact.getContacts().get("A1B2C3D4");
        assertEquals("Иван", contact.firstname);
        assertEquals("Иванов", contact.lastname);
        assertEquals("+71234567890", contact.getPhoneNumber());
    }

    // Тест для удаления контакта
    @Test
    void testDeleteContact() {
        // Добавим контакт для удаления
        ServiceContact.addContact("Иван", "Иванов", "A1B2C3D4", "+71234567890");
        assertEquals(1, ServiceContact.getContacts().size());

        // Удаляем контакт
        ServiceContact.deleteContact(1);
        assertEquals(0, ServiceContact.getContacts().size());
    }

    // Тест для поиска контакта по номеру пропуска
    @Test
    void testSearchContactByPass() {
        ServiceContact.addContact("Иван", "Иванов", "A1B2C3D4", "+71234567890");

        Contact foundContact = ServiceContact.searchContactByPass("A1B2C3D4");
        assertNotNull(foundContact);
        assertEquals("Иван", foundContact.firstname);
        assertEquals("Иванов", foundContact.lastname);

        // Поиск по несуществующему номеру
        assertNull(ServiceContact.searchContactByPass("ZZZZZZZZ"));
    }
}