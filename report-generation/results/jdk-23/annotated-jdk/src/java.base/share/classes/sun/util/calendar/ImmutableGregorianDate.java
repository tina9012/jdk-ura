/*
 * Copyright (c) 2005, 2023, Oracle and/or its affiliates. All rights reserved.
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
package sun.util.calendar;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.util.Objects;
import java.util.TimeZone;

final class ImmutableGregorianDate extends BaseCalendar.Date {

    @Override
    public Era getEra();

    @Override
    public CalendarDate setEra(Era era);

    @Override
    public int getYear();

    @Override
    public CalendarDate setYear(int year);

    @Override
    public CalendarDate addYear(int n);

    @Override
    public boolean isLeapYear();

    @Override
    void setLeapYear(boolean leapYear);

    @Override
    public int getMonth();

    @Override
    public CalendarDate setMonth(int month);

    @Override
    public CalendarDate addMonth(int n);

    @Override
    public int getDayOfMonth();

    @Override
    public CalendarDate setDayOfMonth(int date);

    @Override
    public int getDayOfWeek();

    @Override
    public int getHours();

    @Override
    public CalendarDate setHours(int hours);

    @Override
    public CalendarDate addHours(int n);

    @Override
    public int getMinutes();

    @Override
    public CalendarDate setMinutes(int minutes);

    @Override
    public int getSeconds();

    @Override
    public CalendarDate setSeconds(int seconds);

    @Override
    public int getMillis();

    @Override
    public CalendarDate setMillis(int millis);

    @Override
    public long getTimeOfDay();

    @Override
    public CalendarDate setDate(int year, int month, int dayOfMonth);

    @Override
    public CalendarDate setTimeOfDay(int hours, int minutes, int seconds, int millis);

    @Override
    protected void setTimeOfDay(long fraction);

    @Override
    public boolean isNormalized();

    @Override
    public boolean isDaylightTime();

    @Override
    public TimeZone getZone();

    @Override
    public CalendarDate setZone(TimeZone zoneinfo);

    @Override
    public boolean isSameDate(CalendarDate date);

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    @Override
    public int hashCode();

    @Override
    public Object clone();

    @Override
    public String toString();

    @Override
    protected void setDayOfWeek(int dayOfWeek);

    @Override
    protected void setNormalized(boolean normalized);

    @Override
    public int getZoneOffset();

    @Override
    protected void setZoneOffset(int offset);

    @Override
    public int getDaylightSaving();

    @Override
    protected void setDaylightSaving(int daylightSaving);

    @Override
    public int getNormalizedYear();

    @Override
    public void setNormalizedYear(int normalizedYear);
}
