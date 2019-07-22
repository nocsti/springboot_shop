package com.tfotu.shop.domin;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object data){
        ValidationResult res = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSet =  validator.validate(data);
        if( validateSet.size() > 0 ){
            res.setHasError(true);
            validateSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                res.getErrorMsgMap().put( propertyName, errMsg);
            });
        }
        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
