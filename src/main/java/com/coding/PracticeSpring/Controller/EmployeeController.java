package com.coding.PracticeSpring.Controller;

import com.coding.PracticeSpring.DTO.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @GetMapping("/{id}")
    public EmployeeDto getEmpById(@PathVariable Long id) {
        return new EmployeeDto(id, "Piyush","dixitpiyush23@gmil.com",26, LocalDate.of(2025,1,1),
                true);
    }

    @GetMapping
        public String getEmp(@RequestParam(required = false) String name,
                             @RequestParam Integer age) {
        return "hi my name is "+name +" and age is "+age;
        }
}
