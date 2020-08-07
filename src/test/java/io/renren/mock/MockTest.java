package io.renren.mock;

import com.google.common.collect.ImmutableMap;
import io.renren.BaseSpringMockTest;
import io.renren.dao.GeneratorDao;
import io.renren.service.SysGeneratorService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangxj
 * @date 2020-08-07 14:53
 */
public class MockTest extends BaseSpringMockTest {
    @InjectMocks
            @Spy // @Spy可以对标注该注解的类进行打桩
    SysGeneratorService sysGeneratorService;


    @Mock
    GeneratorDao generatorDao;


    @Test
    public void test() {
        List<Map<String, Object>> lists = new ArrayList<>();
        lists.add(ImmutableMap.of("name", "yangxj"));
        lists.add(ImmutableMap.of("age", 24));

        Mockito.when(generatorDao.queryList(Mockito.anyMap())).thenReturn(lists);

//        Mockito.doReturn(ImmutableMap.of("sex", "female")).when(sysGeneratorService).get();

//        Mockito.when(sysGeneratorService.get()).thenReturn(ImmutableMap.of("sex", "sex"));

        PageUtils result = sysGeneratorService.queryList(new Query(ImmutableMap.of("page", 1, "limit", 10)));


        Assert.assertEquals(2, result.getList().size());


    }

}
