package org.checkerframework.checker.test.junit;

import org.checkerframework.checker.calledmethods.CalledMethodsChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

/** Test that the Called Methods Checker's support for Lombok works correctly. */
public class CalledMethodsLombokTest extends CheckerFrameworkPerDirectoryTest {
    public CalledMethodsLombokTest(List<File> testFiles) {
        super(
                testFiles,
                CalledMethodsChecker.class,
                "calledmethods-delomboked",
                "-nowarn",
                "-AsuppressWarnings=type.anno.before.modifier");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"calledmethods-delomboked"};
    }
}
