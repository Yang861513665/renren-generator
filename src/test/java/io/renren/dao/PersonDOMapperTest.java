package io.renren.dao;

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
        PersonDO personDO = new PersonDO("yang", "ximing");
//        personMapper.add(personDO);

        personMapper.add2(null, "yang", "xijie");

    }
}