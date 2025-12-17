package com.coding.PracticeSpring.Controller;

import com.coding.PracticeSpring.DTO.EmployeeDto;
import com.coding.PracticeSpring.ENTITY.EmployeeEntity;
import com.coding.PracticeSpring.REPO.EmployeeRepo;
import com.coding.PracticeSpring.SERVICE.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

//    private final EmployeeRepo employeeRepo;
//
//    public EmployeeController(EmployeeRepo employeeRepo) {
//        this.employeeRepo = employeeRepo;
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public EmployeeDto getEmpById(@PathVariable Long id) {
        return employeeService.getEmpById(id);
    }

    @GetMapping
        public String getEmp(@RequestParam(required = false) String name,
                             @RequestParam Integer age) {
        return "hi my name is "+name +" and age is "+age;
        }

    @GetMapping("/list")
    public List<EmployeeDto> getAllEmps(  ){
        return employeeService.getAllEmps();
    }

    @PostMapping("/add")
    public EmployeeDto addEmp(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addEmp(employeeDto);
    }

    @PutMapping("/empId")
    public EmployeeDto updateEmp(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        return employeeService.updateEmp(employeeDto,id);
    }

    @DeleteMapping("/empId")
    public Boolean deleteEmp(@PathVariable Long id) {
        return employeeService.deleteEmp(id);
    }

    @PatchMapping("/empId")
    public EmployeeDto partialUpdateEmp(@RequestBody Map<String, Object> update, @PathVariable Long id) {
        return employeeService.partialUpdateEmp(update,id);
    }
}
