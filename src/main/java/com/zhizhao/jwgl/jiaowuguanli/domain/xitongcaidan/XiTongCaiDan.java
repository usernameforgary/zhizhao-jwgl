package com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan;

import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.swing.*;
import java.util.Map;

@Aggregate
@Data
public class XiTongCaiDan {
    @AggregateIdentifier
    private String id;
    private String parentId;
    private String menuName;
    private String menuUrl;
    private Integer menuOrder;
    private Boolean hidden = false;
    private String menuIcon;

    @CommandHandler
    public XiTongCaiDan(XiTongCaiDan_chaungJianCmd cmd) {
        AggregateLifecycle.apply(new XiTongCaiDan_chaungJianEvt(
                cmd.getId(),
                cmd.getParentId(),
                cmd.getMenuName(),
                cmd.getMenuUrl(),
                cmd.getMenuOrder(),
                cmd.getHidden(),
                cmd.getMenuIcon()
        ));
    }

    @EventSourcingHandler
    public void on(XiTongCaiDan_chaungJianEvt evt) {
        this.id = evt.getId();
        this.parentId = evt.getParentId();
        this.menuName = evt.getMenuName();
        this.menuUrl = evt.getMenuUrl();
        this.menuOrder = evt.getMenuOrder();
        this.hidden = evt.getHidden();
        this.menuIcon = evt.getMenuIcon();
    }

    @CommandHandler
    public void on(XiTongCaiDan_gengXinCmd cmd) {
        AggregateLifecycle.apply(new XiTongCaiDan_gengXinEvt(
                cmd.getId(),
                cmd.getParentId(),
                cmd.getMenuName(),
                cmd.getMenuUrl(),
                cmd.getMenuOrder(),
                cmd.getHidden(),
                cmd.getMenuIcon()
        ));
    }

    @EventSourcingHandler
    public void on(XiTongCaiDan_gengXinEvt evt) {
        this.parentId = evt.getParentId();
        this.menuName = evt.getMenuName();
        this.menuUrl = evt.getMenuUrl();
        this.menuOrder = evt.getMenuOrder();
        this.hidden = evt.getHidden();
        this.menuIcon = evt.getMenuIcon();
    }
}

