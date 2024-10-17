import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        // Überprüfe, ob das Programm mit dem Parameter "i" gestartet wurde
        if (args.length > 0 && args[0].equals("i")) {
            Scanner scanner = new Scanner(System.in);
            
            // Frage den Benutzer nach seinem Namen
            System.out.print("What's your name? ");
            String name = scanner.nextLine();
            
            // Begrüße den Benutzer
            System.out.println("Hello, " + name + "!");
            
            // Scanner schließen
            scanner.close();
        } else {
            // Standardausgabe, wenn kein Parameter übergeben wurde
            System.out.println("Hello World!");
        }
    }
}