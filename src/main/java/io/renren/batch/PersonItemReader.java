package io.renren.batch;

import com.google.common.collect.Lists;
import io.renren.entity.Person;
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
public class PersonItemReader implements ItemReader<Person> {
    private List<Person> persons;

    @PostConstruct
    public void init() {
        persons = Lists.newArrayList(new Person("zhang", "shan"), new Person("li", "si"), new Person("wang", "wu"));
    }

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (persons.isEmpty()) return null;
        Person person = persons.remove(0);
        log.info("<====== read data start =======>");
        log.info("data: {}", person);
        return person;
    }


}
