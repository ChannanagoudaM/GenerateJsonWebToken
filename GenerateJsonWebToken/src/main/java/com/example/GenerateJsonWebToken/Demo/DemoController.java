package com.example.GenerateJsonWebToken.Demo;

import com.example.GenerateJsonWebToken.Details.Employees;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

    @Autowired
    EmployeeRepo employeeRepo;

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @GetMapping("/demo")
    public ResponseEntity<String> demo()
    {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employees>> getAll()
    {
        logger.info(employeeRepo.findAll().toString());
    return ResponseEntity.ok(employeeRepo.findAll());
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Employees> getByName(@PathVariable String name)
    {
        return ResponseEntity.ok(employeeRepo.findByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id)
    {
        employeeRepo.deleteById(id);
        return ResponseEntity.ok("Employee deleted");
    }


    @PutMapping("update/{id}")
    public  ResponseEntity<Employees> updateEmployeeName(@PathVariable Integer id,@RequestBody String name)
    {
        Employees employee = employeeRepo.findById(id)
                .orElseThrow();
        employee.setName(name);
        employeeRepo.save(employee);
        return ResponseEntity.ok(employee);
    }

}
