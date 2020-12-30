package org.adekeagle;

import org.adekeagle.repository.InMemoryClientRepository;
import org.adekeagle.service.BankService;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private BankService bankService;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run(){
        final InMemoryClientRepository repository = new InMemoryClientRepository(new HashSet<>());
        bankService = new BankService(repository);

        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {

                System.out.println("1 - add user");
                System.out.println("2 - find user");
                System.out.println("3 - transfer from account to another");
                System.out.println("4 - exit app");

                final String next = scanner.next();

                if(next.equals("1")) {
                    addUser(scanner);
                }
                if(next.equals("2")) {
                    printUser(scanner);
                }
                if(next.equals("3")) {
                    transferMoney(scanner);
                }
                if(next.equals("4")) {
                    break;
                }
            }
        }
    }

    private void transferMoney(Scanner scanner) {
        System.out.println("Enter from email");
        final String fromEmail = scanner.next();
        System.out.println("Enter to email");
        final String toEmail = scanner.next();
        System.out.println("Enter amount");
        final double amount = scanner.nextDouble();
        bankService.transfer(fromEmail, toEmail, amount);
    }

    private void printUser(Scanner scanner) {
        System.out.println("Enter email:");
        final String mail = scanner.next();
        System.out.println(bankService.findByEmail(mail));
    }

    private void addUser(Scanner scanner) {
        System.out.println("Enter name:");
        final String name = scanner.next();
        System.out.println("Enter email:");
        final String mail = scanner.next();
        System.out.println("Enter balance:");
        final double balance = scanner.nextDouble();
        bankService.save(new Client(name, mail, balance));
    }
}
