package com.module23.demo1.service;

import com.module23.demo1.configs.EmployeeConfig;
import com.module23.demo1.dto.EmployeeDTO;
import com.module23.demo1.entities.EmployeeEntity;
import com.module23.demo1.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    public final EmployeeRepository employeeRepository;
    public final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity=  employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
       List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class)).collect(Collectors.toList());

          }

    public EmployeeDTO saveEmployee(EmployeeDTO inputEmp) {
        EmployeeEntity employeeEntity=modelMapper.map(inputEmp,EmployeeEntity.class);
        EmployeeEntity employeeEntity1 = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity1,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmpById(EmployeeDTO employeeDTO, Long empId)
    {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setEmpid(empId);
        EmployeeEntity savedEmpEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmpEntity,EmployeeDTO.class);
    }
    public boolean isEmpExits(Long empId)
    {
        return employeeRepository.existsById(empId);
    }
    public boolean deleteEmpByid(Long empId)
    {
        boolean isExits=isEmpExits(empId);
        if(!isExits) return false;
        employeeRepository.deleteById(empId);
        return true;
    }

    public EmployeeDTO updatePartialEmp(Map<String, Object> updates, Long empId)
    {
        boolean empExits = isEmpExits(empId);
        if(!empExits) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        updates.forEach((field,value)->{
                    Field field1= ReflectionUtils.findField(EmployeeEntity.class,field);
                    field1.setAccessible(true);
                    ReflectionUtils.setField(field1,employeeEntity,value);
                }
                );
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }
}
