package com.coding.PracticeSpring.SERVICE;

import com.coding.PracticeSpring.DTO.EmployeeDto;
import com.coding.PracticeSpring.ENTITY.EmployeeEntity;
import com.coding.PracticeSpring.Advices.ResouceNotFoundException;
import com.coding.PracticeSpring.REPO.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    private final EmployeeRepo employeeRepo;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto getEmpById(Long id){
        EmployeeEntity employeeEntity =employeeRepo.findById(id).orElseThrow(()->
                new ResouceNotFoundException("Employee Not found with id: "+id)
        );
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmps() {
        List<EmployeeEntity> list = employeeRepo.findAll();
        return list
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto addEmp(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeRepo.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    public EmployeeDto updateEmp(EmployeeDto employeeDto, Long id) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity SavedEmployeeENtity = employeeRepo.save(employeeEntity);
        return modelMapper.map(SavedEmployeeENtity,EmployeeDto.class);

    }

    public void isExistByEmpId(Long id){
        boolean exist = employeeRepo.existsById(id);
        if(!exist) throw new ResouceNotFoundException("Employee Not found with id: "+id);
    }

    public boolean deleteEmp(Long id) {
        isExistByEmpId(id);
        employeeRepo.deleteById(id);
            return true;
    }

    public EmployeeDto partialUpdateEmp(Map<String, Object> update, Long id) {
         isExistByEmpId(id);
        EmployeeEntity employeeEntity= employeeRepo.findById(id).get();
        update.forEach((field, value)->{
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        EmployeeEntity saved = employeeRepo.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }
}
