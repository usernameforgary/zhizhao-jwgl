package com.zhizhao.jwgl.jiaowuguanli.utils;

import cn.hutool.core.date.Week;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeShangKeTian;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    /**
     * 转换Jpa的分页结果，组装成前端需要的格式
     * @param item Jpa的分页结果
     * @return 返回值
     */
    public static Map<String, Object> convertJpaPageResult (Page<?> item) {
        Map<String, Object> result = new HashMap<>();
        // JAP 分页是从0开始，前端是从1开始
        result.put("current", item.getNumber() + 1);
        result.put("pages", item.getTotalPages());
        result.put("records", item.getContent());
        result.put("size", item.getSize());
        result.put("total", item.getTotalElements());
        return result;
    }


    public static DtoPageResult convertPageResultMybatisPlus (IPage item) {
        DtoPageResult dtoPageResult = new DtoPageResult();
        // JAP 分页是从0开始，前端是从1开始
        dtoPageResult.setCurrent(item.getCurrent() + 1);
        dtoPageResult.setPages(item.getPages());
        dtoPageResult.setRecords(item.getRecords());
        dtoPageResult.setSize(item.getSize());
        dtoPageResult.setTotal(item.getTotal());
        return dtoPageResult;
    }

    /**
     * 转换mybatis plus的分页结果，直接返回，前端默认处理的是mybatis 格式的分页结果。这里为了后端理解上的一致
     * @param item mybatis plus的分页结果
     * @return 返回值
     */
    public static Map<String, Object> convertMyBatisPlusPageResult (IPage item) {
        Map<String, Object> result = new HashMap<>();
        // JAP 分页是从0开始，前端是从1开始
        result.put("current", item.getCurrent()+ 1);
        result.put("pages", item.getPages());
        result.put("records", item.getRecords());
        result.put("size", item.getSize());
        result.put("total", item.getTotal());
        return result;
    }

    /**
     * 系统里定义的星期几转成hutool里的星期几
     * @param paiKeShangKeTian 系统里定义的星期几
     * @return hutool里的星期几或null
     */
    public static Week convertPaiKeShangKeTian2Week(PaiKeShangKeTian paiKeShangKeTian) {
        Week week = null;
        switch (paiKeShangKeTian) {
            case ONE:
                week = Week.MONDAY;
                break;
            case TWO:
                week = Week.TUESDAY;
                break;
            case THREE:
                week = Week.WEDNESDAY;
                break;
            case FOUR:
                week = Week.THURSDAY;
                break;
            case FIVE:
                week = Week.FRIDAY;
                break;
            case SIX:
                week = Week.SATURDAY;
                break;
            case SEVEN:
                week = Week.SUNDAY;
                break;
            default:
                break;
        }
        return week;
    }

    /**
     * 学员到课状态转换
     * @param xueYuanDaoKeZhuangTai
     * @return
     */
    public static String convertXueYuanDaoKeZhuangTai2String(XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai) {
        String daoKeZhuangTaiStr = null;
        if(XueYuanDaoKeZhuangTai.DAO_KE.equals(xueYuanDaoKeZhuangTai)) {
            daoKeZhuangTaiStr = "到课";
        } else if(XueYuanDaoKeZhuangTai.CHI_DAO.equals(xueYuanDaoKeZhuangTai)) {
            daoKeZhuangTaiStr = "迟到";
        } else if(XueYuanDaoKeZhuangTai.QING_JIA.equals(xueYuanDaoKeZhuangTai)) {
            daoKeZhuangTaiStr = "请假";
        } else if(XueYuanDaoKeZhuangTai.WEI_DAO.equals(xueYuanDaoKeZhuangTai)) {
            daoKeZhuangTaiStr = "旷课";
        } else {
            daoKeZhuangTaiStr = "未知";
        }
        return daoKeZhuangTaiStr;
    }
}
