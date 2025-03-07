package org.checkerframework.checker.i18nformatter.qual;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@AnnotatedFor({ "initialization", "nullness" })
public enum I18nConversionCategory {

    UNUSED(null, null), GENERAL(null, null), DATE(new Class<?>[] { Date.class, Number.class }, new String[] { "date", "time" }), NUMBER(new Class<?>[] { Number.class }, new String[] { "number", "choice" });

    @SuppressWarnings("ImmutableEnumChecker")
    public final Class<?> @Nullable [] types;

    @SuppressWarnings("ImmutableEnumChecker")
    public final String @Nullable [] strings;

    @SuppressWarnings("nullness:iterating.over.nullable")
    public static I18nConversionCategory stringToI18nConversionCategory(String string);

    public static boolean isSubsetOf(I18nConversionCategory a, I18nConversionCategory b);

    public static I18nConversionCategory intersect(I18nConversionCategory a, I18nConversionCategory b);

    public static I18nConversionCategory union(I18nConversionCategory a, I18nConversionCategory b);

    public boolean isAssignableFrom(Class<?> argType);

    @Override
    public String toString();
}
