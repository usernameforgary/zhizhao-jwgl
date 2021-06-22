package com.zhizhao.jwgl.jiaowuguanli.repository;

import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ZhangHaoRepository extends CrudRepository<ZhangHao, Long> {
    @Query("SELECT u FROM ZhangHao u WHERE u.shouJi = :shouJi")
    ZhangHao getUserByUsername(@Param("shouJi") String username);

    Optional<ZhangHao> findByShouJi(String shouJi);
}
