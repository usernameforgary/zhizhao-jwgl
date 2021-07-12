package com.zhizhao.jwgl.jiaowuguanli.domain.constant

//课程类型
enum class KeChengLeiXing {
    XIN_BAO,
    KUO_KE,
    XU_FEI
}

// 学员课程状态
enum class XuYuanKeChengZhuangTai {
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

//性别
enum class XingBie {
    NAN,
    NV,
    QITA
}

//优惠类型
enum class YouHuiLeiXing {
    ZHI_JIAN,
    ZHE_KOU
}

// 排课记录状态
enum class PaiKeJiLuZhuangTai {
    DAI_DIAN_MING,
    // 查询班级列表的，【已上/排课课次】时，会用到 YI_DIAN_MING这个状态
    YI_DIAN_MING
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