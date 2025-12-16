package com.coding.PracticeSpring.Controller;

import com.coding.PracticeSpring.DTO.EmployeeDto;
import com.coding.PracticeSpring.ENTITY.EmployeeEntity;
import com.coding.PracticeSpring.REPO.EmployeeRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/{id}")
    public EmployeeEntity getEmpById(@PathVariable Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @GetMapping
        public String getEmp(@RequestParam(required = false) String name,
                             @RequestParam Integer age) {
        return "hi my name is "+name +" and age is "+age;
        }

}
