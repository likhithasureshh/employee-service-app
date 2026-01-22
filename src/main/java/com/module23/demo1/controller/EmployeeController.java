package com.module23.demo1.controller;

import com.module23.demo1.configs.EmployeeConfig;
import com.module23.demo1.dto.EmployeeDTO;
import com.module23.demo1.entities.EmployeeEntity;
import com.module23.demo1.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {
   public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/{empId}")
    public EmployeeDTO getEmpById(@PathVariable(name="empId") Long id)
    {
        return employeeService.getEmployeeById(id);

    }

    @GetMapping
    public List<EmployeeDTO> getAllEmp()
    {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO saveEmp(@RequestBody EmployeeDTO inputEmp)
    {
        return employeeService.saveEmployee(inputEmp);
    }



    @PutMapping(path="/{empId}")
    public EmployeeDTO updateEmpById(@RequestBody EmployeeDTO employeeDTO,
                                     @PathVariable Long empId)

    {
        return employeeService.updateEmpById(employeeDTO,empId);
    }

   @DeleteMapping(path="/{empId}")
    public boolean deleteEmpByid(@PathVariable Long empId)
   {
       return employeeService.deleteEmpByid(empId);
   }

   @PatchMapping(path="/{empId}")
    public EmployeeDTO updatePartialEmp(@RequestBody Map<String,Object> updates,
                                        @PathVariable Long empId)

   {
       return employeeService.updatePartialEmp(updates,empId);
   }

}
