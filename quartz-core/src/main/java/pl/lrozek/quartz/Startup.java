package pl.lrozek.quartz;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import pl.lrozek.quartz.job.DynamicJob;

public class Startup {

    public static void main( String[] args ) throws Exception {
        ApplicationContext applicationContext = new GenericXmlApplicationContext( "classpath:application-context.xml" );
        Scheduler scheduler = applicationContext.getBean( Scheduler.class );
        for ( int i = 1; i <= 10; i++ ) {
            SimpleTrigger simpleTrigger = newTrigger().withSchedule( simpleSchedule().withIntervalInSeconds( 1 ).withRepeatCount( 50 ) ).build();
            JobDetail jobDetail = JobBuilder.newJob( DynamicJob.class ).withIdentity( "dynamicJob" + i ).usingJobData( "unternehmenId", Long.valueOf( i ) ).build();
            scheduler.scheduleJob( jobDetail, simpleTrigger );
        }
    }
}
