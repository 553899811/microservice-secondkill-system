package com.newbiegroup.secondkill.common.utils;

import com.newbiegroup.secondkill.common.exception.BizBusinessError;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * 表单验证工具类
 */

@Slf4j
public class FormValidateUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证实体，如遇到不通过的，直接抛出异常
     *
     * @param object
     */
    public static void validate(Object object) throws BusinessException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            String message = iter.next().getMessage();
            sb.append(message).append(",");
        }
        if (sb.length() > 0) {
            log.error("FormValidateUtils ValidateError: {}", sb.toString());
            throw new BusinessException(BizBusinessError.PARAMETER_VALIDATION_ERROR, sb.deleteCharAt(sb.length() - 1).toString());
        }

    }
}
