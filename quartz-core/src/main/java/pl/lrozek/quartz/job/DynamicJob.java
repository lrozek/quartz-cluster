package pl.lrozek.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DynamicJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger( DynamicJob.class );

    @Override
    public void execute( JobExecutionContext context ) throws JobExecutionException {
        try {
            Long unternehmenId = (Long) context.getTrigger().getJobDataMap().get( "unternehmenId" );
            String instanceId = context.getScheduler().getSchedulerInstanceId();
            LOGGER.info( "running dynamic job for following unternehmenId: {} in following instance {}", unternehmenId, instanceId );
            Thread.sleep( 10000 );
        }
        catch ( SchedulerException e ) {
            LOGGER.error( "exception thrown", e );
        }
        catch ( InterruptedException e ) {
            LOGGER.error( "exception thrown", e );
        }
    }

}
