package manager;

import iofile.IOFile;
import model.Contact;
import validate.Validate;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactsManager {
    private final String PATH_NAME = "D:\\4. Outlook sync\\OneDrive\\Desktop\\ThiModule2\\Quanlydanhba\\src\\data\\contacts.csv";
    private final IOFile ioFile = new IOFile();
    private final Validate validate = new Validate();
    private final ArrayList<Contact> contacts = ioFile.readFile(PATH_NAME);
    Scanner scanner = new Scanner(System.in);

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void displayAllContact(){
        for (Contact contact: contacts) {
            System.out.println(contact);
        }
    }

    public void deleteContact(String phoneNumber) {
        Contact deleteContact = null;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                deleteContact = contact;
            }
        }
        if (deleteContact != null) {
            System.out.println("Nhập xác nhận:");
            System.out.println("Y: Xóa");
            System.out.println("Ký tự khác: Thoát");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("y")) {
                contacts.remove(deleteContact);
                ioFile.writeFile(contacts,PATH_NAME);
                System.out.println(" Xóa " + phoneNumber + " thành công !");
                System.out.println("--------------------");
            }
        } else {
            System.out.println("Không tìm được số điện thoại trong danh ba!");
            System.out.println("--------------------");
        }
    }

    public void addNewContact(){
        Contact newContact = creatNewContact();
        contacts.add(newContact);
        ioFile.writeFile(contacts,PATH_NAME);
    }

    public Contact creatNewContact(){
        boolean phoneNumberCheck;
        String phoneNumber;
        System.out.println("Nhập số điện thoại mới");
        do{
            phoneNumber = scanner.nextLine();
            phoneNumberCheck = validate.validatePhone(phoneNumber);
        }while (!phoneNumberCheck);
        System.out.println("Nhập nhóm danh bạ");
        String group = scanner.nextLine();
        System.out.println("Nhập tên");
        String name = scanner.nextLine();
        System.out.println("Nhập giới tính");
        String gender = scanner.nextLine();
        System.out.println("Nhập địa chỉ");
        String address = scanner.nextLine();
        System.out.println("Nhập ngày sinh");
        String birthday = scanner.nextLine();
        boolean emailCheck;
        String email;
        do{
            System.out.println("Nhập email");
            email = scanner.nextLine();
            emailCheck = validate.validateEmail(email);
        }while (!emailCheck);
        return new Contact(phoneNumber,group,name,gender,address,birthday,email);
    }

    public void updateContact(String phoneNumber) {
        Contact editContact = null;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                editContact = contact;
            }
        }
        if (editContact != null) {
            int index = contacts.indexOf(editContact);
            System.out.println("Nhập tên nhóm mới:");
            editContact.setGroup(scanner.nextLine());
            System.out.println("Nhập Họ tên mới:");
            editContact.setName(scanner.nextLine());
            System.out.println("Nhập giới tính mới:");
            editContact.setGender(scanner.nextLine());
            System.out.println("Nhập địa chỉ mới:");
            editContact.setAddress(scanner.nextLine());
            System.out.println("Nhập ngày sinh mới:");
            editContact.setBirthday(scanner.nextLine());
            boolean emailCheck;
            String email;
            do{
                System.out.println("Nhập email");
                email = scanner.nextLine();
                emailCheck = validate.validateEmail(email);
            }while (!emailCheck);
            editContact.setEmail(email);
            contacts.set(index, editContact);
            ioFile.writeFile(contacts,PATH_NAME);
            System.out.println("Sửa " + phoneNumber + " thành công !");
            System.out.println("--------------------");
        } else {
            System.out.println("Không tìm được số điện thoại trong danh bạ !");
            System.out.println("--------------------");
        }
    }

    public void searchContactByNameOrPhone(String keyword) {
        ArrayList<Contact> newContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (validate.validatePhoneOrName(keyword, contact.getPhoneNumber()) || validate.validatePhoneOrName(keyword, contact.getName())) {
                newContacts.add(contact);
            }
        }
        if (newContacts.isEmpty()) {
            System.out.println("Không tìm thấy danh bạ với từ khóa trên !");
            System.out.println("--------------------");
        } else {
            System.out.println("Danh bạ cần tìm:");
            newContacts.forEach(System.out::println);
            System.out.println("--------------------");
        }
    }
}
