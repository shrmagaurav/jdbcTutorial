package com.example.jdbc.demo.project.controller;

import com.example.jdbc.demo.project.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.jdbc.demo.project.ExecutionHandler.insertEmployee;


@RestController
@RequestMapping("/EmployeeController")
public class EmployeeController {


    @GetMapping("/allEmployeeData")
    public ResponseEntity<Map<Integer, Employee>> retrieveAllEmployee() {   //@RequestParam String argument) {
        Map<Integer, Employee> data = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.retrieveAllEmployee();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/singleEmpData")
    public ResponseEntity<Map<Integer, Employee>> retrieveSingleEmployee(@RequestParam String name) {
        Map<Integer, Employee> data = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.retrieveSingleEmployee(name);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/removeEmp")
    public ResponseEntity<Boolean> removeEmployee(@RequestParam int id) {
        Boolean data = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.removeEmployee(id);
        return ResponseEntity.ok(data);
    }

    @PutMapping("{id}/")
    public ResponseEntity<Boolean> updateEmployee(@PathVariable int id, @RequestParam String name) {
        Boolean data = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.updateEmployee(name, id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/insertEmployee")
    public Employee Employee(@RequestBody Employee request) {

//        if(alreadyExist)


//        Map<String, Employee> employeeMap = new HashMap<>();
        Employee data = new Employee(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getHireDate(), request.getSalary(), request.getDepartment());
        insertEmployee("name", data);
//        employeeMap.put("name", new Employee(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getHireDate(), request.getSalary(), request.getDepartment()));
//        System.out.println(employeeMap);
//        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
//            insertEmployee(entry.getKey(), entry.getValue());
//        }


//            com.example.jdbc.demo.project.databaseImpl.EmployeeDao.insertEmployee();
//        Employee data = com.example.jdbc.demo.project.databaseImpl.EmployeeDao.insertEmployee();
        return data;  //ResponseEntity.ok("Data inserted successfully" + data) ;
//        return ResponseEntity.ok("Successfull");

    }
}
