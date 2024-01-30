package com.kdhr.service;

import com.kdhr.dto.EmployeeDTO;
import com.kdhr.dto.EmployeeLoginDTO;
import com.kdhr.dto.EmployeePageQueryDTO;
import com.kdhr.entity.Employee;
import com.kdhr.result.PageResult;

public interface EmployeeService {

    /**
     * 員工登入
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增員工
     *
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分頁查詢員工
     *
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 啟用或禁用員工
     *
     * @param status
     * @param id
     */
    void enable(Integer status, Long id);

    /**
     * 根據id查詢員工
     *
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 修改員工
     *
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}