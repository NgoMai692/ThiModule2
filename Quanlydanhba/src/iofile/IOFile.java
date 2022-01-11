package iofile;

import model.Contact;

import java.io.*;
import java.util.ArrayList;

public class IOFile {

    public void writeFile(ArrayList<Contact> phoneBookList, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (Contact contact : phoneBookList) {
                bufferedWriter.write(contact.getPhoneNumber() + "," + contact.getGroup() + "," + contact.getName() + "," + contact.getGender() + ","
                        + contact.getAddress() + "," + contact.getBirthday() + "," + contact.getEmail() + "\n");
            }
            bufferedWriter.close();
            System.out.println(" Write file successfully !");
            System.out.println("--------------------");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public ArrayList<Contact> readFile(String path) {
        ArrayList<Contact> phoneBooks = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                phoneBooks.add(new Contact(strings[0], strings[1], strings[2], strings[3], strings[4],strings[5], strings[6]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return phoneBooks;
    }
}
