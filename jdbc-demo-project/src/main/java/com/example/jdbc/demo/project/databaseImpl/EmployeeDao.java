package com.example.jdbc.demo.project.databaseImpl;

import com.example.jdbc.demo.project.connectivity.JdbcConnectivity;
import com.example.jdbc.demo.project.model.Employee;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDao {

    private static final String INSERT_QUERY = "INSERT INTO employee_details (FirstName, LastName, Email, PhoneNumber, HireDate, Salary, Department) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_EMPLOYEE_DETAILS = "SELECT * FROM employee_details";

    private static final String DELETE_QUERY = "DELETE FROM employee_details where EmployeeID=(?)";

    private static final String UPDATE_QUERY = "UPDATE employee_details SET FirstName=(?) WHERE EmployeeID=(?)";

    private static final String GET_SINGLE_EMPLOYEE_DETAILS = "SELECT * FROM employee_details WHERE FirstName=(?)";

    public static boolean insertEmployee(Employee Employee) {
        try (

                Connection connection = JdbcConnectivity.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        ) {
            preparedStatement.setString(1, Employee.getFirstName());
            preparedStatement.setString(2, Employee.getLastName());
            preparedStatement.setString(3, Employee.getEmail());
            preparedStatement.setString(4, Employee.getPhoneNumber());
            preparedStatement.setDate(5, Employee.getHireDate());
            preparedStatement.setDouble(6, Employee.getSalary());
            preparedStatement.setString(7, Employee.getDepartment());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeEmployee(int id) {
        try (
                Connection connection = JdbcConnectivity.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        ) {
            preparedStatement.setInt(1, id);

            int a = preparedStatement.executeUpdate();
            return a == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateEmployee(String name, int id) {
        try (
                Connection connection = JdbcConnectivity.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {

            preparedStatement.setString(1, name);

            preparedStatement.setInt(2, id);

            int a = preparedStatement.executeUpdate();
            return a == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<Integer, Employee> retrieveSingleEmployee(String Fname) {

        Map<Integer, Employee> employeeMap = new HashMap<>();

//        boolean foundResponse = false;
        try (
                Connection connection = JdbcConnectivity.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(GET_SINGLE_EMPLOYEE_DETAILS);
        ) {
            preparedStatement.setString(1, Fname);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve employee details from ResultSet
            while (resultSet.next()) {
//                foundResponse = true;
                int id = resultSet.getInt("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                Date hireDate = resultSet.getDate("HireDate");
                double salary = resultSet.getDouble("Salary");
                String department = resultSet.getString("Department");

                Employee employee = new Employee(firstName, lastName, email, phoneNumber, hireDate, salary, department);

                employeeMap.put(id, employee);


            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
//        return foundResponse;
        return employeeMap;
    }


    public static Map<Integer, Employee> retrieveAllEmployee() {

        Map<Integer, Employee> employeeMap = new HashMap<>();

        try (
                Connection connection = JdbcConnectivity.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_DETAILS);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                // Retrieve employee details from ResultSet
                int id = resultSet.getInt("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                Date hireDate = resultSet.getDate("HireDate");
                double salary = resultSet.getDouble("Salary");
                String department = resultSet.getString("Department");

                Employee employee = new Employee(firstName, lastName, email, phoneNumber, hireDate, salary, department);

                employeeMap.put(id, employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeMap;
    }
}
