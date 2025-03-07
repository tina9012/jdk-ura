package org.checkerframework.checker.test.junit;

import org.checkerframework.checker.resourceleak.ResourceLeakChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

/** Tests for the Resource Leak Checker. */
public class ResourceLeakNoLightweightOwnershipTest extends CheckerFrameworkPerDirectoryTest {
    public ResourceLeakNoLightweightOwnershipTest(List<File> testFiles) {
        super(
                testFiles,
                ResourceLeakChecker.class,
                "resourceleak-nolightweightownership",
                "-AnoLightweightOwnership",
                "-nowarn",
                "-encoding",
                "UTF-8");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"resourceleak-nolightweightownership"};
    }
}
