package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import javax.persistence.Entity;
import java.util.Map;
import java.util.Set;

public class BanJiPaiKeXinXi {
    private Long id;
    private Long banJiId;
    private Map<String, Object> paiKeGuiZe;
    private Set<Long> paiKeJiLu;
}
