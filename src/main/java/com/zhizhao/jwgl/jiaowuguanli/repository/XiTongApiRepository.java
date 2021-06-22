package com.zhizhao.jwgl.jiaowuguanli.repository;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongapi.XiTongApi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface XiTongApiRepository extends CrudRepository<XiTongApi, Long> {
    @Query("SELECT DISTINCT a.url FROM XiTongApi a WHERE a.isDeleted = false ")
    Set<String> getAllApiUrl();
}
