/*
 * Copyright (c) 2003, 2024, Oracle and/or its affiliates. All rights reserved.
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
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;
import jdk.javadoc.internal.html.Content;
import jdk.javadoc.internal.html.ContentBuilder;
import jdk.javadoc.internal.html.HtmlStyle;
import jdk.javadoc.internal.html.Text;

public class HtmlLinkInfo {

    public enum Kind {

        PLAIN,
        SHOW_PREVIEW,
        SHOW_TYPE_PARAMS,
        SHOW_TYPE_PARAMS_IN_LABEL,
        SHOW_TYPE_PARAMS_AND_BOUNDS,
        LINK_TYPE_PARAMS,
        LINK_TYPE_PARAMS_AND_BOUNDS
    }

    public HtmlLinkInfo(HtmlConfiguration configuration, Kind context, ExecutableElement ee) {
    }

    public HtmlLinkInfo(HtmlConfiguration configuration, Kind context, TypeElement typeElement) {
    }

    public HtmlLinkInfo(HtmlConfiguration configuration, Kind context, TypeMirror type) {
    }

    public HtmlLinkInfo forType(TypeMirror type);

    public void setTypeElement(TypeElement typeElement);

    public TypeElement getTypeElement();

    public ExecutableElement getExecutableElement();

    public TypeMirror getType();

    public HtmlLinkInfo label(CharSequence label);

    public HtmlLinkInfo label(Content label);

    public Content getLabel();

    public HtmlLinkInfo style(HtmlStyle style);

    public HtmlStyle getStyle();

    public HtmlLinkInfo varargs(boolean varargs);

    public boolean isVarArg();

    public HtmlLinkInfo fragment(String fragment);

    public String getFragment();

    public HtmlLinkInfo addLineBreaksInTypeParameters(boolean addLineBreaksInTypeParameters);

    public boolean addLineBreaksInTypeParameters();

    public HtmlLinkInfo addLineBreakOpportunitiesInTypeParameters(boolean addLineBreakOpportunities);

    public boolean addLineBreakOpportunitiesInTypeParameters();

    public HtmlLinkInfo linkToSelf(boolean linkToSelf);

    public boolean linkToSelf();

    public boolean linkTypeParameters();

    public void showTypeBounds(boolean showTypeBounds);

    public boolean showTypeBounds();

    public HtmlLinkInfo showTypeParameterAnnotations(boolean showTypeParameterAnnotations);

    public boolean showTypeParameterAnnotations();

    public HtmlLinkInfo targetMember(Element el);

    public Element getTargetMember();

    public HtmlLinkInfo skipPreview(boolean skipPreview);

    public boolean isSkipPreview();

    public Kind getContext();

    @Pure
    public boolean isLinkable();

    public boolean showTypeParameters();

    public Content getClassLinkLabel(BaseConfiguration configuration);

    protected Content newContent();

    @Override
    public String toString();
}
