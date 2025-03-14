/*
 * Copyright (c) 2018, 2023, Oracle and/or its affiliates. All rights reserved.
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
package jdk.javadoc.internal.doclets.toolkit.util;

import org.checkerframework.dataflow.qual.Pure;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleTypeVisitor14;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.BaseOptions;
import jdk.javadoc.internal.doclets.toolkit.PropertyUtils;

public class VisibleMemberTable {

    public enum Kind {

        NESTED_CLASSES,
        ENUM_CONSTANTS,
        FIELDS,
        CONSTRUCTORS,
        METHODS,
        ANNOTATION_TYPE_MEMBER,
        ANNOTATION_TYPE_MEMBER_REQUIRED,
        ANNOTATION_TYPE_MEMBER_OPTIONAL,
        PROPERTIES;

        public static Set<Kind> forSummariesOf(ElementKind kind);

        public static Set<Kind> forDetailsOf(ElementKind kind);
    }

    protected VisibleMemberTable(TypeElement typeElement, BaseConfiguration configuration, VisibleMemberCache mcache) {
    }

    public List<Element> getAllVisibleMembers(Kind kind);

    public List<Element> getVisibleMembers(Kind kind, Predicate<Element> p);

    public List<Element> getVisibleMembers(Kind kind);

    public List<Element> getMembers(Kind kind);

    public ExecutableElement getOverriddenMethod(ExecutableElement e);

    public Set<TypeElement> getVisibleTypeElements();

    public boolean hasVisibleMembers();

    public boolean hasVisibleMembers(Kind kind);

    public VariableElement getPropertyField(ExecutableElement ee);

    public ExecutableElement getPropertyGetter(ExecutableElement ee);

    public ExecutableElement getPropertySetter(ExecutableElement ee);

    public ExecutableElement getPropertyMethod(ExecutableElement ee);

    void computeVisibleMembers(LocalMemberTable lmt, Kind kind);

    private class LocalMemberTable {

        void addMember(Element e, Kind kind);

        List<Element> getOrderedMembers(Kind kind);

        List<Element> getMembers(Name simpleName, Kind kind);

        <T extends Element> List<T> getMembers(Name simpleName, Kind kind, Class<T> clazz);

        List<ExecutableElement> getPropertyMethods(Name simpleName);
    }

    private record PropertyMembers(ExecutableElement propertyMethod, VariableElement field, ExecutableElement getter, ExecutableElement setter) {
    }

    public List<ExecutableElement> getImplementedMethods(ExecutableElement method);

    public TypeMirror getImplementedMethodHolder(ExecutableElement method, ExecutableElement implementedMethod);

    private class ImplementedMethods {

        public ImplementedMethods(ExecutableElement implementer) {
        }

        Collection<ExecutableElement> getImplementedMethods();

        TypeMirror getMethodHolder(ExecutableElement ee);
    }

    private record OverrideInfo(ExecutableElement overriddenMethod, boolean simpleOverride) {

        @Override
        public String toString();
    }

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);
}
