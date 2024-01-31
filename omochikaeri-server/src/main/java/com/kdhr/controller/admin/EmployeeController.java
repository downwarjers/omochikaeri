package com.kdhr.controller.admin;

import com.kdhr.constant.JwtClaimsConstant;
import com.kdhr.dto.EmployeeDTO;
import com.kdhr.dto.EmployeeLoginDTO;
import com.kdhr.dto.EmployeePageQueryDTO;
import com.kdhr.entity.Employee;
import com.kdhr.properties.JwtProperties;
import com.kdhr.result.PageResult;
import com.kdhr.result.Result;
import com.kdhr.service.EmployeeService;
import com.kdhr.utils.JwtUtil;
import com.kdhr.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 員工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "員工相關介面")
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
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增員工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增員工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 分頁查詢員工
     *
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分頁查詢員工")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分頁查詢員工，參數為:{}" + employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 啟用或禁用員工
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("啟用或禁用員工")
    public Result enable(@PathVariable Integer status, Long id) {
        log.info("將員工id {} 狀態設為 {}", id, status);
        employeeService.enable(status, id);
        return Result.success();
    }

    /**
     * 根據id查詢員工
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根據id查詢員工")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("查詢id:{}的員工", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @PutMapping
    @ApiOperation("編輯員工資訊")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("編輯員工資料 {}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}