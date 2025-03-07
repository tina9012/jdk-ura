package org.checkerframework.checker.formatter.qual;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@SuppressWarnings("unchecked")
@AnnotatedFor({ "initialization", "nullness" })
public enum ConversionCategory {

    GENERAL("bBhHsS", (Class<?>[]) null),
    CHAR("cC", Character.class, Byte.class, Short.class, Integer.class),
    INT("doxX", Byte.class, Short.class, Integer.class, Long.class, BigInteger.class),
    FLOAT("eEfgGaA", Float.class, Double.class, BigDecimal.class),
    @SuppressWarnings("JdkObsolete")
    TIME("tT", Long.class, Calendar.class, Date.class),
    CHAR_AND_INT(null, Byte.class, Short.class, Integer.class),
    INT_AND_TIME(null, Long.class),
    NULL(null),
    UNUSED(null, (Class<?>[]) null);

    @SuppressWarnings("ImmutableEnumChecker")
    public final Class<?> @Nullable [] types;

    @Nullable
    public final String chars;

    @SuppressWarnings("nullness:dereference.of.nullable")
    public static ConversionCategory fromConversionChar(char c);

    public static boolean isSubsetOf(ConversionCategory a, ConversionCategory b);

    public static ConversionCategory intersect(ConversionCategory a, ConversionCategory b);

    public static ConversionCategory union(ConversionCategory a, ConversionCategory b);

    public boolean isAssignableFrom(Class<?> argType);

    @Pure
    @Override
    public String toString();
}
