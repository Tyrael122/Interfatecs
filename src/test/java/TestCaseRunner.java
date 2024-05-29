import util.TestCaseReader;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCaseRunner {
    private final String TEST_CASE_FOLDER;

    public TestCaseRunner(String testCaseFolder) {
        TEST_CASE_FOLDER = testCaseFolder;
    }

    public double runNTimesAndAverageExecutionTime(int times, String folder, Function<TestCaseReader, String> runnable) {
        return runNTimesAndAverageExecutionTime(times, () -> new TestCaseRunner(TEST_CASE_FOLDER).runTestCasesFromFolder(folder, runnable));
    }

    public static double runNTimesAndAverageExecutionTime(int times, Supplier<Double> runnable) {
        double totalTime = 0;

        for (int i = 0; i < times; i++) {
            System.out.println("\n=====================================");
            System.out.println("Running test case " + (i + 1) + " of " + times);

            double executionTime = runnable.get();

            totalTime += executionTime;
        }

        double average = totalTime / times;

        System.out.println("\n=====================================");
        System.out.println("Total time: " + totalTime + " milliseconds");
        System.out.println("Average time: " + average + " milliseconds");

        return average;
    }

    public double runTestCasesFromFolder(String folder, Function<TestCaseReader, String> runnable) {
        File[] files = TestCaseReader.readInputFilesFromFolder(TEST_CASE_FOLDER + folder);

        double totalTime = 0;

        for (File file : files) {
            TestCaseReader reader = TestCaseReader.readTestCaseFile(file.getAbsolutePath());

            System.out.println("\n=====================================");
            System.out.println("Running test case: " + file.getName());

            long start = System.nanoTime();
            String result = runnable.apply(reader);
            long end = System.nanoTime();

            double seconds = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
            totalTime += seconds;

            System.out.println("Execution time: " + seconds + " milliseconds");

            String expectedOutput = TestCaseReader.getExpectedOutput(file.getParent(), file.getName());
            assertEquals(expectedOutput.trim(), result.trim());

            System.out.println("Test case " + file.getName() + " passed!");
        }

        System.out.println("\n=====================================");
        System.out.println("Total time: " + totalTime + " milliseconds");

        return totalTime;
    }
}
