import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMSystem{
    private static String userId="12345";
    private static String userPin="6789";
    private static double accountBalance=1000.0;
    private static List<String> transactionHistory=new ArrayList<>();

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        System.out.println("Welcome to the ATM System!");
        System.out.print("Enter User ID: ");
        String enteredUserId=scanner.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPin=scanner.nextLine();

        if(authenticateUser(enteredUserId,enteredPin)){
            System.out.println("Authentication successful. Welcome!");

            int choice;
            do{
                System.out.println("\nChoose an option:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                choice=scanner.nextInt();

                switch(choice){
                    case 1:
                        showTransactionHistory();
                        break;
                    case 2:
                        withdrawFunds(scanner);
                        break;
                    case 3:
                        depositFunds(scanner);
                        break;
                    case 4:
                        transferFunds(scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }while(choice!=5);
        }else{
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private static boolean authenticateUser(String enteredUserId,String enteredPin){
        return userId.equals(enteredUserId)&&userPin.equals(enteredPin);
    }

    private static void showTransactionHistory(){
        if (transactionHistory.isEmpty()){
            System.out.println("No transaction history available.");
        } else{
            System.out.println("Transaction History:");
            for(String transaction:transactionHistory){
                System.out.println(transaction);
            }
        }
    }

    private static void withdrawFunds(Scanner scanner){
        System.out.print("Enter withdrawal amount: ");
        double withdrawalAmount=scanner.nextDouble();

        if(isValidAmount(withdrawalAmount)&&hasSufficientFunds(withdrawalAmount)){
            accountBalance-=withdrawalAmount;
            updateTransactionHistory("Withdrawal: -$"+withdrawalAmount);
            System.out.println("Withdrawal successful. Remaining balance: $"+accountBalance);
        } else{
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    private static void depositFunds(Scanner scanner){
        System.out.print("Enter deposit amount: ");
        double depositAmount=scanner.nextDouble();

        if(isValidAmount(depositAmount)){
            accountBalance+=depositAmount;
            updateTransactionHistory("Deposit: +$" + depositAmount);
            System.out.println("Deposit successful. New balance: $" + accountBalance);
        }else{
            System.out.println("Invalid deposit amount.");
        }
    }

    private static void transferFunds(Scanner scanner){
        System.out.print("Enter recipient's account number: ");
        String recipientAccount=scanner.next();

        System.out.print("Enter transfer amount: ");
        double transferAmount=scanner.nextDouble();

        if(isValidAmount(transferAmount)&&hasSufficientFunds(transferAmount)){
            accountBalance-=transferAmount;
            updateTransactionHistory("Transfer to "+recipientAccount+": -$"+transferAmount);
            System.out.println("Transfer successful. Remaining balance: $"+accountBalance);
        } else{
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }

    private static boolean isValidAmount(double amount){
        return amount>0;
    }

    private static boolean hasSufficientFunds(double amount){
        return amount<=accountBalance;
    }

    private static void updateTransactionHistory(String transaction){
        transactionHistory.add(transaction);
    }
}
