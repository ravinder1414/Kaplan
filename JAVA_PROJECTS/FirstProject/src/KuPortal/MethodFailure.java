package KuPortal;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public  class MethodFailure implements ITestListener {
	
	public int invocationCount=1;
	public String sLastMethodCalled = null,sCurrentMethodCalled = null;
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		sCurrentMethodCalled = result.getMethod().getMethodName();		
				
		if(sCurrentMethodCalled == sLastMethodCalled)
		{
			invocationCount++;
			if(invocationCount == 3)
			{
				invocationCount = 0;
				Reporter.log(result.getMethod().getMethodName() + " Send Mail");
				Reporter.log(result.getThrowable().getMessage() + " Send Mail");
			}
		}		
		sLastMethodCalled = sCurrentMethodCalled;				
	}

	@Override
	public void onFinish(ITestContext arg0) {
		
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
