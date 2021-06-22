package com.zhizhao.jwgl.jiaowuguanli.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MyUserDetailsServiceMapper {
    @Select("SELECT a.url\n" +
            "FROM xi_tong_api a\n" +
            "LEFT JOIN jue_se_xi_tong_api_zu b ON a.id = b.xi_tong_api_id AND a.is_deleted = 0\n" +
            "LEFT JOIN zhang_hao_jue_se_zu c ON c.jue_se_id = b.jue_se_id\n" +
            "LEFT JOIN zhang_hao d on c.zhang_hao_id = d.id\n" +
            "WHERE d.id = #{zhangHaoId}")
    List<String> findApiByZhangHuId(@Param("zhangHaoId") Long zhangHaoId);
}
