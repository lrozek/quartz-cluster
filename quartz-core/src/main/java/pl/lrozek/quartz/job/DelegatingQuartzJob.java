package pl.lrozek.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DelegatingQuartzJob extends QuartzJobBean {

    private String delegateBean;

    private ApplicationContext applicationContext;

    @Override
    protected void executeInternal( JobExecutionContext context ) throws JobExecutionException {
        Job job = applicationContext.getBean( delegateBean, Job.class );
        job.execute( context );
    }

    public void setDelegateBean( String delegateBean ) {
        this.delegateBean = delegateBean;
    }

    public void setApplicationContext( ApplicationContext applicationContext ) {
        this.applicationContext = applicationContext;
    }

    public static final String APPLICATION_CONTEXT_KEY = "applicationContext";

    public static final String DELEGATE_BEAN_KEY = "delegateBean";

}
