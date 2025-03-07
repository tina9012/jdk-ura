package org.checkerframework.framework.test.junit;

import org.checkerframework.common.value.ValueChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

/** Tests conservative defaults for the constant value propagation type system. */
public class ValueUncheckedDefaultsTest extends CheckerFrameworkPerDirectoryTest {

    /**
     * @param testFiles the files containing test code, which will be type-checked
     */
    public ValueUncheckedDefaultsTest(List<File> testFiles) {
        super(
                testFiles,
                ValueChecker.class,
                "value",
                // Ignore the test suite's usage of qualifiers in illegal locations.
                "-AignoreTargetLocations",
                "-AuseConservativeDefaultsForUncheckedCode=btyecode",
                "-A" + ValueChecker.REPORT_EVAL_WARNS);
    }

    @Parameters
    public static String[] getTestDirs() {
        // The defaults for unchecked code should be the same as checked code, so use the same
        // tests.
        return new String[] {"value", "all-systems"};
    }
}
