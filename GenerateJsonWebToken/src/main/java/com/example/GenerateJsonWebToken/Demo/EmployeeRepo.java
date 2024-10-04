package com.example.GenerateJsonWebToken.Demo;

import com.example.GenerateJsonWebToken.Details.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employees,Integer> {

    Employees findByName(String name);
}
