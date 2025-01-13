package org.dbi.dbi_kursarbeit;



public class GuavaTest {
    public static void main(String[] args) {

        GuavaHashGenerator generator = new GuavaHashGenerator();


        String input = "example";


        String hash = generator.generateHash256Hex(input);


        System.out.println("Input: " + input);
        System.out.println("Generated Hash: " + hash);
    }
}