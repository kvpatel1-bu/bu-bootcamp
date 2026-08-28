import java.util.Scanner;

public class Greeting {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your input?");
        String input = scanner.nextLine();
        System.out.println("You have printed: ".concat(input));
        scanner.close();


    }
}