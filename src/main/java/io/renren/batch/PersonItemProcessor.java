package io.renren.batch;

import io.renren.entity.PersonDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<PersonDO, PersonDO> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public PersonDO process(PersonDO personDO) throws Exception {

        PersonDO transformedPersonDO = new PersonDO(personDO.getFirstName().toUpperCase(), personDO.getLastName().toUpperCase());

        log.info("Converting (" + personDO + ") into (" + transformedPersonDO + ")");

        return transformedPersonDO;
    }

}