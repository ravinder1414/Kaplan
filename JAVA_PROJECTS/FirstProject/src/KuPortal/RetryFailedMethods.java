package KuPortal;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedMethods implements IRetryAnalyzer {

	int retryCount = 0;
	int maxRetryCount = 2;
	@Override
	public boolean retry(ITestResult result) {
		
	    if (retryCount < maxRetryCount) {
	        System.out.println("Retry");	        
	        retryCount++;
	        return true;
	    }
	    return false;
	}

}
