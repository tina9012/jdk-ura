/*
 * Copyright (c) 2016, 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.javadoc.internal.doclets.formats.html;

import org.checkerframework.dataflow.qual.Pure;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.lang.model.element.Element;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocTree;
import java.util.function.Predicate;
import jdk.javadoc.doclet.DocletEnvironment.ModuleMode;
import jdk.javadoc.internal.doclets.formats.html.Navigation.PageMode;
import jdk.javadoc.internal.doclets.formats.html.markup.BodyContents;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.util.CommentHelper;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.ContentBuilder;
import jdk.javadoc.internal.html.Entity;
import jdk.javadoc.internal.html.HtmlId;
import jdk.javadoc.internal.html.HtmlStyle;
import jdk.javadoc.internal.html.HtmlTree;
import jdk.javadoc.internal.html.RawHtml;
import jdk.javadoc.internal.html.Text;

public class ModuleWriter extends HtmlDocletWriter {

    protected ModuleElement mdle;

    static class PackageEntry {
    }

    public ModuleWriter(HtmlConfiguration configuration, ModuleElement mdle) {
    }

    @Override
    public void buildPage() throws DocletException;

    protected void buildModuleDoc() throws DocletException;

    protected void buildContent();

    protected void buildSummary(Content target);

    protected void buildModulesSummary(Content summariesList);

    protected void buildPackagesSummary(Content summariesList);

    protected void buildServicesSummary(Content summariesList);

    protected void buildModuleDescription(Content moduleContent);

    protected Content getModuleHeader(String heading);

    protected Content getContentHeader();

    protected Content getSummariesList();

    protected Content getSummary(Content source);

    public void computeModulesData();

    public boolean shouldDocument(Element element);

    public boolean display(Set<? extends Element> section);

    public boolean display(Map<? extends Element, ?> section);

    public void addSummaryHeader(Content startMarker, Content heading, Content target);

    protected void addModulesSummary(Content summariesList);

    protected void addPackagesSummary(Content summariesList);

    public void addPackageSummary(HtmlTree li);

    public void addIndirectPackages(Table<?> table, Map<ModuleElement, SortedSet<PackageElement>> ip, Predicate<ModuleElement> acceptModule);

    protected void addServicesSummary(Content summariesList);

    public void addUsesList(Table<?> table);

    public void addProvidesList(Table<?> table);

    public void addDeprecationInfo(Content div);

    protected void addModuleDescription(Content moduleContent);

    protected void addModuleSignature(Content moduleContent);

    protected void addModuleContent(Content source);

    protected void addModuleFooter();

    protected void printDocument(Content content) throws DocFileIOException;

    public void addPackageDeprecationInfo(Content li, PackageElement pkg);

    @Override
    public boolean isIndexable();
}
