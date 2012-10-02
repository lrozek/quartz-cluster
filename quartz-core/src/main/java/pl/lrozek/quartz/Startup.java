package pl.lrozek.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Startup {

    public static void main( String[] args ) throws Exception {
        ApplicationContext applicationContext = new GenericXmlApplicationContext( "classpath:application-context.xml" );
    }

}
