package com.mycompany.myapp.validator;

import com.mycompany.myapp.anotation.ExistInDb;
import com.mycompany.myapp.service.ExistService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class ExistedInDb implements ConstraintValidator<ExistInDb, Object> {
    private Class<?> aClass;
    private final List<ExistService> existServices;

    public ExistedInDb(List<ExistService> existServices) {
        this.existServices = existServices;
    }

    @Override
    public void initialize(ExistInDb constraintAnnotation) {
        aClass = constraintAnnotation.entity();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        for (var existService: existServices) {
            if (existService.isValidService(aClass)){
                return existService.exist(value);
            }
        }
        return false;
    }
}
