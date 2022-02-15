package com.example.demoprojectforspringsecurity.Employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private static final List<Employee> employeeList = Arrays.asList(
            new Employee(1, "James Bond"),
            new Employee(2, "Sachin Mewar"),
            new Employee(3, "Anna Smith"),
            new Employee(4, "Honey")
    );


    @GetMapping(path = "{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") long employeeId){
        return employeeList.stream()
                .filter(employee -> employeeId == employee.getEmployeeId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                       " employee " + employeeId + "does not exist"
                ));
    }
}
