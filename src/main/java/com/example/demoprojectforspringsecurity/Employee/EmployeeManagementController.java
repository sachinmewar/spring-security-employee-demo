package com.example.demoprojectforspringsecurity.Employee;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/employee")
public class EmployeeManagementController {
    private static final List<Employee> employeeList = Arrays.asList(
            new Employee(1, "James Bond"),
            new Employee(2, "Sachin Mewar"),
            new Employee(3, "Anna Smith"),
            new Employee(4, "Honey")
    );

    @GetMapping
    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    @PostMapping
    public String newEmployeeRegistration(@RequestBody Employee employee){
        return "Added Successfully";
    }

    @DeleteMapping(path = "{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") long employeeId){
        return "Deleted Successfully";
    }

    @PutMapping(path = "{employeeId}")
    public String updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody Employee employee){
        return "Updated Successfully";
    }
}
