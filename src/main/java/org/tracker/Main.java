package org.tracker;


import org.tracker.dao.ExpenseDAO;
import org.tracker.model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpenseDAO expenseDAO = new ExpenseDAO();

        ExpenseDAO.createTableExpense();

        while (true) {

            System.out.println("\n***** Personal Expense Tracker *****");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Delete Expense by ID");
            System.out.println("4. Update Expense by ID");
            System.out.println("5. Total Expense ");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();

                    Expense newExp = new Expense();
                    newExp.setDate(date);
                    newExp.setAmount(amount);
                    newExp.setCategory(category);
                    newExp.setDescription(description);

                    boolean added = expenseDAO.addExpense(newExp);
                    System.out.println(added ? "\nExpense added!" : " \nFailed to add expense.");
                    break;

                case 2:
                    List<Expense> expenses = expenseDAO.getAllExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {
                        System.out.println("\nID | Date       | Amount   | Category     | Description");
                        System.out.println("---------------------------------------------------------");
                        for (Expense exp : expenses) {
                            System.out.printf("%-3d| %-10s | %-8.2f | %-12s | %s\n",
                                    exp.getId(),
                                    exp.getDate(),
                                    exp.getAmount(),
                                    exp.getCategory(),
                                    exp.getDescription());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Expense ID to delete: ");
                    int delId = sc.nextInt();
                    boolean deleted = expenseDAO.deleteExpense(delId);
                    System.out.println(deleted ? "\nDeleted successfully." : "\nID not found or delete failed.");
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int updId = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter New Date (YYYY-MM-DD): ");
                    LocalDate updDate = LocalDate.parse(sc.nextLine());

                    System.out.print("Enter New Amount: ");
                    double updAmount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter New Category: ");
                    String updCategory = sc.nextLine();

                    System.out.print("Enter New Description: ");
                    String upDescription = sc.nextLine();

                    Expense updExp = new Expense();
                    updExp.setId(updId);
                    updExp.setDate(updDate);
                    updExp.setAmount(updAmount);
                    updExp.setCategory(updCategory);
                    updExp.setDescription(upDescription);

                    boolean updated = expenseDAO.updateExpense(updExp);
                    System.out.println(updated ? "\nExpense updated." : "\nUpdate failed or ID not found.");
                    break;

                case 5:
                    double total = expenseDAO.getTotal();
                    System.out.printf("\n Total spent : ₹%.2f\n", total);
                    break;

                case 6:
                    System.out.println("\n Exiting Personal Expense Tracker.");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Try again.");

            }
        }
    }
}