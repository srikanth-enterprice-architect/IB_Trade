
package com.IdgoSoft.test.intigration.functionaltestCase.util;

import java.util.Iterator;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.context.MuleContextBuilder;
import org.mule.config.DefaultMuleConfiguration;
import org.mule.construct.AbstractFlowConstruct;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author srikanth.vaddella
 *
 */
public abstract class performanceFunctionalTestCase extends FunctionalTestCase {
    private static final Logger LOG = LoggerFactory.getLogger(performanceFunctionalTestCase.class);
    

    /* (non-Javadoc)
     * @see org.mule.tck.junit4.AbstractMuleContextTestCase#configureMuleContext(org.mule.api.context.MuleContextBuilder)
     */
    protected void configureMuleContext(MuleContextBuilder contextBuilder) {
        super.configureMuleContext(contextBuilder);
        DefaultMuleConfiguration config = new DefaultMuleConfiguration();
        config.setShutdownTimeout(1000);
        config.setWorkingDirectory(this.getWorkingDirectory().getAbsolutePath());
        contextBuilder.setMuleConfiguration(config);
    }

    /* (non-Javadoc)
     * @see org.mule.tck.junit4.AbstractMuleContextTestCase#doTearDownAfterMuleContextDispose()
     */
    protected void doTearDownAfterMuleContextDispose() throws Exception {
        if(!this.isDisposeContextPerClass()) {
            for(int i = 0; i < 100 && muleContext != null && !muleContext.isStopped(); ++i) {
                LOG.info("Failed to stop Mule context. Retrying...");
                muleContext.dispose();
            }

            if(muleContext != null && !muleContext.isStopped()) {
                LOG.warn("Failed to stop Mule context, setting to null as a last gasp attempt to recover. Failure to stop the Mule context is likely to result in javax.management.InstanceAlreadyExistsException exceptions being thrown by subsequent tests.");
                muleContext = null;
            }
        }

    }

   
    
    /* (non-Javadoc)
     * @see org.mule.tck.junit4.AbstractMuleContextTestCase#createMuleContext()
     * created from AbstractMuleContextTestCase
     */
    protected MuleContext createMuleContext() throws Exception {
        MuleContext muleContext = super.createMuleContext();
        if(this.isStartFlowsOnStartupEnabled()) {
            this.startFlowsOnStartup(muleContext);
        }

        return muleContext;
    }

    /**
     * @return
     */
    protected boolean isStartFlowsOnStartupEnabled() {
        return true;
    }

    /**
     * @param muleContext
     */
    private void startFlowsOnStartup(MuleContext muleContext) {
        Iterator<?> var2 = muleContext.getRegistry().lookupFlowConstructs().iterator();

        while(var2.hasNext()) {
            FlowConstruct flow = (FlowConstruct)var2.next();
            AbstractFlowConstruct abstractFlow = (AbstractFlowConstruct)flow;
            abstractFlow.setInitialState("started");
        }

    }

    /* (non-Javadoc)
     * @see org.mule.tck.junit4.FunctionalTestCase#getConfigFiles()
     */
    protected String[] getConfigFiles() {
		return null;
    }

    /* (non-Javadoc)
     * @see org.mule.tck.junit4.AbstractMuleTestCase#getTestTimeoutSecs()
     */
    public int getTestTimeoutSecs() {
        return 180;
    }

    /* (non-Javadoc)
     * @see org.mule.tck.junit4.FunctionalTestCase#runFlow(java.lang.String)
     */
    protected MuleEvent runFlow(String flowName) throws Exception {
        this.waitForAllFlowsToStart();
        return super.runFlow(flowName);
    }

    /**
     * @throws Exception
     */
    protected void waitForAllFlowsToStart() throws Exception {
        while(muleContext.getRegistry().lookupFlowConstructs().stream().anyMatch((f) -> {
            return f instanceof Flow?((Flow)f).isStopped():false;
        })) {
            Thread.sleep(100L);
        }

    }
    
   
}
