package com.coding.PracticeSpring.SERVICE;

import com.coding.PracticeSpring.DTO.EmployeeDto;
import com.coding.PracticeSpring.ENTITY.EmployeeEntity;
import com.coding.PracticeSpring.REPO.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        EmployeeEntity employeeEntity =employeeRepo.findById(id).orElse(null);
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

    public boolean isExistByEmpId(Long id){
        return employeeRepo.existsById(id);
    }

    public boolean deleteEmp(Long id) {
        boolean exist = isExistByEmpId(id);
        if(!exist)
            return false;
        employeeRepo.deleteById(id);
            return true;
    }

    public EmployeeDto partialUpdateEmp(Map<String, Object> update, Long id) {
        boolean exist = isExistByEmpId(id);
        if(!exist)return null;
        EmployeeEntity employeeEntity= employeeRepo.findById(id).get();
        update.forEach((field, value)->{
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
                return modelMapper.map(employeeEntity,EmployeeDto.class);
    }
}
