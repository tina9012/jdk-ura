/*
 * Copyright (c) 2010, 2022, Oracle and/or its affiliates. All rights reserved.
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
package sun.util.locale;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import jdk.internal.misc.CDS;
import jdk.internal.vm.annotation.Stable;
import sun.security.action.GetPropertyAction;
import java.lang.ref.SoftReference;
import java.util.StringJoiner;

public final class BaseLocale {

    @Stable
    public static BaseLocale[] constantBaseLocales;

    public static final byte ENGLISH, FRENCH, GERMAN, ITALIAN, JAPANESE, KOREAN, CHINESE, SIMPLIFIED_CHINESE, TRADITIONAL_CHINESE, FRANCE, GERMANY, ITALY, JAPAN, KOREA, UK, US, CANADA, CANADA_FRENCH, ROOT, NUM_CONSTANTS;

    public static final String SEP;

    public static BaseLocale getInstance(String language, String script, String region, String variant);

    public static String convertOldISOCodes(String language);

    public String getLanguage();

    public String getScript();

    public String getRegion();

    public String getVariant();

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    @Override
    public String toString();

    @Override
    public int hashCode();

    private static final class Key {

        public int hashCode();

        @Override
        public boolean equals(Object obj);

        public static Key normalize(Key key);
    }

    private static class Cache extends LocaleObjectCache<Key, BaseLocale> {

        public Cache() {
        }

        @Override
        protected Key normalizeKey(Key key);

        @Override
        protected BaseLocale createObject(Key key);
    }
}
