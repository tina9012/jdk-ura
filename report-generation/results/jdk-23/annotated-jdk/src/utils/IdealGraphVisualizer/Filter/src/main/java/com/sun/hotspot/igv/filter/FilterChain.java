/*
 * Copyright (c) 2008, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
 *
 */
package com.sun.hotspot.igv.filter;

import org.checkerframework.dataflow.qual.Pure;
import com.sun.hotspot.igv.data.ChangedEvent;
import com.sun.hotspot.igv.data.ChangedEventProvider;
import com.sun.hotspot.igv.data.ChangedListener;
import com.sun.hotspot.igv.graph.Diagram;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilterChain implements ChangedEventProvider<FilterChain> {

    public FilterChain(String name) {
    }

    public FilterChain() {
    }

    public void sortBy(List<String> order);

    @Override
    public ChangedEvent<FilterChain> getChangedEvent();

    public void applyInOrder(Diagram diagram, FilterChain filterOrder);

    public void addFilter(Filter filter);

    @Pure
    public boolean containsFilter(Filter filter);

    public void clearFilters();

    public void removeFilter(Filter filter);

    public void moveFilterUp(Filter filter);

    public void moveFilterDown(Filter filter);

    public void addFilters(List<Filter> filtersToAdd);

    public List<Filter> getFilters();

    public String getName();

    @Override
    public String toString();
}
