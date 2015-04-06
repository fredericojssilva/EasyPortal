package net.fredericosilva.easyportal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by frederico <fredericojssilva@gmail.com>
 * on 06/04/2015 .
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE})

public @interface IPortalOut {
    public String name() default "";
}

