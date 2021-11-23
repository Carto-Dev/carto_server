package com.carto.server.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@AuthenticationPrincipal(expression = "@fetchUser.apply(#this)", errorOnInvalidType = true)
public @interface LoggedInUser {
}
