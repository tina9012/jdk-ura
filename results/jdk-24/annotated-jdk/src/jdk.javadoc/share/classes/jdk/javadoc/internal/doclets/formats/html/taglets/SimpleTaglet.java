/*
 * Copyright (c) 2001, 2024, Oracle and/or its affiliates. All rights reserved.
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
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import com.sun.source.doctree.BlockTagTree;
import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.internal.doclets.formats.html.HtmlConfiguration;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.ContentBuilder;
import jdk.javadoc.internal.html.HtmlTree;
import jdk.javadoc.internal.html.RawHtml;

public class SimpleTaglet extends BaseTaglet implements InheritableTaglet {

    protected SimpleTaglet(HtmlConfiguration config, DocTree.Kind tagKind, String header, Set<Taglet.Location> locations, boolean enabled) {
    }

    static SimpleTaglet createWithDefaultForNested(HtmlConfiguration config, DocTree.Kind tagKind, String header, Set<Taglet.Location> locations, boolean enabled);

    @Override
    public Output inherit(Element dst, Element src, DocTree tag, boolean isFirstSentence);

    boolean isEnabled();

    record Documentation(DocTree tag, List<? extends DocTree> description, ExecutableElement method) {
    }

    @Override
    public Content getAllBlockTagOutput(Element holder, TagletWriter tagletWriter);

    protected List<? extends BlockTagTree> getDefaultBlockTags(Element e, Predicate<? super BlockTagTree> accepts);
}
