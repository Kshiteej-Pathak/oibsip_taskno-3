import java.util.Scanner;

public class ATM_Interface {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount("Kshiteej", 2567);
        ba.checkId();
    }

}

class BankAccount {
    int balance;
    int prev_Trans;
    String cName;
    int cID;
    int flag = 0;
    int end;

    BankAccount(String cName, int cID) {
        this.cName = cName;
        this.cID = cID;
    }

    Scanner sc = new Scanner(System.in);

    public final void clrscr() {
        try {
            try {
                final String os = System.getProperty("os.name");

                if (os.contains("Windows")) {
                    Runtime.getRuntime().exec("cls");
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (final Exception e) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (final Exception es) {
            // System.out.println("nothing here!");
        }

    }

    void checkId() {
        clrscr();
        System.out.println("Welcome to our Services Mr. " + cName);
        System.out.println();
        System.out.print("Please enter the Customer ID or PIN: ");
        int chk = sc.nextInt();
        if (chk==cID) {
            clrscr();
            showMenu();
        } else {
            System.out.println("*************************************");
            System.out.println("Wrong Login!! Please try again");
            System.out.println("*************************************");

            if (flag < 3) {
                flag++;
                checkId();
            }
        }
    }

    void balance(){
        System.out.println("*************************************");
        System.out.println("Balance :" + balance);
        System.out.println("*************************************");
        System.out.println("Press 0 for exit");
        end=sc.nextInt();
    }

    void deposit(int amount) {
        if (amount != 0) {
            balance = balance + amount;
            prev_Trans = amount;
        }
    }

    void withdraw(int amount) {
        if (this.balance > amount) {
            balance = balance - amount;
            prev_Trans = -amount;
        } else {
            clrscr();
            System.out.println("*************************************");
            System.out.println("Sufficient Balance not available for Withdrawal!");
            System.out.println("*************************************");
        }
    }

    void getPrevTransaction() {
        if (prev_Trans > 0) {
            System.out.println("Deposited: " + prev_Trans);
        } else if (prev_Trans < 0) {
            System.out.println("Withdraw: " + Math.abs(prev_Trans));
        } else {
            System.out.println("No Transaction has occured ");
        }
        System.out.println("Press 0 for exit");
        end=sc.nextInt();
    }

    public void transfer(double amount, BankAccount acc) {
        if (this.balance < amount) {
            clrscr();
            System.out.println("*************************************");
            System.out.println("Transfer Failed due to Insufficient Balance!");
            System.out.println("*************************************");
        } else {
            this.balance -= amount;
            acc.balance += amount;
            System.out.println("Account of " + this.cName + " becomes $" + this.balance);
            System.out.println("Account of " + acc.cName + " becomes $" + acc.balance);
            System.out.println("\n");
        }
    }

    private void showMenu() {
        char option;
        System.out.println("Welcome " + cName);
        System.out.println("Your ID is : " + cID);
        do {
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Previous Transaction");
            System.out.println("5. Transfer");
            System.out.println("6. Quit");

            System.out.println("*************************************");
            System.out.println("Enter the option :");
            System.out.println("*************************************");
            option = sc.next().charAt(0);
            option = Character.toUpperCase(option);
            System.out.println("\n");

            switch (option) {
                case '1':
                    clrscr();
                    balance();
                    break;

                case '2':
                    clrscr();
                    System.out.println("*************************************");
                    System.out.println("Enter the amount to deposit :");
                    System.out.println("*************************************");
                    int amount = sc.nextInt();
                    deposit(amount);
                    System.out.println("\n");
                    break;

                case '3':
                    clrscr();
                    System.out.println("*************************************");
                    System.out.println("Enter the amount to withdraw");
                    System.out.println("*************************************");
                    int amount2 = sc.nextInt();
                    withdraw(amount2);
                    System.out.println("\n");
                    break;

                case '4':
                    clrscr();
                    System.out.println("*************************************");
                    getPrevTransaction();
                    System.out.println("*************************************");
                    System.out.println("\n");
                    break;

                case '5':
                    clrscr();
                    System.out.println("***************");
                    System.out.println("To whom");
                    BankAccount bb = new BankAccount("Atharv", 6000);
                    System.out.println(bb.cName);
                    System.out.println("***************");
                    System.out.println("Amount to Transfer");
                    double am = sc.nextDouble();
                    System.out.println("***************");
                    transfer(am, bb);
                    break;

                case '6':
                    clrscr();
                    System.out.println("***************");
                    System.out.println("ThankYou Mr. "+cName +" For Using Our Services");
                    System.exit(0);

                default:
                    clrscr();
                    System.out.println("Invalid Option! Please Enter Again");
            }

        } while (true);

    }
}
