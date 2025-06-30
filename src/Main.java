import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int balance = 100;
        int bet;
        int payment;
        String[] rows;
        String playAgain;

        System.out.println("################################");
        System.out.println("Welcome to the Book of Ra Slot Machine!");
        System.out.println("Symbols: 📕 👳‍♂️ 🗿 🪙 🦅 ");
        System.out.println("################################");

        while (balance > 0){
            System.out.println("Current balance: " + balance + " €");
            System.out.print("Place your bet: ");
            bet = scanner.nextInt();
            scanner.nextLine();

            if (bet > balance){
                System.out.println("INSUFFICIENT BALANCE!");
                continue;
            }
            else if (bet < 0){
                System.out.println("The bet must be bigger than 0!");
                continue;
            }
            else{
                balance -= bet;
            }

            System.out.println("Spinning...");
            rows = spin();
            printLine(rows);
            payment = rewardsPayout(rows, bet);

            if (payment > 0){
                System.out.println("You have won " + payment + " €!");
                balance += payment;
            }
            else {
                System.out.println("Sadly you did not win anything this time. Better luck next time!");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().toLowerCase();

            if (!playAgain.equals("yes")){
                break;
            }
        }

        System.out.println("END OF THE GAME! Your balance is: " + balance + " €");
        System.out.println("Thanks for playing! See you again next time!");
    }
    static String[] spin(){

        String[] symbols = {"📕", "👳‍♂️", "🗿", "🪙", "🦅"};
        String[] line = new String[3];
        Random random = new Random();

        for (int i = 0; i < 3; i++){
            line[i] = symbols[random.nextInt(symbols.length)];
        }

        return line;
    }
    static void printLine(String[] line){
        System.out.println("################################");
        System.out.println(" " + String.join(" | ", line));
        System.out.println("################################");
    }
    static int rewardsPayout(String[] line, int bet){

        if (line[0].equals(line[1]) && line[1].equals(line[2])) {
            return switch (line[0]) {
                case "📕" -> bet * 4;
                case "👳" -> bet * 6;
                case "🗿" -> bet * 8;
                case "🪙" -> bet * 10;
                case "🦅" -> bet * 20;
                default -> 0;
            };
        }
        else if (line[0].equals(line[1])){
            return switch (line[0]) {
                case "📕" -> bet * 2;
                case "👳" -> bet * 3;
                case "🗿" -> bet * 4;
                case "🪙" -> bet * 5;
                case "🦅" -> bet * 10;
                default -> 0;
            };
        }
        else if (line[1].equals(line[2])) {
            return switch (line[1]) {
                case "📕" -> bet * 2;
                case "👳" -> bet * 3;
                case "🗿" -> bet * 4;
                case "🪙" -> bet * 5;
                case "🦅" -> bet * 10;
                default -> 0;
            };
        }
        return 0;
    }
}