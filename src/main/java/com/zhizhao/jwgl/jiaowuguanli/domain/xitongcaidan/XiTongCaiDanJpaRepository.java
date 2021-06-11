package com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XiTongCaiDanJpaRepository extends JpaRepository<XiTongCaiDanView, String> {
}
