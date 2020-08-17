package com.yang.javaspringsoot.aspect;




import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAnnotation {
    String value() default "aaa";
}
