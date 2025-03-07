package org.checkerframework.checker.test.junit;

import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

public class UnitsTest extends CheckerFrameworkPerDirectoryTest {

    /**
     * Create a UnitsTest.
     *
     * @param testFiles the files containing test code, which will be type-checked
     */
    public UnitsTest(List<File> testFiles) {
        super(testFiles, org.checkerframework.checker.units.UnitsChecker.class, "units");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"units", "all-systems"};
    }
}
