package com.kdhr.mapper;

import com.github.pagehelper.Page;
import com.kdhr.dto.OrdersPageQueryDTO;
import com.kdhr.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {
    void insert(Orders orders);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);
    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);

    void update(Orders orders);
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer toBeConfirmed);
}
