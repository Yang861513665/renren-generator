package io.renren.plugin;

import cn.hutool.core.util.IdUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author yangxj
 * @date 2020-08-10 15:57
 * <p>
 * 自定义id生成器
 */
@Component
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class IDGeneratorPlugin implements Interceptor {

    public final static String ID_FIELD = "id";

    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (!sqlCommandType.equals(SqlCommandType.INSERT)) { // 非插入类型跳过
           return invocation.proceed();
        }
        // 参数类型 map or entity
        if (param instanceof Map) {
            Map paramMap = (Map)param;
            // 是否为批量插入操作
            Optional batchParam = paramMap.values().stream().filter(paramValues -> paramValues instanceof List).findFirst();
            if (batchParam.isPresent()) {
                for (Object o : (List) batchParam.get()) {
                    restId(o);
                }
            } else {
                paramMap.put(ID_FIELD, IdUtil.fastSimpleUUID());
            }
        } else {
            restId(param);
        }
        return invocation.proceed();
    }

    private void restId(Object param) {
        try {
            Field id = param.getClass().getDeclaredField(ID_FIELD);
            id.setAccessible(true);
            if (Objects.isNull(id.get(param))) {
                id.set(param, IdUtil.fastSimpleUUID());
            }
        } catch (Exception e) {
        }
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof Executor ? (Plugin.wrap(target, this)) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
