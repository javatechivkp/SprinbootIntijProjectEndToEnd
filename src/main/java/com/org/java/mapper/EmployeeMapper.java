package com.org.java.mapper;

import com.org.java.dto.EmployeeDto;
import com.org.java.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE= Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto mapToEmployeetoEmployeeDTO(Employee employee);
    Employee mapToEmployeeDtotoEmployee(EmployeeDto employeeDto);









}
