package com.kdhr.admin;

import com.kdhr.constant.JwtClaimsConstant;
import com.kdhr.dto.EmployeeDTO;
import com.kdhr.dto.EmployeeLoginDTO;
import com.kdhr.entity.Employee;
import com.kdhr.properties.JwtProperties;
import com.kdhr.result.Result;
import com.kdhr.service.EmployeeService;
import com.kdhr.utils.JwtUtil;
import com.kdhr.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 員工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登入
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("員工登入")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("員工登入：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登入成功後，產生jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("員工登出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增員工
     * @param employeeDTO
     * @return
     */
    @PostMapping("")
    @ApiOperation("新增員工")
    public Result<?> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增員工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }
}