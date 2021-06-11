package com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XiTongCaiDanView {
    @Id
    private String id;
    private String parentId;
    private String menuName;
    private String menuUrl;
    private Integer menuOrder;
    private Boolean hidden = false;
    private String menuIcon;
}
