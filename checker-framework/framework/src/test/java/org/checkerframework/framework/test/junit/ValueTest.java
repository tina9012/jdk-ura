package org.checkerframework.framework.test.junit;

import org.checkerframework.common.value.ValueChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.checkerframework.framework.test.TestUtilities;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

/**
 * Tests the constant value propagation type system.
 *
 * <p>NOTE: $CHECKERFRAMEWORK/framework/tests/value/ needs to be on the classpath. Otherwise
 * ExceptionTest will fail because it cannot find the ExceptionTest.class file for reflective method
 * resolution.
 */
public class ValueTest extends CheckerFrameworkPerDirectoryTest {

    /**
     * @param testFiles the files containing test code, which will be type-checked
     */
    public ValueTest(List<File> testFiles) {
        super(
                testFiles,
                org.checkerframework.common.value.ValueChecker.class,
                "value",
                // Ignore the test suite's usage of qualifiers in illegal locations.
                "-AignoreTargetLocations",
                TestUtilities.adapt(
                        "-Astubs=tests/value/minints-stub.astub:tests/value/lowercase.astub"),
                "-A" + ValueChecker.REPORT_EVAL_WARNS);
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"value", "all-systems"};
    }
}
