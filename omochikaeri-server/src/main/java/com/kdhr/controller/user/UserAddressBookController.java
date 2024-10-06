package com.kdhr.controller.user;

import com.kdhr.constant.MessageConstant;
import com.kdhr.context.BaseContext;
import com.kdhr.entity.AddressBook;
import com.kdhr.result.Result;
import com.kdhr.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址管理
 */
@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "用戶地址相關介面")
@Slf4j
public class UserAddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查詢目前登入使用者的所有地址
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查詢目前登入使用者的所有地址")
    public Result<List<AddressBook>> list() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());
        List<AddressBook> list = addressBookService.list(addressBook);
        return Result.success(list);
    }

    /**
     * 新增地址
     *
     * @param addressBook
     * @return
     */
    @PostMapping
    @ApiOperation("新增地址")
    public Result save(@RequestBody AddressBook addressBook) {
        log.info("新增地址 {}", addressBook);
        addressBookService.save(addressBook);
        return Result.success();
    }

    /**
     * 根據id查詢地址
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根據id查詢地址")
    public Result<AddressBook> getById(@PathVariable Long id) {
        log.info("根據id查詢地址 {}", id);
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * 根據id修改地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping
    @ApiOperation("根據id修改地址")
    public Result update(@RequestBody AddressBook addressBook) {
        log.info("根據id修改地址 {}", addressBook);
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 設定預設地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    @ApiOperation("設定預設地址")
    public Result setDefault(@RequestBody AddressBook addressBook) {
        log.info("設定預設地址 {}", addressBook);
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

    /**
     * 根據id刪除地址
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("根據id刪除地址")
    public Result deleteById(Long id) {
        addressBookService.deleteById(id);
        return Result.success();
    }

    /**
     * 查詢預設位址
     *
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("查詢預設位址")
    public Result<AddressBook> getDefault() {
        //SQL:select * from address_book where user_id = ? and is_default = 1
        AddressBook addressBook = new AddressBook();
        addressBook.setIsDefault(1);
        addressBook.setUserId(BaseContext.getCurrentId());
        List<AddressBook> list = addressBookService.list(addressBook);

        if (list != null && list.size() == 1) {
            return Result.success(list.get(0));
        }

        return Result.error(MessageConstant.CANNOT_FIND_DEFAULT_ADDRESS);
    }
}
