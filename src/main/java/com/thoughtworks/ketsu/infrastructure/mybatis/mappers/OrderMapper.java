package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrderMapper {
    void save(@Param("info") Map info, @Param("userId") long userId);

    Order findById(Long id);
}
