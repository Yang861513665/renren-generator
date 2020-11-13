package io.renren.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxj
 * @date 2020-07-06 17:17
 */
//@RestController
@RequestMapping("job")
@Slf4j
public class BatchController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job1;
    @GetMapping("run")
    public void run() throws Exception{
        log.info("<===== try to run job ======>");
        jobLauncher.run(job1, new JobParameters());
    }
}
