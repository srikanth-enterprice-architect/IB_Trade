/**
 * 
 */
package com.IdgoSoft.test.intigration.Munit;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mule.api.MuleContext;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import com.IdgoSoft.test.intigration.Munit.util.Ib_Trade_Constants;

/**
 * @author srikanth.vaddella
 *
 */
public class PerformanceFunctionalMunitSuiteTest extends FunctionalMunitSuite {
	
	
	/* (non-Javadoc)
	 * @see org.mule.munit.runner.functional.FunctionalMunitSuite#muleContextStarted(org.mule.api.MuleContext)
	 */
	@Override
	protected void muleContextStarted(MuleContext muleContext) {
	  System.out.println("Mule Context Started at" + new Date(muleContext.getStartDate()));
	}
	
	
	/* (non-Javadoc)
	 * @see org.mule.munit.runner.functional.FunctionalMunitSuite#haveToDisableInboundEndpoints()
	 */
	@Override
	protected boolean haveToDisableInboundEndpoints() {
	        return Ib_Trade_Constants.haveToDisableInboundEndpoints;
	}
	
	/* (non-Javadoc)
	 * @see org.mule.munit.runner.functional.FunctionalMunitSuite#haveToMockMuleConnectors()
	 */
	@Override
	protected boolean haveToMockMuleConnectors() {
	  return Ib_Trade_Constants.haveToMockMuleConnectors;
	}
	
	/* (non-Javadoc)
	 * @see org.mule.munit.runner.functional.FunctionalMunitSuite#getFlowsExcludedOfInboundDisabling()
	 */
	@Override
	protected List<String> getFlowsExcludedOfInboundDisabling() {
	  return Arrays.asList("firstFlow","thirdFlow");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mule.munit.runner.functional.FunctionalMunitSuite#getConfigResources
	 * () For example: return "my-config-file.xml,my-second-config-file.xml"
	 */
	
	
	@Override
	protected String getConfigResources() {
		return "ib_trade_test.xml";
	}

}
