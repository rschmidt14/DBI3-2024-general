package org.dbi.dbi_kursarbeit;



import java.util.Scanner;

public class HashCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the Hash Calculator!");
        System.out.print("Enter a string to hash: ");
        String input = scanner.nextLine();

        // Choose hashing method
        System.out.println("Choose hashing method:");
        System.out.println("1. Guava (Google's library)");
        System.out.println("2. Apache Commons Codec");
        int choice = scanner.nextInt();

        HashGenerator hashGenerator;
        if (choice == 1) {
            hashGenerator = new GuavaHashGenerator();
        } else if (choice == 2) {
            hashGenerator = new CommonsHashGenerator();
        } else {
            System.out.println("Invalid choice! Exiting...");
            return;
        }

        // Calculate and display the hash
        String hash = hashGenerator.generateHash256Hex(input);
        System.out.println("Generated Hash: " + hash);
    }
}
