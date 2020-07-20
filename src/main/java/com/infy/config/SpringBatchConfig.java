package com.infy.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.infy.model.*;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	public Job firstJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<User> itemReader, ItemProcessor<User, User> itemProcessor, ItemWriter<User> itemWriter) {
		Step step1 = stepBuilderFactory.get("step_first_job").<User, User>chunk(10).reader(itemReader)
				.writer(itemWriter).build();

		return jobBuilderFactory.get("firstJob").incrementer(new RunIdIncrementer()).start(step1).build();
	}

	@Bean
	public FlatFileItemReader<User> reader() {
		FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<User>();
		flatFileItemReader.setResource(new FileSystemResource("src/main/resources/user.csv"));
		flatFileItemReader.setName("user.csv");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;

	}
     @Bean
	private LineMapper<User> lineMapper() {
		// TODO Auto-generated method stub
		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<User>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] { "id", "name" });

		BeanWrapperFieldSetMapper<User> beanFiledMapper = new BeanWrapperFieldSetMapper<User>();
		beanFiledMapper.setTargetType(User.class);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanFiledMapper);

		return defaultLineMapper;
	}

}