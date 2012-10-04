package pl.lrozek.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger( SimpleJob.class );

    @Override
    public void execute( JobExecutionContext context ) throws JobExecutionException {
        try {
            String instanceId = context.getScheduler().getSchedulerInstanceId();
            LOGGER.info( "running simple job in following instance: {}", instanceId );
            Thread.sleep( 10000 );
            LOGGER.info( "finishing simple job in following instance: {}", instanceId );
        }
        catch ( SchedulerException e ) {
            LOGGER.error( "exception thrown", e );
        }
        catch ( InterruptedException e ) {
            LOGGER.error( "exception thrown", e );
        }
    }

}
