package org.tracker.dao;

import org.tracker.DBConnection;
import org.tracker.model.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    //TODO: Create Expense Table
    public static void createTableExpense() {
        String sql = "CREATE TABLE if not exists expenses (id SERIAL PRIMARY KEY, date Date, amount NUMERIC(10, 2), category VARCHAR(50), description TEXT)";
        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO: Add expense
    public boolean addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (date, amount, category, description) VALUES (?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(expense.getDate()));
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getCategory());
            stmt.setString(4, expense.getDescription());

            int rows = stmt.executeUpdate();
            return  rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO: View all expenses
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses ORDER BY id ASC";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setDate(resultSet.getDate("date").toLocalDate());
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setDescription(resultSet.getString("description"));
                expenses.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    //TODO: Delete expense by ID
    public boolean deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))  {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO:  Update an existing expense
    public boolean updateExpense(Expense expense) {
        String query = "UPDATE expenses SET date = ?, amount = ?, category = ?, description = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDate(1, Date.valueOf(expense.getDate()));
            ps.setDouble(2, expense.getAmount());
            ps.setString(3, expense.getCategory());
            ps.setString(4, expense.getDescription());
            ps.setInt(5, expense.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO: Get total expense
    public double getTotal() {
        String query = "SELECT SUM(amount) FROM expenses";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
