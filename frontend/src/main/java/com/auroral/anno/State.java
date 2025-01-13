package com.auroral.anno;

import com.auroral.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.List;

@Documented //原注解
@Target({ElementType.FIELD})//元注解
@Retention(RetentionPolicy.RUNTIME)//原注解
@Constraint(
        validatedBy = {StateValidation.class}
)

public @interface State {
    //校验失败后的提示信息
    String message() default "{state参数的值只能是已发布或者草稿}";
//指定分组
    Class<?>[] groups() default {};
//负载 获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
