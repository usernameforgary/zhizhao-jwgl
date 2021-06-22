package com.zhizhao.jwgl.jiaowuguanli.utils;

import com.zhizhao.jwgl.jiaowuguanli.constant.ErrorCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@Data
public class PPResult<T> {
    @NotBlank
    int code;

    @NotBlank
    String msg;

    T payload;

    public static <T> PPResult<T> success() {
        return getPPResultOK(null);
    }

    public static PPResult getPPResultOK(Object payload) {
        return new PPResult(1, "OK", payload);
    }

    public static PPResult Ok() {
        return new PPResult(1, "OK", null);
    }

    public static PPResult OkWith(Object payload) {
        return new PPResult(1, "OK", payload);
    }

    public static PPResult getPPResultNotImplemented() {
        return new PPResult(1, "NotImplemented", null);
    }

    public static <T> PPResult<T> fail(ErrorCode errorCode, String description) {
        return fail(errorCode.getCode(), description);
    }

    private static <T> PPResult<T> fail(int code, String description) {
        return new PPResult(code, description, null);
    }
}

