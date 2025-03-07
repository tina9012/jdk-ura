package org.checkerframework.checker.test.junit;

import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

public class I18nTest extends CheckerFrameworkPerDirectoryTest {

    /**
     * Create an I18nTest.
     *
     * @param testFiles the files containing test code, which will be type-checked
     */
    public I18nTest(List<File> testFiles) {
        super(
                testFiles,
                org.checkerframework.checker.i18n.I18nChecker.class,
                "i18n",
                // Ignore the test suite's usage of qualifiers in illegal locations.
                "-AignoreTargetLocations");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"i18n", "all-systems"};
    }
}
