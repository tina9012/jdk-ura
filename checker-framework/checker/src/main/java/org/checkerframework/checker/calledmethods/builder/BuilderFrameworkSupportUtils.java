package org.checkerframework.checker.calledmethods.builder;

import javax.lang.model.type.TypeMirror;

/** A utility class of static methods used in supporting builder-generation frameworks. */
public class BuilderFrameworkSupportUtils {
    /** This class is non-instantiable. */
    private BuilderFrameworkSupportUtils() {
        throw new Error("Do not instantiate");
    }

    /**
     * Returns true if the given type is one of the immutable collections defined in
     * com.google.common.collect.
     *
     * @param type a type
     * @return true if the type is a Guava immutable collection
     */
    public static boolean isGuavaImmutableType(TypeMirror type) {
        // Use concatenation to avoid ShadowJar relocate
        // "com.google.common.collect.Immutable"
        return type.toString().startsWith("com.go".toString() + "ogle.common.collect.Immutable");
    }

    /**
     * Capitalizes the first letter of the given string.
     *
     * @param prop a String
     * @return the same String, but with the first character capitalized
     */
    public static String capitalize(String prop) {
        return Character.toUpperCase(prop.charAt(0)) + prop.substring(1);
    }
}
