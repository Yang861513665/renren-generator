package io.renren.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.entity.PersonDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangxj
 * @date 2020-08-10 13:43
 */
@Mapper
public interface PersonMapper extends BaseMapper<PersonDO> {
}
