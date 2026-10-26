import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

List<File> files = new ArrayList<>();
Scanner scanner = new Scanner(System.in);
int introChoice;

void main() {

    createFiles();
    user();
}

void intro(){
    System.out.print("\nChoose an action:\n");
    System.out.print("1. Write\n");
    System.out.print("2. Delete\n");
    System.out.print("3. Read\n");
    System.out.print("Choice: ");

    introChoice = scanner.nextInt();
    scanner.nextLine();

    if(introChoice == 1){
        System.out.println("\nChoose a file: ");
        for (int i = 1; i < 11; i++){
            System.out.println("File " + i + ".");
        }
        System.out.print("Choice: ");

    } else if (introChoice == 2) {
        System.out.println("\nChoose a file to delete: ");
        for (int i = 1; i < 11; i++){
            System.out.println("File " + i + ".");
        }
        System.out.print("Choice: ");

    } else if (introChoice == 3) {
        System.out.println("\nChoose a file to read: ");
        for (int i = 1; i < 11; i++){
            System.out.println("File " + i + ".");
        }
        System.out.print("Choice: ");

    } else {
        System.out.print("Invalid choice\n");
        System.exit(0);
    }
}

void createFiles(){
    System.out.println("Creating files...");
    for (int i = 1; i <= 10; i++){
        File file = new File("file" + i + ".txt");

        try{
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists!");
            }

            files.add(file);

        } catch (IOException e) {
            System.out.print("An error occurred.\n");
        }
    }
    System.out.print("Files in list: " + files.size() + "\n");
}

void writeInFile(int index) {
    String text = scanner.nextLine();

    try (FileWriter writer = new FileWriter(files.get(index), true)) {
        writer.write(text + "\n");
        System.out.println("You added: <" + text + "> to "
                + files.get(index).getName());
    } catch (IOException e) {
        System.out.println("Invalid");
    }
}

void deleteFile(int index) {
    if (files.get(index).delete()) {
        System.out.println("Deleted: " + files.get(index).getName());
    } else {
        System.out.println("Could not delete file.");
    }
}

void readFile(int index) {

    try {
        Scanner fileReader = new Scanner(files.get(index));

        System.out.println("\nContents of "
                + files.get(index).getName() + ":");

        while (fileReader.hasNextLine()) {
            System.out.println(fileReader.nextLine());
        }

        fileReader.close();

    } catch (IOException e) {
        System.out.println("Could not read file.");
    }
}

public void user(){
    while (true) {
        intro();

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (introChoice == 1) {
            if (choice >= 1 && choice <= files.size()) {
                System.out.print("Write something to file " + choice + ": ");
                writeInFile(choice - 1);
            }
        }
        else if (introChoice == 2) {
            if (choice >= 1 && choice <= files.size()) {
                deleteFile(choice - 1);
            }
        }
        else if (introChoice == 3) {
            if (choice >= 1 && choice <= files.size()) {
                readFile(choice - 1);
            }
        }
        else{
            System.out.println("Invalid type..");
        }
    }
}
