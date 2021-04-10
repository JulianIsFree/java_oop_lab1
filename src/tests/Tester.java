package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    static void runTest(Class klass) {
        Result result = JUnitCore.runClasses(klass);
        System.out.println("Failed tests: " + result.getFailureCount());
        for(Failure failure : result.getFailures()) {
            failure.getException().printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Class> forTest = new ArrayList<Class>();

        forTest.add(TestWordStatCollector.class);

        for(Class klass : forTest) {
            runTest(klass);
        }
    }
}
