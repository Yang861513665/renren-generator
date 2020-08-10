package io.renren.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.BaseSpringMockTest;
import io.renren.entity.PersonDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangxj
 * @date 2020-08-10 13:45
 */
public class PersonDOMapperTest extends BaseSpringMockTest {

    @Autowired
    PersonMapper personMapper;

    @Test
    public void test(){
        int count = personMapper.selectCount(new QueryWrapper<PersonDO>().eq("first_name", "yang"));
        System.out.println(count);
    }
}