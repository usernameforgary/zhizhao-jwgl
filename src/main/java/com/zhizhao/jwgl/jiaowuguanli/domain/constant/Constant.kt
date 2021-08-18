package com.zhizhao.jwgl.jiaowuguanli.domain.constant

//课程类型
enum class KeChengLeiXing {
    XIN_BAO,
    KUO_KE,
    XU_FEI
}

/**
 *  学员课程状态
    待确认:
        学员有缴费记录,但会计还未确认缴费,该状态下学员不可加入班级
    待补缴:
        学员已完成报名,但会计只收到缴费金额的一部分,此时状态为“部分缴费”,状态,该状态下学员可以加入班级上课。不论学员报名了多少课程,在“部分缴费”状态下,所有报名的课程均可加入班级。
    待排课:
        会计已确认缴费,但学员没有加入任何班级
    待上课:
        学员加入班级,且有剩余课时的情况下,为待上课状态,该状态下老师可进行点名操作
    待结课:
        学员加入班级,且剩余课时为0,该状态下显示结课按钮
    已结课:
        学员某一课程剩余课时为0,且已点击结课按钮,则该课程的状态为已结课。
 */
enum class XueYuanKeChengZhuangTai {
    DAI_QUE_REN,
    DAI_BU_JIAO,
    DAI_PAI_KE,
    DAI_SHANG_KE,
    DAI_JIE_KE,
    YI_JIE_KE
}

// 账号类型
enum class ZhangHaoLeiXing {
    YUAN_GONG,
    XUE_YUAN
}

// 学员状态
enum class XueYuanZhuangTai {
    QIAN_ZAI,
    ZAI_DU,
    LI_SHI
}

// 班级状态
enum class BanJiZhuangTai {
    KAI_KE,
    JIE_KE
}

// 性别
enum class XingBie {
    NAN,
    NV,
    QITA
}

// 优惠类型
enum class YouHuiLeiXing {
    ZHI_JIAN,
    ZHE_KOU
}

// 排课记录状态
enum class PaiKeJiLuZhuangTai {
    DAI_DIAN_MING,
    // 查询班级列表的，【已上/排课课次】时，会用到 YI_DIAN_MING这个状态
    YI_DIAN_MING,
    // 已点评
    YI_DIAN_PING
}

// 排课记录里，上课学员的类型
enum class ShangKeXueYuanLeiXing {
    BEN_BAN,
    SHI_TING,
    BU_KE,
    LIN_SHI
}

// 学员到课状态
enum class XueYuanDaoKeZhuangTai {
    DAO_KE,
    CHI_DAO,
    QING_JIA,
    WEI_DAO
}

/**
 * 排课方式，规则排课 || 日历排课
 */
enum class PaiKeFangShi {
    GUI_ZE_PAI_KE,
    RI_LI_PAI_KE
}

/**
 * 规则排课重复方式，每天重复 || 每周重复
 */
enum class GuiZeChongFuFangShi {
    MEI_ZHOU,
    MEI_TIAN
}

/**
 * 规则排课结束方式，日期结束 || 次数结束
 */
enum class GuiZeJiShuFangShi {
    RI_QI_JIE_SHU,
    CI_SHU_JIE_SHU
}


/**
 * 排课上课时间，周一到周日，每天
 */
enum class PaiKeShangKeTian {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    DAILY
}

/**
 * 收款方式
 */
enum class ShouKuanFangShi {
    ZHI_FU_BAO,
    WEI_XIN,
    XIAN_JIN
}

/**
 * 缴费记录状态
 * 未交费：学员已完成报名，但会计未确认收费，此时状态为“未交费”状态，该状态下学员不能加入班级上课。
 * 部分缴费：学员已完成报名，但会计只收到缴费金额的一部分，此时状态为“部分缴费”，状态，该状态下学员可以加入班级上课。不论学员报名了多少课程，在“部分缴费”状态下，所有报名的课程均可加入班级。
 * 全部已缴：学员已完成报名，会计确认收到全部缴费金额，此时学员可加入对应报名的课程班级上课。
 */
enum class JiaoFeiJiLuZhuangTai {
    WEI_JIAO_FEI,
    BU_FEN_JIAO_FEI,
    QUAN_BU_YI_JIAO
}

/**
 * 缴费中的流水类型
 */
enum class LiuShuiLieXing {
    XIN_JIAO,
    BU_JIAO
}

/**
 * 补课记录状态
 */
enum class BuKeZhuangTai {
    DAI_BU_KE,
    BU_KE_ZHONG,
    YI_BU_KE
}

/**
 * 试听记录状态
 * 已预约
 * 已过期：安排学员试听后，已过试听上课的时间，但老师点名为“未到”，则该状态为“已过期”
 * 已体验：安排学员试听后，给该试听学员点名为“到课或迟到”，则该状态为“已体验”
 * 已报名：安排学员试听后，给该试听学员点名为“到课或迟到”，其次再给试听学员报名该课程，则会由“已体验”变成“已报名”。
 * 已取消：安排试听后，如果未上课点名，直接给学员报名该课程，则会由“已预约”变成“已取消”
 */
enum class ShiTingZhuangTai {
    YI_YU_YUE,
    YI_GUO_QI,
    YI_TI_YAN,
    YI_BAO_MING,
    YI_QU_XIAO
}

/**
 * 成长记录类型
 * KE_HOU_DIAN_PING： 课后点评
 * HUO_LI_KE_TANG：活力课堂
 * XUE_YUAN_FENG_CAI：学员风采
 * ZUO_PIN_ZHAN_SHI：作品展示
 * SHENG_HUO_JI_LU：生活记录
 */
enum class ChengZhangJiLuLeiXing {
    KE_HOU_DIAN_PING,
    HUO_LI_KE_TANG,
    XUE_YUAN_FENG_CAI,
    ZUO_PIN_ZHAN_SHI,
    SHENG_HUO_JI_LU
}

/**
 * 上传下载文件分类: 上传 | 下载
 */
enum class WenJianFenLei {
    DOWNLOAD,
    UPLOAD
}

/**
 * 上传下载文件状态: 未下载 | 已下载
 */
enum class WenJianZhuangTai {
    WEI_XIA_ZAI,
    YI_XIA_ZAI
}