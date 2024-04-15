package com.example.jdbc.demo.project;

import com.example.jdbc.demo.project.databaseImpl.EmployeeDao;
import com.example.jdbc.demo.project.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class ExecutionHandler {
    private static EmployeeDao EmployeeDao;

    public ExecutionHandler() {
        this.EmployeeDao = new EmployeeDao();
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(ExecutionHandler.class);

    public static void executeInsertion() {

        Map<String, Employee> employeeMap = createEmployeeMap();

        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
            boolean inserted = insertEmployee(entry.getKey(), entry.getValue());
            if (inserted) {
                LOGGER.info("Employee '{}' inserted successfully.", entry.getKey());
            } else {
                LOGGER.error("Failed to insert employee '{}'", entry.getKey());
            }
        }
    }

    public static void retrieveAllEmployeeDetails() {
        var map = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.retrieveAllEmployee();
        LOGGER.info("Employee Details:");
        for (Map.Entry<Integer, Employee> entry : map.entrySet()) {
            LOGGER.info("Key: {}, Value: {}", entry.getKey(), entry.getValue());
        }
    }


    public static void retrieveSingleEmployeeDetails(String name) {
        var map = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.retrieveSingleEmployee(name);
        LOGGER.info("Single Employee Details:");
        for (Map.Entry<Integer, Employee> entry : map.entrySet()) {
            LOGGER.info("Information: {}", entry.getValue());
        }
//        boolean featched = EmployeeDao.retrieveSingleEmployee(name);
//        if (featched) {
//            LOGGER.info("Employee Details fetched Successfully");
//        } else {
//            LOGGER.info("No Details available in database");
//        }
    }

    public static void removeEmployeeDetail(int id) {
        boolean deleted = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.removeEmployee(id);
        if (deleted) {
            LOGGER.info("Employee Removed Successfully.");
        } else {
            LOGGER.info("Failed to remove Employee.");
        }
    }


    public static void updateEmployeeDetails(int id, String name) {
        boolean updated = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.updateEmployee(name, id);
        if (updated) {
            LOGGER.info("Updation Successfully");
        } else {
            LOGGER.info("Updation Failed");
        }
    }


    private static Map<String, Employee> createEmployeeMap() {
//        Map<String, Employee> employeeMap = new HashMap<>();
        Map<String, Employee> employeeMap = new HashMap<>();

        employeeMap.put("Gaurav", new Employee("Gaurav", "Sharma", "gaurav.sharma@sarvika.com",
                "123456789", Date.valueOf("2024-03-04"), 50000.00, "Software Engineer"));


        employeeMap.put("Darshika", new Employee("Darshika", "Maheshwari",
                "darshika.Maheshwari@sarvika.com", "0123456789", Date.valueOf("2024-03-04"),
                50000.00, "Software Engineer"));

        employeeMap.put("Gunjan", new Employee("Gunjan", "Kanwar", "gunjan.kanwar@sarvika.com",
                "0123456789", Date.valueOf("2024-03-11"), 50000.00, "Software Engineer"));
        System.out.println(employeeMap);

        for (Map.Entry<String, Employee> e : employeeMap.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
        return employeeMap;
    }

    public static boolean insertEmployee(String employeeName, Employee employee) {
        return com.example.jdbc.demo.project.databaseImpl.EmployeeDao.insertEmployee(employee);
    }

}
