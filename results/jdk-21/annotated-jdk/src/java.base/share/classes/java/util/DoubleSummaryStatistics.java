/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.DoubleConsumer;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;

@AnnotatedFor({ "lock", "nullness" })
public class DoubleSummaryStatistics implements DoubleConsumer {

    public DoubleSummaryStatistics() {
    }

    public DoubleSummaryStatistics(long count, double min, double max, double sum) throws IllegalArgumentException {
    }

    @Override
    public void accept(double value);

    public void combine(DoubleSummaryStatistics other);

    public final long getCount(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getSum(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getMin(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getMax(@GuardSatisfied DoubleSummaryStatistics this);

    public final double getAverage(@GuardSatisfied DoubleSummaryStatistics this);

    @Override
    public String toString();
}
