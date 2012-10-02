package pl.lrozek.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SimpleJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger( SimpleJob.class );

    @Override
    protected void executeInternal( JobExecutionContext context ) throws JobExecutionException {
        try {
            String instanceId = context.getScheduler().getSchedulerInstanceId();
            LOGGER.info( "running simple job in following instance " + instanceId );
        }
        catch ( SchedulerException e ) {
        }
    }
}
