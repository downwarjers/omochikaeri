package com.kdhr.service;

import com.kdhr.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    /**
     * 根據條件查詢地址
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 新增地址
     * @param addressBook
     */
    void save(AddressBook addressBook);

    /**
     * 根據id查詢地址
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 根據id修改地址
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 設定預設地址
     * @param addressBook
     */
    void setDefault(AddressBook addressBook);

    /**
     * 根據id刪除地址
     * @param id
     */
    void deleteById(Long id);
}
