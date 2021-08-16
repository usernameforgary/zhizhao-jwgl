package com.zhizhao.jwgl.jiaowuguanli.repository;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiKeJiLuRepository extends JpaRepository<PaiKeJiLu, Long> {
    /**
     * 根据排课信息Id，获取所有排课记录（未删除，未排课，未点名，上课日期晚于给定日期）
     * @param banJiPaiKeXinXiId
     * @param paiKeJiLuZhuangTai
     * @param shangKeShiJian
     * @return
     */
    List<PaiKeJiLu> findAllByBanJiPaiKeXinXiIdInAndPaiKeJiLuZhuangTaiEqualsAndShangKeRiQiAfterAndIsDeletedFalse(List<Long> banJiPaiKeXinXiId, PaiKeJiLuZhuangTai paiKeJiLuZhuangTai, Long shangKeShiJian);

    @Query("select a from PaiKeJiLu a join fetch a.shangKeXueYuanZu b where a.id = :id and b.isDeleted = false")
    PaiKeJiLu getById(@Param("id") Long id);
}
