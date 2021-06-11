package com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class XiTongCaiDan_chaungJianCmd(
        @TargetAggregateIdentifier
        var id: String,
        var parentId: String,
        var menuName: String,
        var menuUrl: String,
        var menuOrder: Int,
        var hidden: Boolean,
        var menuIcon: String,
)

data class XiTongCaiDan_chaungJianEvt(
        var id: String,
        var parentId: String,
        var menuName: String,
        var menuUrl: String,
        var menuOrder: Int,
        var hidden: Boolean,
        var menuIcon: String,
)

data class XiTongCaiDan_gengXinCmd(
        @TargetAggregateIdentifier
        var id: String,
        var parentId: String,
        var menuName: String,
        var menuUrl: String,
        var menuOrder: Int,
        var hidden: Boolean,
        var menuIcon: String,
)

data class XiTongCaiDan_gengXinEvt(
        var id: String,
        var parentId: String,
        var menuName: String,
        var menuUrl: String,
        var menuOrder: Int,
        var hidden: Boolean,
        var menuIcon: String,
)