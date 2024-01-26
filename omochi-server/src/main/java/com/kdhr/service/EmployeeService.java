package com.kdhr.service;

import com.kdhr.dto.EmployeeDTO;
import com.kdhr.dto.EmployeeLoginDTO;
import com.kdhr.dto.EmployeePageQueryDTO;
import com.kdhr.entity.Employee;
import com.kdhr.result.PageResult;

public interface EmployeeService {

    /**
     * 員工登入
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}