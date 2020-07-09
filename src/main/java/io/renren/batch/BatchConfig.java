package io.renren.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxj
 * @date 2020-07-06 14:49
 */
@Configuration
@EnableBatchProcessing
@SuppressWarnings("unchecked")
public class BatchConfig {
    @Autowired
    JobBuilderFactory jobs;
    @Autowired
    StepBuilderFactory steps;

    @Bean
    ItemReader personItemReader() {
        return new PersonItemReader();
    }

    @Bean
    ItemWriter personItemWriter() {
        return new PersonItemWriter();
    }

    @Bean
    ItemProcessor personItemProcessor() {
        return new PersonItemProcessor();
    }

    @Bean
    Step step1(ItemReader read, ItemProcessor processor, ItemWriter writer) {
        return steps.get("step1")
//                .allowStartIfComplete(true)
                .chunk(1)
                .reader(read)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    Job job1(Step step1) {
        return jobs.get("job1")
                .flow(step1)
                .end()
                .build();
    }
}
