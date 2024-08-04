package com.example.GenerateJsonWebToken.RepositoryPackage;

import com.example.GenerateJsonWebToken.Details.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Employees,Integer> {

   Optional<Employees> findByName(String name);
}
