package org.smir;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        if (args.length > 0 && args[0].equals("i")){
            Scanner scanner = new Scanner(System.in);
            System.out.println("What is you name? ");
            String name = scanner.nextLine();

            scanner.close();

            if(!name.isEmpty()){
                System.out.println("Hello, " + name + ":)");
            } else{
                System.out.println("Hello World!");
            }
            
        } else {
            System.out.println("Hello World");;
        }
    }
}
