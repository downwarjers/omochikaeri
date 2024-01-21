package com.kdhr.service;

import com.kdhr.dto.EmployeeLoginDTO;
import com.kdhr.entity.Employee;

public interface EmployeeService {

    /**
     * 員工登入
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}