package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.domain.Menu;

import java.util.List;

/**
 * @author wxz
 * @date 14:20 2023/2/19
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询用户对应权限
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.String>
     * @author wxz
     * @date 14:22 2023/2/19
     */
    List<String> selectPermsByUserId(Long userId);
}
