package main;

import iofile.IOFile;
import manager.ContactsManager;
import model.Contact;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    Scanner scanner = new Scanner(System.in);
    ContactsManager contactManager = new ContactsManager();
    IOFile ioFile = new IOFile();
    private final String PATH_NAME = "D:\\4. Outlook sync\\OneDrive\\Desktop\\ThiModule2\\Quanlydanhba\\src\\data\\contacts.csv";

    public Run() {
    }

    public void menu() {
        try {
            do {
                System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
                System.out.println("Chọn chức năng theo số (để tiếp tục):");
                System.out.println("1. Xem danh sách");
                System.out.println("2. Thêm mới");
                System.out.println("3. Cập nhật");
                System.out.println("4. Xóa");
                System.out.println("5. Tìm kiếm");
                System.out.println("6. Đọc từ file");
                System.out.println("7. Ghi vào file");
                System.out.println("8. Thoát");
                System.out.println("Chọn chức năng:");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 8) {
                    System.out.println();
                    System.out.println("Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                }
                switch (choice) {
                    case 1:
                        contactManager.displayAllContact();
                        break;
                    case 2:
                        contactManager.addNewContact();
                        break;
                    case 3:
                        System.out.println("▹ Nhập số điện thoại cần sửa:");
                        String phoneEdit = scanner.nextLine();
                        if (phoneEdit.equals("")) {
                            menu();
                        } else {
                            contactManager.updateContact(phoneEdit);
                        }
                        break;
                    case 4:
                        System.out.println("▹ Nhập số điện thoại cần xóa:");
                        String phoneDelete = scanner.nextLine();
                        if (phoneDelete.equals("")) {
                            menu();
                        } else {
                            contactManager.deleteContact(phoneDelete);
                        }
                        break;
                    case 5:
                        System.out.println("▹ Nhập từ khóa:");
                        String keyword = scanner.nextLine();
                        contactManager.searchContactByNameOrPhone(keyword);
                        break;
                    case 6:
                        ArrayList<Contact> contactArrayList = ioFile.readFile(PATH_NAME);
                        contactArrayList.forEach(System.out::println);
                        System.out.println("⛔ Read file successfully !");
                        System.out.println("--------------------");
                        break;
                    case 7:
                        ioFile.writeFile(contactManager.getContacts(),PATH_NAME);
                        break;
                    case 8:
                        System.exit(8);
                }
            } while (true);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menu();
        }
    }
}
