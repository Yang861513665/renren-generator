package io.renren.batch;

import com.google.common.collect.Lists;
import io.renren.entity.PersonDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author yangxj
 * @date 2020-07-06 14:57
 */
@Slf4j
public class PersonItemReader implements ItemReader<PersonDO> {
    private List<PersonDO> personDOS;

    @PostConstruct
    public void init() {
        personDOS = Lists.newArrayList(new PersonDO("zhang", "shan"), new PersonDO("li", "si"), new PersonDO("wang", "wu"));
    }

    @Override
    public PersonDO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (personDOS.isEmpty()) return null;
        PersonDO personDO = personDOS.remove(0);
        log.info("<====== read data start =======>");
        log.info("data: {}", personDO);
        return personDO;
    }


}
