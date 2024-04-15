package com.example.jdbc.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcDemoProjectApplication {

//	public String getGreeting() {
//		return "Hello world.";
//	}

	public static void main(String[] args) {

		SpringApplication.run(JdbcDemoProjectApplication.class, args);
		System.out.println("Server Started");
//		System.out.println(new App().getGreeting());
		//todo -> Before running this project, please ensure that one of the methods is commented out.

//		ExecutionHandler.executeInsertion();

//		ExecutionHandler.retrieveAllEmployeeDetails();

//		ExecutionHandler.retrieveSingleEmployeeDetails("Gaurav");

//		ExecutionHandler.updateEmployeeDetails(1,"Darshika");

//		ExecutionHandler.removeEmployeeDetail(1);

	}

}
