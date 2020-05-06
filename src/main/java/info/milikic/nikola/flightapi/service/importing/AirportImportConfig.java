package info.milikic.nikola.flightapi.service.importing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class AirportImportConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<AirportImportDto> reader() {
        return new FlatFileItemReaderBuilder<AirportImportDto>()
                .name("airportItemReader")
                .delimited()
                .names("id", "name", "city", "country", "iata", "icao", "latitude", "longitude", "altitude", "timezone", "dst", "tzTime", "type", "source")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(AirportImportDto.class);
                }})
                .build();
    }

    @Bean
    public AirportItemProcessor processor() {
        return new AirportItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<AirportImportDto> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AirportImportDto>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(
                        "INSERT INTO airport (name, city_id, iata, icao, latitude, longitude, altitude, timezone, dst, tz_time, type, source) " +
                        "VALUES (:name, :cityId, :iata, :icao, :latitude, :longitude, :altitude, :timezone, :dst, :tzTime, :type, :source )")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importAirportsJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<AirportImportDto> writer) {
        return stepBuilderFactory.get("step1")
                .<AirportImportDto, AirportImportDto>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .faultTolerant()
                .skipLimit(20)
                .skip(DataIntegrityViolationException.class)
                .build();
    }

}
