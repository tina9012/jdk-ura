package org.checkerframework.framework.test.junit;

import org.checkerframework.framework.test.CheckerFrameworkPerDirectoryTest;
import org.checkerframework.framework.testchecker.util.Encrypted;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.List;

/** Test suite for the Subtyping Checker, using a simple {@link Encrypted} annotation. */
public class SubtypingEncryptedTest extends CheckerFrameworkPerDirectoryTest {

    /**
     * @param testFiles the files containing test code, which will be type-checked
     */
    public SubtypingEncryptedTest(List<File> testFiles) {
        super(
                testFiles,
                org.checkerframework.common.subtyping.SubtypingChecker.class,
                "subtyping",
                "-Aquals=org.checkerframework.framework.testchecker.util.Encrypted,org.checkerframework.framework.testchecker.util.PolyEncrypted,org.checkerframework.common.subtyping.qual.Unqualified");
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[] {"subtyping", "all-systems"};
    }
}
