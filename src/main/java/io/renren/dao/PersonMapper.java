package io.renren.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.entity.PersonDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangxj
 * @date 2020-08-10 13:43
 */
@Mapper
public interface PersonMapper extends BaseMapper<PersonDO> {
    @Insert("insert into person (id, first_name, last_name) values(#{id}, #{firstName}, #{lastName})")
    int add(PersonDO personDO);

    @Insert("insert into person (id, first_name, last_name) values(#{id}, #{firstName}, #{lastName})")
    int add2(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
