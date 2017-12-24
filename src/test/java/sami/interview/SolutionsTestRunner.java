package sami.interview;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sami on 22:05 - 30/03/17.
 */
public class SolutionsTestRunner {
    private static final Logger logger = LoggerFactory.getLogger(SolutionsTestRunner.class);

    public static void main(String[] args) {
        logger.info("Running jUnit tests...");
        List<Class> classList = new ArrayList<>();
        classList.add(Q1Test.class);
        classList.add(Q2Test.class);
        classList.add(Q3Test.class);
        classList.add(Q3OptimizeTest.class);

        Result result = JUnitCore.runClasses(classList.toArray(new Class<?>[classList.size()]));
        if (result.wasSuccessful()) {
            logger.info("All tests passed successfully. Tests ran for: {}ms", result.getRunTime());
            return;
        }
        result.getFailures().forEach(f -> logger.error("Error on: {}", f));
    }
}
