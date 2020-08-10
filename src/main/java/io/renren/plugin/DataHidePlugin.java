package io.renren.plugin;

import io.renren.annotation.DataHide;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * @author yangxj
 * @date 2020-08-10 11:31
 * <p>
 * 数据脱敏拦截器
 */
@Component
@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class))
public class DataHidePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List result = (List) invocation.proceed();
        return dataHide(result);
    }

    @SuppressWarnings("unchecked")
    private List dataHide(List result) {
        if (result.isEmpty()) return result;
        Class<?> clazz = result.get(0).getClass();
        // 基本类型或者基本类型的包装类型跳过
        if (isWrapClass(clazz)) return result;
        // 获取字段开始处理
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 包含脱敏注解字段做脱敏处理
            if (field.isAnnotationPresent(DataHide.class)) {
                result.forEach(originData -> doHide(originData, field));
            }
        }
        return result;
    }

    private boolean isWrapClass(Class<?> clazz) {
        try {
            return clazz.isPrimitive() || ((Class) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    private void doHide(Object originData, Field field) {
        try {
            field.setAccessible(true);
            field.set(originData, "**" + field.get(originData) + "**");
        } catch (Exception e) {
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
