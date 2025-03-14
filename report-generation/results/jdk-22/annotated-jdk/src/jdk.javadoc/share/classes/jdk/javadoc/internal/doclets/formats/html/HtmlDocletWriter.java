/*
 * Copyright (c) 1998, 2023, Oracle and/or its affiliates. All rights reserved.
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
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor9;
import javax.lang.model.util.SimpleElementVisitor14;
import javax.lang.model.util.SimpleTypeVisitor9;
import com.sun.source.doctree.AttributeTree;
import com.sun.source.doctree.AttributeTree.ValueKind;
import com.sun.source.doctree.CommentTree;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocRootTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.DocTree.Kind;
import com.sun.source.doctree.EndElementTree;
import com.sun.source.doctree.EntityTree;
import com.sun.source.doctree.ErroneousTree;
import com.sun.source.doctree.EscapeTree;
import com.sun.source.doctree.InheritDocTree;
import com.sun.source.doctree.InlineTagTree;
import com.sun.source.doctree.LinkTree;
import com.sun.source.doctree.LiteralTree;
import com.sun.source.doctree.StartElementTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.util.DocTreePath;
import com.sun.source.util.SimpleDocTreeVisitor;
import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.Entity;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlDocument;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlId;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.markup.Links;
import jdk.javadoc.internal.doclets.formats.html.markup.RawHtml;
import jdk.javadoc.internal.doclets.formats.html.markup.Script;
import jdk.javadoc.internal.doclets.formats.html.markup.TagName;
import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import jdk.javadoc.internal.doclets.formats.html.markup.TextBuilder;
import jdk.javadoc.internal.doclets.formats.html.taglets.Taglet;
import jdk.javadoc.internal.doclets.formats.html.taglets.TagletWriter;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.Messages;
import jdk.javadoc.internal.doclets.toolkit.Resources;
import jdk.javadoc.internal.doclets.toolkit.util.CommentHelper;
import jdk.javadoc.internal.doclets.toolkit.util.Comparators;
import jdk.javadoc.internal.doclets.toolkit.util.DocFile;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocLink;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;
import jdk.javadoc.internal.doclets.toolkit.util.IndexItem;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;
import jdk.javadoc.internal.doclets.toolkit.util.Utils.DeclarationPreviewLanguageFeatures;
import jdk.javadoc.internal.doclets.toolkit.util.Utils.ElementFlag;
import jdk.javadoc.internal.doclets.toolkit.util.Utils.PreviewSummary;
import jdk.javadoc.internal.doclint.HtmlTag;
import static com.sun.source.doctree.DocTree.Kind.COMMENT;
import static com.sun.source.doctree.DocTree.Kind.TEXT;

public abstract class HtmlDocletWriter {

    public final DocPath pathToRoot;

    public final DocPath path;

    public final HtmlConfiguration configuration;

    protected final HtmlOptions options;

    protected final Utils utils;

    protected final Contents contents;

    public final Messages messages;

    protected final Resources resources;

    public final Links links;

    protected final DocPaths docPaths;

    protected final Comparators comparators;

    protected final HtmlIds htmlIds;

    protected String winTitle;

    protected Script mainBodyScript;

    public final Map<String, Integer> indexAnchorTable;

    public HtmlDocletWriter(HtmlConfiguration configuration, DocPath path) {
    }

    protected HtmlDocletWriter(HtmlConfiguration configuration, DocPath path, boolean generating) {
    }

    public abstract void buildPage() throws DocletException;

    protected final void writeGenerating();

    public String replaceDocRootDir(String htmlstr);

    protected void addTagsInfo(Element e, Content content);

    protected Content getBlockTagOutput(Element element);

    protected Content getBlockTagOutput(Element element, List<Taglet> taglets);

    protected boolean hasSerializationOverviewTags(VariableElement field);

    public TagletWriter getTagletWriterInstance(boolean isFirstSentence);

    public TagletWriter getTagletWriterInstance(TagletWriter.Context context);

    public boolean isIndexable();

    public void printHtmlDocument(List<String> metakeywords, String description, Content body) throws DocFileIOException;

    public void printHtmlDocument(List<String> metakeywords, String description, List<DocPath> localStylesheets, Content body) throws DocFileIOException;

    public void printHtmlDocument(List<String> metakeywords, String description, Content extraHeadContent, List<DocPath> localStylesheets, Content body) throws DocFileIOException;

    public String getWindowTitle(String title);

    protected Content getHeader(Navigation.PageMode pageMode);

    protected Content getHeader(Navigation.PageMode pageMode, Element element);

    protected Navigation getNavBar(Navigation.PageMode pageMode, Element element);

    public HtmlTree getFooter();

    protected Content getNavLinkToOverviewTree(String label);

    public Content getLocalizedPackageName(PackageElement packageElement);

    public Content getPackageLabel(CharSequence packageName);

    protected DocPath pathString(TypeElement te, DocPath name);

    protected DocPath pathString(PackageElement packageElement, DocPath name);

    public Content getPackageLink(PackageElement packageElement, Content label);

    public Content getPackageLink(PackageElement packageElement, Content label, String fragment);

    public Content getModuleLink(ModuleElement mdle, Content label);

    public Content getModuleLink(ModuleElement mdle, Content label, String fragment);

    public void addSrcLink(Element element, Content label, Content target);

    public Content getLink(HtmlLinkInfo linkInfo);

    public Content getTypeParameterLinks(HtmlLinkInfo linkInfo);

    public Content getCrossClassLink(TypeElement classElement, String refMemName, Content label, HtmlStyle style, boolean code);

    public DocLink getCrossPackageLink(PackageElement element);

    public DocLink getCrossModuleLink(ModuleElement element);

    public Content getQualifiedClassLink(HtmlLinkInfo.Kind context, Element element);

    public void addPreQualifiedClassLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Content target);

    public Content getPreQualifiedClassLink(HtmlLinkInfo.Kind context, TypeElement typeElement);

    public void addPreQualifiedClassLink(HtmlLinkInfo.Kind context, TypeElement typeElement, HtmlStyle style, Content target);

    public String getEnclosingPackageName(TypeElement te);

    public TypeElement getCurrentPageElement();

    public void addPreQualifiedStrongClassLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Content content);

    public Content getDocLink(HtmlLinkInfo.Kind context, Element element, CharSequence label);

    public Content getDocLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Element element, CharSequence label);

    public Content getDocLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Element element, CharSequence label, HtmlStyle style);

    public Content getDocLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Element element, CharSequence label, boolean isProperty);

    public Content getDocLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Element element, Content label, HtmlStyle style, boolean isProperty);

    public void addInlineComment(Element element, DocTree tag, Content target);

    public Content getDeprecatedPhrase(Element e);

    public void addInlineDeprecatedComment(Element e, DeprecatedTree tag, Content target);

    public void addSummaryComment(Element element, Content target);

    public void addPreviewComment(Element element, List<? extends DocTree> firstSentenceTags, Content target);

    public void addSummaryComment(Element element, List<? extends DocTree> firstSentenceTags, Content target);

    public void addSummaryDeprecatedComment(Element element, DeprecatedTree tag, Content target);

    public void addInlineComment(Element element, Content target);

    boolean ignoreNonInlineTag(DocTree dtree);

    public Content commentTagsToContent(Element element, List<? extends DocTree> tags, boolean isFirstSentence);

    public Content commentTagsToContent(Element element, List<? extends DocTree> trees, boolean isFirstSentence, boolean inSummary);

    public Content commentTagsToContent(Element element, List<? extends DocTree> trees, TagletWriter.Context context);

    public Content invalidTagOutput(String summary, Optional<Content> detail);

    Content getAnnotationInfo(Element element, boolean lineBreak);

    Content getAnnotationInfo(List<? extends AnnotationMirror> descList, boolean lineBreak);

    public List<Content> getAnnotations(List<? extends AnnotationMirror> descList, boolean lineBreak);

    protected TableHeader getPackageTableHeader();

    static String getDescription(String prefix, Element elem);

    static String getGenerator(Class<?> clazz);

    public HtmlTree getBody(String title);

    public HtmlStyle getBodyStyle();

    List<DocPath> getLocalStylesheets(Element element) throws DocFileIOException;

    public void addPreviewSummary(Element forWhat, Content target);

    public void addRestrictedSummary(Element forWhat, Content target);

    public void addPreviewInfo(Element forWhat, Content target);

    public URI resolveExternalSpecURI(URI specURI);

    public void addRestrictedInfo(ExecutableElement forWhat, Content target);
}
