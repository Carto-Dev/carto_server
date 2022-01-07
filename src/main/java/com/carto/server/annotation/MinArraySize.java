package com.carto.server.annotation;

import com.carto.server.validators.MinArraySizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinArraySizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinArraySize {
    String message() default "The input list should be at least contain one element.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
