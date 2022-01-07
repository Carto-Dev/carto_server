package com.carto.server.validators;

import com.carto.server.annotation.MinArraySize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MinArraySizeValidator implements ConstraintValidator<MinArraySize, List<String>> {
    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        return strings != null && strings.size() > 0;
    }
}
