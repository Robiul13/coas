package com.asl.asl_rms;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
public class QartzConfig {

	@Bean
	public JobDetail sampleJobDetail() {
		return JobBuilder.newJob(LicenseJob.class).withIdentity("licenseJob").usingJobData("name", "ASL").storeDurably()
				.build();
	}

	@Bean
	public Trigger sampleJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1800)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger")
				.withSchedule(scheduleBuilder).build();
	}
}
