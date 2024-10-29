

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);
    String userName;
    
    // Enter username and press Enter
    System.out.println("Wie heißt du?"); 
    userName = myObj.nextLine();   
       
    System.out.println("Servus "  + userName);     
    
    
  }
}