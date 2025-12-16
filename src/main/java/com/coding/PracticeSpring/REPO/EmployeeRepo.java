package com.coding.PracticeSpring.REPO;

import com.coding.PracticeSpring.ENTITY.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long> {

}
