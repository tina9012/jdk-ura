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
package jdk.javadoc.internal.doclets.formats.html.taglets;

import org.checkerframework.dataflow.qual.Pure;
import java.util.Set;
import javax.lang.model.element.Element;
import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.internal.doclets.formats.html.HtmlConfiguration;
import jdk.javadoc.internal.doclets.formats.html.Content;
import jdk.javadoc.internal.doclets.toolkit.Messages;
import jdk.javadoc.internal.doclets.toolkit.Resources;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;

public class BaseTaglet implements Taglet {

    protected final HtmlConfiguration config;

    protected final Messages messages;

    protected final Resources resources;

    protected final Utils utils;

    protected final DocTree.Kind tagKind;

    protected final String name;

    protected TagletWriter tagletWriter;

    public BaseTaglet(HtmlConfiguration config, DocTree.Kind tagKind, boolean inline, Set<Location> sites) {
    }

    protected BaseTaglet(HtmlConfiguration config, String name, boolean inline, Set<Location> sites) {
    }

    @Override
    public Set<Location> getAllowedLocations();

    @Override
    @Pure
    public final boolean isInlineTag();

    @Override
    public String getName();

    public DocTree.Kind getTagKind();

    @Override
    public Content getInlineTagOutput(Element element, DocTree tag, TagletWriter tagletWriter);

    @Override
    public Content getAllBlockTagOutput(Element holder, TagletWriter tagletWriter);
}
