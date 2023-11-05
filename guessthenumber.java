import java.util.Random;
import java.util.Scanner;

public class guessthenumber{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        Random random=new Random();
        int minRange=1;
        int maxRange=100;
        int totalScore=0;
        int round=0;

        System.out.println("Welcome to Guess the Number!");

        while(true){
            int targetNumber=random.nextInt(maxRange-minRange+1)+minRange;
            int attempts=0;
            int userGuess;

            System.out.println("Round "+(++round)+":");
            System.out.println("I'm thinking of a number between "+minRange+" and "+maxRange);

            while(true){
                System.out.print("Enter your guess: ");
                userGuess=scanner.nextInt();
                attempts++;

                if(userGuess==targetNumber){
                    System.out.println("Congratulations! You guessed the number in "+attempts+" attempts.");
                    int roundScore=maxRange-attempts+1;
                    totalScore+=roundScore;
                    System.out.println("Round Score: " +roundScore);
                    break;
                }else if(userGuess<targetNumber){
                    System.out.println("Try a higher number.");
                }else{
                    System.out.println("Try a lower number.");
                }

                if(attempts>=10){
                    System.out.println("Out of attempts! The correct number was "+targetNumber);
                    break;
                }
            }

            System.out.println("Total Score: "+totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain=scanner.next();
            if(!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thanks for playing!");
                break;
            }
        }
    }
}
