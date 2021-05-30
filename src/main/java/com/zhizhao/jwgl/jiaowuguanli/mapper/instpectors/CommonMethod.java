package com.zhizhao.jwgl.jiaowuguanli.mapper.instpectors;

public enum CommonMethod {
    SELECT_ALL("selectAll");

    private String methodName;

    CommonMethod(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}
