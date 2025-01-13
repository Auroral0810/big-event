package com.auroral.validation;


import com.auroral.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {

    /**
     * 初始化方法，用于设置校验规则
     * @param value 将来要校验的数据
     * @param context 上下文对象，用于提供给校验器一些信息
     * @return 如果返回false，则校验不通过，如果返回true,则校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if(value == null)
        {
            return true;
        }
        if(value.equals("已发布")||value.equals("草稿")){
            return true;
        }
        return false;
    }
}
