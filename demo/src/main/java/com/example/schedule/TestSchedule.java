package com.example.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TestSchedule {
	
	private final  Log logger = LogFactory.getLog(TestSchedule.class);
	
	@Value("${task-schedule}")
	private Boolean taskSchedule;
	
	@Scheduled(cron="0/20 * * * * ?")
	public void scheduleOne(){
		//logger.info("this service can run taskSchedule :" + (taskSchedule == true) );
		if(taskSchedule){
			logger.info("this is my  test schedule task");
		}
	}
}
