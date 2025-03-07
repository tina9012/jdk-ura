package org.checkerframework.checker.test.junit;

import org.checkerframework.checker.nullness.NullnessChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

/** JUnit tests for the Nullness Checker without the Initialization Checker. */
public class NullnessAssumeInitializedTest extends CheckerFrameworkPerDirectoryTest {

    /**
     * Create a NullnessAssumeInitializedTest.
     *
     * @param testFiles the files containing test code, which will be type-checked
     */
    public NullnessAssumeInitializedTest(List<File> testFiles) {
        // TODO: remove soundArrayCreationNullness option once it's no
        // longer needed.  See issue #986:
        // https://github.com/typetools/checker-framework/issues/986
        super(
                testFiles,
                org.checkerframework.checker.nullness.NullnessChecker.class,
                "nullness",
                "-AcheckPurityAnnotations",
                "-AassumeInitialized",
                "-AconservativeArgumentNullnessAfterInvocation=true",
                "-Xlint:deprecation",
                "-Alint=soundArrayCreationNullness,"
                        + NullnessChecker.LINT_REDUNDANTNULLCOMPARISON);
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"nullness", "nullness-assumeinitialized", "all-systems"};
    }
}
