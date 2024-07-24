package com.tap.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.model.Customer;

public class CustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/customerdb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Dattesh@1";

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customers" + " (firstName, lastName, street, address, city, state, email, phone) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "select id, firstName, lastName, street, address, city, state, email, phone from customers where id = ?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from customers";
    private static final String DELETE_CUSTOMERS_SQL = "delete from customers where id = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "update customers set firstName = ?, lastName = ?, street = ?, address = ?, city = ?, state = ?, email = ?, phone = ? where id = ?;";

    public CustomerDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCustomer(Customer customer) throws SQLException {
        System.out.println(INSERT_CUSTOMER_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getStreet());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getState());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getPhone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Customer selectCustomer(Long id) {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                customer = new Customer(id, firstName, lastName, street, address, city, state, email, phone);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    public List<Customer> selectAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                customers.add(new Customer(id, firstName, lastName, street, address, city, state, email, phone));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    public boolean deleteCustomer(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMERS_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getStreet());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getCity());
            statement.setString(6, customer.getState());
            statement.setString(7, customer.getEmail());
            statement.setString(8, customer.getPhone());
            statement.setLong(9, customer.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
