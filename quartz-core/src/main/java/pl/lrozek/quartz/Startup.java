package pl.lrozek.quartz;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static pl.lrozek.quartz.job.DelegatingQuartzJob.DELEGATE_BEAN_KEY;

import java.util.List;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import pl.lrozek.quartz.job.DelegatingQuartzJob;

public class Startup {

    public static void main( String[] args ) throws Exception {
        ApplicationContext applicationContext = new GenericXmlApplicationContext( "classpath:application-context.xml" );
        Scheduler scheduler = applicationContext.getBean( Scheduler.class );
        schedulaDynamicJobs( scheduler );
    }

    public static void schedulaDynamicJobs( Scheduler scheduler ) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob( DelegatingQuartzJob.class ).usingJobData( DELEGATE_BEAN_KEY, "dynamicJob" ).withIdentity( "dynamicJob" ).build();
        boolean jobExists = null != scheduler.getJobDetail( jobDetail.getKey() );
        if ( !jobExists ) {
            List<Trigger> triggers = newArrayList();
            for ( int i = 1; i <= 10; i++ ) {
                SimpleTrigger simpleTrigger = newTrigger().usingJobData( "unternehmenId", Long.valueOf( i ) ).withIdentity( "dynamicTrigger" + i ).withSchedule( simpleSchedule().withIntervalInSeconds( 1 ).withRepeatCount( 50 ) ).build();
                triggers.add( simpleTrigger );
            }
            Map<JobDetail, List<Trigger>> triggersAndJobs = newHashMap();
            triggersAndJobs.put( jobDetail, triggers );
            scheduler.scheduleJobs( triggersAndJobs, false );

        }
    }
}
