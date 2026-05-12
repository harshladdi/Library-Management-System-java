```java
import java.io.*;
import java.util.Scanner;

class Book implements Serializable {
    int id;
    String name;
    boolean issued;

    Book(int id, String name) {
        this.id = id;
        this.name = name;
        this.issued = false;
    }
}

public class LibraryManagementSystem {

    static Book[] books = new Book[100];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadData();

        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    issueBook();
                    break;

                case 3:
                    returnBook();
                    break;

                case 4:
                    viewBooks();
                    break;

                case 5:
                    saveData();
                    System.out.println("Data saved. Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);
    }

    static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        books[count] = new Book(id, name);
        count++;

        System.out.println("Book added successfully");
    }

    static void issueBook() {

        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (books[i].id == id && !books[i].issued) {

                books[i].issued = true;

                System.out.println("Book issued successfully");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not available");
        }
    }

    static void returnBook() {

        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (books[i].id == id && books[i].issued) {

                books[i].issued = false;

                System.out.println("Book returned successfully");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid Book ID");
        }
    }

    static void viewBooks() {

        if (count == 0) {

            System.out.println("No books available");
            return;
        }

        System.out.println("\nID\tName\tIssued");

        for (int i = 0; i < count; i++) {

            System.out.println(
                    books[i].id + "\t" +
                    books[i].name + "\t" +
                    books[i].issued
            );
        }
    }

    static void saveData() {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream("library.dat"));

            out.writeInt(count);

            for (int i = 0; i < count; i++) {
                out.writeObject(books[i]);
            }

            out.close();

        } catch (Exception e) {

            System.out.println("Error saving data");
        }
    }

    static void loadData() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream("library.dat"));

            count = in.readInt();

            for (int i = 0; i < count; i++) {
                books[i] = (Book) in.readObject();
            }

            in.close();

        } catch (Exception e) {

            System.out.println("No previous data found");
        }
    }
}
```
