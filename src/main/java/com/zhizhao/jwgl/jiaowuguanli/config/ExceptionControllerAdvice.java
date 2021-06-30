package com.zhizhao.jwgl.jiaowuguanli.config;

import com.zhizhao.jwgl.jiaowuguanli.constant.ErrorCode;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public PPResult handler(BusinessException e) {
        return PPResult.fail(ErrorCode.BusinessError, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public PPResult handler(ConstraintViolationException e) {
        return PPResult.fail(ErrorCode.DataAlreadyExisted, "存在重复记录");
    }
}
