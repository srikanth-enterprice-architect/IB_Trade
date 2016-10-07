/**
 * 
 */
package com.IdgoSoft.test.intigration.Munit.testcase;

import org.junit.Assert;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.munit.common.mocking.MessageProcessorMocker;
import org.mule.munit.common.mocking.SpyProcess;

import com.IdgoSoft.test.intigration.Munit.PerformanceFunctionalMunitSuiteTest;

/**
 * @author srikanth.vaddella
 *
 */
public class Ib_trade_FunctionalMunitSuiteTest extends PerformanceFunctionalMunitSuiteTest {

	/**
	 * @throws Exception
	 */
	@org.junit.Test
	public void Test() throws Exception {
		String myStringPayload = "hi";
		MuleEvent resultMuleEvent = runFlow("testfileFlow", testEvent(myStringPayload));
		Assert.assertEquals(myStringPayload, resultMuleEvent.getMessage().getPayload());
	}

	/**
	 * @throws Exception
	 */
	@org.junit.Test
	public void mockingTest() throws Exception {
		String myMockPayload = "815-OA";
		MuleMessage messageToBeReturned = muleMessageWithPayload(myMockPayload);
		MessageProcessorMocker mocker = whenMessageProcessor("select").ofNamespace("db");
		mocker.thenReturn(messageToBeReturned);
		final MuleEvent resultMuleEvent = runFlow("myFlow", testEvent("example"));
		Assert.assertEquals("815", resultMuleEvent.getMessage().getPayload());
	}

	/**
	 * @throws Exception
	 */
	@org.junit.Test
	public void test() throws Exception {
		String myMockPayload = "myPayload";
		MuleMessage messageToBeReturned = muleMessageWithPayload(myMockPayload);
		MessageProcessorMocker mocker = whenMessageProcessor("set-payload");
		mocker.thenReturn(messageToBeReturned);
		MuleEvent resultMuleEvent = runFlow("myFlow", testEvent("example"));
		Assert.assertEquals(myMockPayload, resultMuleEvent.getMessage().getPayload());
	}

	/**
	 * @throws Exception
	 */
	
	@org.junit.Test
	public void SpyTest() throws Exception {
		SpyProcess beforeSpy = new SpyProcess() {

			@Override
			public void spy(MuleEvent event) throws MuleException {
				Assert.assertEquals(1, event.getMessage().getPayload());
			}
		};
		SpyProcess afterSpy = new SpyProcess() {

			@Override
			public void spy(MuleEvent event) throws MuleException {
				Assert.assertEquals(2, event.getMessage().getPayload());
			}
		};
		spyMessageProcessor("set-payload").ofNamespace("mule").before(beforeSpy).after(afterSpy);

		runFlow("myFlow", testEvent(1));
	}

	/**
	 * @throws Exception
	 */
	@org.junit.Test
	public void VerifyTest() throws Exception {
		runFlow("choiceFlow", testEvent(1));
		verifyCallOfMessageProcessor("set-payload").ofNamespace("mule").times(1);
	}

}
