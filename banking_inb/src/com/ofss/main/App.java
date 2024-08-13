package com.ofss.main;

import com.ofss.main.service.CustomersDetailService;
import com.ofss.main.service.LoginDetailService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/i_nb";

    static final String USER = "root";
    static final String PASS = "root";

    static Connection conn = null;
    static Scanner scanner = new Scanner(System.in);
    static CustomersDetailService customersDetailService;
    static LoginDetailService loginDetailService;

    public static void main(String[] args) {
        try {
            // Step 1: Establishing a Connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            customersDetailService = new CustomersDetailService(conn);
            loginDetailService = new LoginDetailService(conn);

            int choice;
            do {
                System.out.println("Menu");
                System.out.println("1. New Registration");
                System.out.println("2. Existing Customer Login");
                System.out.println("3. Admin Login");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        registerNewCustomer();
                        break;
                    case 2:
                        customerLogin();
                        break;
                    case 3:
                        adminLogin();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter again.");
                }
            } while (choice != 1 && choice != 2 && choice != 3);

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            scanner.close();
        }
    }

    private static void registerNewCustomer() {
        System.out.println("\n--- New Customer Registration ---");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        boolean success = customersDetailService.registerNewCustomer(firstName, lastName, dob, address, email, phoneNumber);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static void customerLogin() {
        System.out.println("\n--- Existing Customer Login ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginDetailService.isAccountLocked(username)) {
            System.out.println("Account is locked. Please contact admin for assistance.");
            return;
        }

        boolean loggedIn = loginDetailService.validateLogin(username, password);
        if (loggedIn) {
            System.out.println("Login successful!");
            // Implement further actions after successful login
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void adminLogin() {
        System.out.println("\n--- Admin Login ---");

        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        // Example: Check if admin credentials are valid (replace with your actual logic)
        if (!adminUsername.equals("admin") || !adminPassword.equals("admin")) {
            System.out.println("Invalid admin credentials.");
            return;
        }

        adminMenu();
    }

    private static void adminMenu() {
        int adminChoice;
        do {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Reset Attempts for User");
            System.out.println("2. Lock Account");
            System.out.println("3. Unlock Account");
            System.out.println("4. Exit Admin Menu");
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (adminChoice) {
                case 1:
                    resetAttemptsForUser();
                    break;
                case 2:
                    lockAccount();
                    break;
                case 3:
                    unlockAccount();
                    break;
                case 4:
                    System.out.println("Exiting Admin Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (adminChoice != 4);
    }

    private static void resetAttemptsForUser() {
        System.out.print("Enter username to reset attempts: ");
        String username = scanner.nextLine();
        loginDetailService.resetAttempts(username);
    }

    private static void lockAccount() {
        System.out.print("Enter username to lock account: ");
        String username = scanner.nextLine();
        loginDetailService.lockAccount(username);
    }

    private static void unlockAccount() {
        System.out.print("Enter username to unlock account: ");
        String username = scanner.nextLine();
        loginDetailService.unlockAccount(username);
    }
}