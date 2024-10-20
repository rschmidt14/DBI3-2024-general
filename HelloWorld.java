// Importiert die Scanner-Klasse, die zur Eingabe von der Konsole verwendet wird.
import java.util.Scanner; 

//Die Klasse HelloWorld wird definiert 
class HelloWorld {
    //Die Methode main wird ausgeführt
    public static void main(String[] args) {
        // Überprüft, ob ein Argument übergeben wurde und ob das erste Argument "i" ist.
        if (args.length > 0 && args[0].equals("i")) {
            // Erstellt einen Scanner, um Benutzereingaben von der Konsole zu lesen.
            Scanner scanner = new Scanner(System.in);
            
            // Fragt den Benutzer nach seinem Namen.
            System.out.println("What's your name?");
            
            // Liest die Benutzereingabe (den Namen) und speichert sie in der Variable "name".
            String name = scanner.nextLine();
            
            // Gibt eine personalisierte Begrüßung mit dem Namen des Benutzers aus.
            System.out.println("Hello " + name + "!");
            
            // Schließt den Scanner, um Ressourcen freizugeben.
            scanner.close();
        } else {
            // Wenn kein Argument oder nicht "i" übergeben wurde, wird einfach "Hello World!" ausgegeben.
            System.out.println("Hello World!");
        }
    }
}
