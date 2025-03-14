/*
 * Copyright (c) 2003, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.util;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.mustcall.qual.MustCallAlias;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandle;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.spi.NumberFormatProvider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.UnsupportedTemporalTypeException;
import jdk.internal.javac.PreviewFeature;
import jdk.internal.math.DoubleConsts;
import jdk.internal.math.FormattedFPDecimal;
import sun.util.locale.provider.LocaleProviderAdapter;
import sun.util.locale.provider.ResourceBundleBasedAdapter;

@AnnotatedFor({ "formatter", "index", "lock", "mustcall", "nullness" })
public final class Formatter implements Closeable, Flushable {

    public Formatter() {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias @Nullable Appendable a) {
    }

    public Formatter(@Nullable Locale l) {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias @Nullable Appendable a, @Nullable Locale l) {
    }

    public Formatter(String fileName) throws FileNotFoundException {
    }

    public Formatter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(String fileName, String csn, @Nullable Locale l) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(String fileName, Charset charset, @Nullable Locale l) throws IOException {
    }

    public Formatter(File file) throws FileNotFoundException {
    }

    public Formatter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(File file, String csn, @Nullable Locale l) throws FileNotFoundException, UnsupportedEncodingException {
    }

    public Formatter(File file, Charset charset, @Nullable Locale l) throws IOException {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias PrintStream ps) {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias OutputStream os) {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias OutputStream os, String csn) throws UnsupportedEncodingException {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias OutputStream os, String csn, @Nullable Locale l) throws UnsupportedEncodingException {
    }

    @MustCallAlias
    public Formatter(@MustCallAlias OutputStream os, Charset charset, @Nullable Locale l) {
    }

    @Nullable
    public Locale locale();

    @MustCallAlias
    public Appendable out(@MustCallAlias Formatter this);

    public String toString();

    public void flush();

    public void close();

    @Nullable
    public IOException ioException();

    @FormatMethod
    @MustCallAlias
    public Formatter format(@MustCallAlias Formatter this, String format, @Nullable Object... args);

    @FormatMethod
    @MustCallAlias
    public Formatter format(@MustCallAlias Formatter this, @Nullable Locale l, String format, @Nullable Object... args);

    static List<FormatString> parse(String s);

    interface FormatString {

        int index();

        void print(Formatter fmt, Object arg, Locale l) throws IOException;

        String toString();
    }

    private static class FixedString implements FormatString {

        public int index();

        public void print(Formatter fmt, Object arg, Locale l) throws IOException;

        public String toString();
    }

    public enum BigDecimalLayoutForm {

        SCIENTIFIC, DECIMAL_FLOAT
    }

    static class FormatSpecifier implements FormatString {

        public int index();

        public void print(Formatter fmt, Object arg, Locale l) throws IOException;

        public String toString();

        private class BigDecimalLayout {

            public BigDecimalLayout(BigInteger intVal, int scale, BigDecimalLayoutForm form) {
            }

            public boolean hasDot();

            public int scale();

            public StringBuilder mantissa();

            public StringBuilder exponent();
        }
    }

    static class Flags {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public static boolean contains(int flags, int f);

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public static boolean containsAny(int flags, int f);

        public static int remove(int flags, int f);

        public static int parse(String s, int start, int end);

        public static String toString(int f);
    }

    static class Conversion {

        static boolean isValid(char c);

        static boolean isGeneral(char c);

        static boolean isCharacter(char c);

        static boolean isInteger(char c);

        static boolean isFloat(char c);

        static boolean isText(char c);
    }

    static class DateTime {

        static boolean isValid(char c);
    }
}
