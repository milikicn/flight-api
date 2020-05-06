package info.milikic.nikola.flightapi.controller;

import info.milikic.nikola.flightapi.service.importing.AirportImportDto;
import info.milikic.nikola.flightapi.vo.ApplicationRoles;
import info.milikic.nikola.flightapi.vo.ServiceConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = ServiceConst.AIRPORT_API)
public class AirportController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private FlatFileItemReader<AirportImportDto> reader;

    @Secured(ApplicationRoles.ROLE_ADMIN)
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public BatchStatus uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        reader.setResource(new InputStreamResource(file.getInputStream()));

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));

        JobExecution jobExecution = jobLauncher.run(job, new JobParameters(maps));

        log.info("JobExecution: " + jobExecution.getStatus());
        log.info("Batch is Running...");
        while (jobExecution.isRunning()) {
            log.info("...");
        }

        return jobExecution.getStatus();
    }
}
