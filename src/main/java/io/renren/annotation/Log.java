package io.renren.annotation;

import java.lang.annotation.*;

/**
 * @author yangxj
 * @date 2020-08-31 09:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {
    /**
     * 是否记录请求参数
     */
    boolean recordRequestParam() default true;
}
