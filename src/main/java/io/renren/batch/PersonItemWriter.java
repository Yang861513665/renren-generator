package io.renren.batch;

import io.renren.entity.PersonDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author yangxj
 * @date 2020-07-06 15:26
 */
@Slf4j
public class PersonItemWriter implements ItemWriter<PersonDO> {

    @Override
    public void write(List<? extends PersonDO> list) throws Exception {
     log.info("<====== write data start ======>");
     log.info("data size: {}, data: {}", list.size(), list);
    }
}
