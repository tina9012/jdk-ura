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
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.DocumentationTool;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.StandardDoclet;
import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.internal.Versions;
import jdk.javadoc.internal.doclets.formats.html.taglets.TagletManager;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.BaseOptions;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.Messages;
import jdk.javadoc.internal.doclets.toolkit.Resources;
import jdk.javadoc.internal.doclets.toolkit.util.DeprecatedAPIListBuilder;
import jdk.javadoc.internal.doclets.toolkit.util.DocFile;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;
import jdk.javadoc.internal.doclets.toolkit.util.NewAPIBuilder;
import jdk.javadoc.internal.doclets.toolkit.util.PreviewAPIListBuilder;
import jdk.javadoc.internal.doclets.toolkit.util.RestrictedAPIListBuilder;
import jdk.javadoc.internal.doclets.toolkit.util.SimpleDocletException;

public class HtmlConfiguration extends BaseConfiguration {

    public static final String HTML_DEFAULT_CHARSET;

    public final Resources docResources;

    public DocPath topFile;

    public TypeElement currentTypeElement;

    public HtmlIndexBuilder indexBuilder;

    protected DeprecatedAPIListBuilder deprecatedAPIListBuilder;

    protected PreviewAPIListBuilder previewAPIListBuilder;

    protected NewAPIBuilder newAPIPageBuilder;

    protected RestrictedAPIListBuilder restrictedAPIListBuilder;

    public Contents contents;

    public final Messages messages;

    public DocPaths docPaths;

    public HtmlIds htmlIds;

    public Map<Element, List<DocPath>> localStylesheetMap;

    public TagletManager tagletManager;

    public enum ConditionalPage {

        CONSTANT_VALUES,
        DEPRECATED,
        EXTERNAL_SPECS,
        PREVIEW,
        RESTRICTED,
        SERIALIZED_FORM,
        SYSTEM_PROPERTIES,
        NEW
    }

    public final Set<ConditionalPage> conditionalPages;

    public HtmlConfiguration(Doclet doclet, Locale locale, Reporter reporter) {
    }

    @Override
    protected void initConfiguration(DocletEnvironment docEnv, Function<String, String> resourceKeyMapper);

    @Override
    public Runtime.Version getDocletVersion();

    @Override
    public Resources getDocResources();

    public Contents getContents();

    @Override
    public Messages getMessages();

    @Override
    public HtmlOptions getOptions();

    public Set<PackageElement> getContainingPackagesSeen();

    @Override
    public boolean finishOptionSettings();

    public ZonedDateTime getBuildDate();

    protected void setTopFile();

    protected TypeElement getValidClass(List<TypeElement> classes);

    protected boolean checkForDeprecation();

    protected void setCreateOverview();

    public WriterFactory getWriterFactory();

    @Override
    public Locale getLocale();

    @Override
    public JavaFileObject getOverviewPath();

    public DocPath getMainStylesheet();

    public List<DocPath> getAdditionalStylesheets();

    public List<DocPath> getAdditionalScripts();

    @Override
    public JavaFileManager getFileManager();

    @Override
    protected boolean finishOptionSettings0() throws DocletException;
}
