/*
 * Copyright (c) 2005, 2021, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.IOException;
import java.io.ObjectInputStream;
import sun.util.locale.provider.CalendarDataUtility;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Era;
import sun.util.calendar.Gregorian;
import sun.util.calendar.LocalGregorianCalendar;
import sun.util.calendar.ZoneInfo;

class JapaneseImperialCalendar extends Calendar {

    public static final int BEFORE_MEIJI;

    public static final int MEIJI;

    public static final int TAISHO;

    public static final int SHOWA;

    public static final int HEISEI;

    @Override
    public String getCalendarType();

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    @Override
    public int hashCode();

    @Override
    public void add(int field, int amount);

    @Override
    public void roll(int field, boolean up);

    @Override
    public void roll(int field, int amount);

    @Override
    public String getDisplayName(int field, int style, Locale locale);

    @Override
    public Map<String, Integer> getDisplayNames(int field, int style, Locale locale);

    public int getMinimum(int field);

    public int getMaximum(int field);

    public int getGreatestMinimum(int field);

    public int getLeastMaximum(int field);

    public int getActualMinimum(int field);

    public int getActualMaximum(int field);

    public Object clone();

    public TimeZone getTimeZone();

    public void setTimeZone(TimeZone zone);

    protected void computeFields();

    protected void computeTime();
}
