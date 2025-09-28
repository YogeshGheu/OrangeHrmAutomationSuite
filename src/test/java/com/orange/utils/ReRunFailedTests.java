package com.orange.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReRunFailedTests implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = 3; // number of retries

    public boolean retry(ITestResult result){
        if(retryCount < maxRetryCount){
            retryCount ++;
            System.out.println(String.format(
                    "Test '%s' failed! Retrying... Attempt %d of %d",
                    result.getMethod().getMethodName(),
                    retryCount,
                    maxRetryCount
            ));

            return true;

        }
        return false;
    }
}
