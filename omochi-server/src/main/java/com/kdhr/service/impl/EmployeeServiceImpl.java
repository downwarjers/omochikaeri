package com.kdhr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kdhr.constant.MessageConstant;
import com.kdhr.constant.PasswordConstant;
import com.kdhr.constant.StatusConstant;
import com.kdhr.context.BaseContext;
import com.kdhr.dto.EmployeeDTO;
import com.kdhr.dto.EmployeeLoginDTO;
import com.kdhr.dto.EmployeePageQueryDTO;
import com.kdhr.entity.Employee;
import com.kdhr.exception.AccountLockedException;
import com.kdhr.exception.AccountNotFoundException;
import com.kdhr.exception.PasswordErrorException;
import com.kdhr.mapper.EmployeeMapper;
import com.kdhr.result.PageResult;
import com.kdhr.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 員工登入
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根據使用者名稱查詢資料庫中的數據
        Employee employee = employeeMapper.getByUsername(username);

        //2、處理各種異常狀況（使用者名稱不存在、密碼不對、帳號被鎖定）
        if (employee == null) {
            //帳號不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密碼比對
        // TODO 後期需要進行md5加密，然後再進行比對
        if (!password.equals(employee.getPassword())) {
            //密碼錯誤
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //帳號被鎖定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、回傳實體對象
        return employee;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //複製相同屬性值
        BeanUtils.copyProperties(employeeDTO, employee);
        //設定預設密碼
        employee.setPassword(PasswordConstant.DEFAULT_PASSWORD);
        //預設狀態為啟用
        employee.setStatus(StatusConstant.ENABLE);
        //日期時間
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //TODO 需取得當前登入員工ID
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

}