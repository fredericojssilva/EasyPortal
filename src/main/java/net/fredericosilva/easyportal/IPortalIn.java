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
@Target(ElementType.FIELD)
public @interface IPortalIn {
    public String name() default "";
}

