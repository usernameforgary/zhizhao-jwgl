package com.zhizhao.jwgl.jiaowuguanli.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    // 转换Jpa的分页结果，组装成前端需要的格式
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

    // 转换mybatis plus的分页结果，直接返回，前端默认处理的是mybatis 格式的分页结果。这里为了后端理解上的一致
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
}
