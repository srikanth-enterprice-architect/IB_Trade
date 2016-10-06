/**
 * 
 */
package com.IdgoSoft.test.intigration.functionaltestCase;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.construct.FlowConstruct;

import com.IdgoSoft.test.intigration.functionaltestCase.util.IbTradeFunctionalTestCase;


/**
 * @author srikanth.vaddella
 *
 */
public class IbTradeTest extends IbTradeFunctionalTestCase{

	    @Rule
	    public TemporaryFolder localEndpointRoot = new TemporaryFolder();
      
	    @BeforeClass
	    public static void fixtureSetup() throws Exception {  }
	    
	    @AfterClass
	    public static void fixtureTeardown() {   }

	    @After
	    public void teardown() {  }


	    protected String[] getConfigFiles() {
	        return new String[] { TestData.IB_TRADE };
	    }

	    @Override
	    protected void doSetUpBeforeMuleContextCreation() throws Exception {
	        super.doSetUpBeforeMuleContextCreation();
	    }

	    @Test
	    public void ib_trade_testFinanceData() throws Exception {

	        MuleClient muleClient = muleContext.getClient();
	        @SuppressWarnings("unused")
			MuleMessage message = muleClient.send("/sri", this.getTestMuleMessage());
	        final CountDownLatch latch = new CountDownLatch(1);
	        try {
	            latch.await(30, TimeUnit.SECONDS);
	        }catch (InterruptedException ie){
	            ie.printStackTrace();
	        }
	    }

	    protected int getErrors() {
	        long errors = 0;
	        for (FlowConstruct construct : muleContext.getRegistry().lookupFlowConstructs()) {
	            errors += construct.getStatistics().getExecutionErrors();
	        }
	        return errors > 0 ? new Long(errors).intValue() : 0;
	    }

	    private interface TestData {
	    	String IB_TRADE = "main.xml";
	  	    }

	}



