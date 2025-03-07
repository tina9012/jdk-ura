/*
 * Copyright (c) 1997, 2023, Oracle and/or its affiliates. All rights reserved.
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
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleElementVisitor8;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocTree;
import jdk.javadoc.internal.doclets.formats.html.Navigation.PageMode;
import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.Entity;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlAttr;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.markup.TagName;
import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import jdk.javadoc.internal.doclets.toolkit.CommentUtils;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.PropertyUtils;
import jdk.javadoc.internal.doclets.toolkit.util.ClassTree;
import jdk.javadoc.internal.doclets.toolkit.util.CommentHelper;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.VisibleMemberTable;

public class ClassWriter extends SubWriterHolderWriter {

    protected final TypeElement typeElement;

    protected final ClassTree classTree;

    protected final PropertyUtils.PropertyHelper pHelper;

    public ClassWriter(HtmlConfiguration configuration, TypeElement typeElement, ClassTree classTree) {
    }

    @Override
    public PropertyUtils.PropertyHelper getPropertyHelper();

    @Override
    public void buildPage() throws DocletException;

    protected void buildClassDoc() throws DocletException;

    protected void buildClassTree(Content classContent);

    protected void buildClassInfo(Content target);

    protected void buildParamInfo(Content target);

    protected void buildSuperInterfacesInfo(Content target);

    protected void buildImplementedInterfacesInfo(Content target);

    protected void buildSubClassInfo(Content target);

    protected void buildSubInterfacesInfo(Content target);

    protected void buildInterfaceUsageInfo(Content target);

    protected void buildFunctionalInterfaceInfo(Content target);

    protected void buildDeprecationInfo(Content target);

    protected void buildNestedClassInfo(Content target);

    protected void buildClassSignature(Content target);

    protected void buildClassDescription(Content target);

    protected void buildClassTagInfo(Content target);

    protected void buildMemberSummary(Content classContent);

    protected void buildMemberDetails(Content classContent);

    protected Content getHeader(String header);

    protected Content getClassContentHeader();

    @Override
    protected Navigation getNavBar(PageMode pageMode, Element element);

    protected void addFooter();

    protected void printDocument(Content content) throws DocFileIOException;

    protected Content getClassInfo(Content classInfo);

    @Override
    public TypeElement getCurrentPageElement();

    protected void addClassSignature(Content classInfo);

    protected void addClassDescription(Content classInfo);

    protected void addClassTagInfo(Content classInfo);

    protected void addClassTree(Content target);

    protected void addParamInfo(Content target);

    protected void addSubClassInfo(Content target);

    protected void addSubInterfacesInfo(Content target);

    protected void addInterfaceUsageInfo(Content target);

    protected void addImplementedInterfacesInfo(Content target);

    protected void addSuperInterfacesInfo(Content target);

    protected void addNestedClassInfo(final Content target);

    protected void addFunctionalInterfaceInfo(Content target);

    protected void addClassDeprecationInfo(Content classInfo);

    public TypeElement getTypeElement();

    protected Content getMemberDetails(Content content);

    @Override
    public boolean isIndexable();
}
