/*
 * Copyright (c) 1994, 2021, Oracle and/or its affiliates. All rights reserved.
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
package java.lang;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.checkerframework.checker.signature.qual.CanonicalName;
import org.checkerframework.checker.signature.qual.ClassGetName;
import org.checkerframework.checker.signature.qual.ClassGetSimpleName;
import org.checkerframework.checker.signature.qual.DotSeparatedIdentifiers;
import org.checkerframework.checker.signedness.qual.Signed;
import org.checkerframework.common.reflection.qual.ForName;
import org.checkerframework.common.reflection.qual.GetConstructor;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import org.checkerframework.framework.qual.Covariant;
import java.lang.annotation.Annotation;
import java.lang.constant.ClassDesc;
import java.lang.invoke.TypeDescriptor;
import java.lang.invoke.MethodHandles;
import java.lang.module.ModuleReader;
import java.lang.ref.SoftReference;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamField;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.RecordComponent;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.constant.Constable;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import jdk.internal.loader.BootLoader;
import jdk.internal.loader.BuiltinClassLoader;
import jdk.internal.misc.Unsafe;
import jdk.internal.module.Resources;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.CallerSensitiveAdapter;
import jdk.internal.reflect.ConstantPool;
import jdk.internal.reflect.Reflection;
import jdk.internal.reflect.ReflectionFactory;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import sun.invoke.util.Wrapper;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.repository.ClassRepository;
import sun.reflect.generics.repository.MethodRepository;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.scope.ClassScope;
import sun.security.util.SecurityConstants;
import sun.reflect.annotation.*;
import sun.reflect.misc.ReflectUtil;

@CFComment({ "interning: All instances of Class are interned.", "lock: public boolean isTypeAnnotationPresent(@GuardSatisfied Class<T> this,@GuardSatisfied Class<T><? extends java.lang.annotation.Annotation> annotationClass) { throw new RuntimeException(\"skeleton method\"); }", "public <M extends java.lang.annotation.Annotation> M getTypeAnnotation(Class<M> annotationClass) { throw new RuntimeException(\"skeleton method\"); }", "public java.lang.annotation.Annotation[] getTypeAnnotations() { throw new RuntimeException(\"skeleton method\"); }", "public java.lang.annotation.Annotation[] getDeclaredTypeAnnotations() { throw new RuntimeException(\"skeleton method\"); }", "nullness: The type argument to Class is meaningless.", "Class<@NonNull String> and Class<@Nullable String> have the same", "meaning, but are unrelated by the Java type hierarchy.", "@Covariant makes Class<@NonNull String> a subtype of Class<@Nullable String>." })
@AnnotatedFor({ "index", "interning", "lock", "nullness", "signature" })
@Covariant({ 0 })
@Interned
public final class Class<@UnknownKeyFor T> implements java.io.Serializable, GenericDeclaration, Type, AnnotatedElement, TypeDescriptor.OfField<Class<?>>, Constable {

    @SideEffectFree
    public String toString(@GuardSatisfied Class<T> this);

    public String toGenericString();

    static String typeVarBounds(TypeVariable<?> typeVar);

    @ForName
    @CallerSensitive
    public static Class<? extends Object> forName(@ClassGetName String className) throws ClassNotFoundException;

    @CallerSensitive
    public static Class<? extends Object> forName(@ClassGetName String name, boolean initialize, @Nullable ClassLoader loader) throws ClassNotFoundException;

    @SuppressWarnings("removal")
    @CallerSensitive
    public static Class<? extends Object> forName(Module module, String name);

    @SuppressWarnings("removal")
    @CallerSensitive
    @Deprecated()
    @NonNull
    public T newInstance() throws InstantiationException, IllegalAccessException;

    @Pure
    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @IntrinsicCandidate
    public native boolean isInstance(@GuardSatisfied Class<T> this, @Nullable Object obj);

    @Pure
    @IntrinsicCandidate
    public native boolean isAssignableFrom(@GuardSatisfied Class<T> this, Class<?> cls);

    @Pure
    @IntrinsicCandidate
    public native boolean isInterface(@GuardSatisfied Class<T> this);

    @EnsuresNonNullIf(expression = { "getComponentType()" }, result = true)
    @Pure
    @IntrinsicCandidate
    public native boolean isArray(@GuardSatisfied Class<T> this);

    @Pure
    @IntrinsicCandidate
    public native boolean isPrimitive(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isAnnotation(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isSynthetic(@GuardSatisfied Class<T> this);

    @CFComment({ "interning: In the Oracle JDK, the result of getName is interned", "signature: For a non-array non-primitive type, returns @BinaryName" })
    @Pure
    @ClassGetName
    @Interned
    public String getName();

    @CallerSensitive
    @ForceInline
    @Nullable
    public ClassLoader getClassLoader();

    ClassLoader getClassLoader0();

    public Module getModule();

    Object getClassData();

    @SuppressWarnings("unchecked")
    public TypeVariable<Class<T>>[] getTypeParameters();

    @Pure
    @IntrinsicCandidate
    @Nullable
    public native Class<? super T> getSuperclass(@GuardSatisfied Class<T> this);

    @Nullable
    public Type getGenericSuperclass();

    @Pure
    @Nullable
    public Package getPackage(@GuardSatisfied Class<T> this);

    @DotSeparatedIdentifiers
    public String getPackageName();

    @SideEffectFree
    public Class<? extends Object>[] getInterfaces(@GuardSatisfied Class<T> this);

    public Type[] getGenericInterfaces();

    @Pure
    @Nullable
    public Class<? extends Object> getComponentType(@GuardSatisfied Class<T> this);

    @Pure
    @IntrinsicCandidate
    public native int getModifiers(@GuardSatisfied Class<T> this);

    public native Object @Nullable [] getSigners();

    native void setSigners(Object[] signers);

    @CallerSensitive
    @Nullable
    public Method getEnclosingMethod() throws SecurityException;

    private static final class EnclosingMethodInfo {

        static void validate(Object[] enclosingInfo);

        boolean isPartial();

        boolean isConstructor();

        boolean isMethod();

        Class<?> getEnclosingClass();

        String getName();

        String getDescriptor();
    }

    @CallerSensitive
    @Nullable
    public Constructor<? extends Object> getEnclosingConstructor() throws SecurityException;

    @CallerSensitive
    @Nullable
    public Class<? extends Object> getDeclaringClass() throws SecurityException;

    @Pure
    @CallerSensitive
    @Nullable
    public Class<? extends Object> getEnclosingClass() throws SecurityException;

    @ClassGetSimpleName
    public String getSimpleName();

    public String getTypeName();

    @Nullable
    @CanonicalName
    public String getCanonicalName();

    @Pure
    public boolean isAnonymousClass(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isLocalClass(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isMemberClass(@GuardSatisfied Class<T> this);

    @SuppressWarnings("removal")
    @CallerSensitive
    public Class<? extends Object>[] getClasses();

    @CallerSensitive
    public Field[] getFields() throws SecurityException;

    @CallerSensitive
    public Method[] getMethods() throws SecurityException;

    @CallerSensitive
    public Constructor<? extends Object>[] getConstructors() throws SecurityException;

    @CallerSensitive
    public Field getField(String name) throws NoSuchFieldException, SecurityException;

    @Pure
    @GetMethod
    @CallerSensitive
    public Method getMethod(String name, Class<?>@Nullable ... parameterTypes) throws NoSuchMethodException, SecurityException;

    @GetConstructor
    @Pure
    @CallerSensitive
    public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    @CallerSensitive
    public Class<? extends Object>[] getDeclaredClasses() throws SecurityException;

    @CallerSensitive
    public Field[] getDeclaredFields() throws SecurityException;

    @CallerSensitive
    public RecordComponent[] getRecordComponents();

    @CallerSensitive
    public Method[] getDeclaredMethods() throws SecurityException;

    @CallerSensitive
    public Constructor<?>[] getDeclaredConstructors() throws SecurityException;

    @CallerSensitive
    public Field getDeclaredField(String name) throws NoSuchFieldException, SecurityException;

    @GetMethod
    @CallerSensitive
    public Method getDeclaredMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    List<Method> getDeclaredPublicMethods(String name, Class<?>... parameterTypes);

    @CallerSensitive
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException;

    @CallerSensitive
    @Nullable
    public InputStream getResourceAsStream(String name);

    @CallerSensitive
    @Nullable
    public URL getResource(String name);

    public java.security.ProtectionDomain getProtectionDomain();

    java.security.ProtectionDomain protectionDomain();

    static native Class<?> getPrimitiveClass(String name);

    private static class Atomic {

        static <T> boolean casReflectionData(Class<?> clazz, SoftReference<ReflectionData<T>> oldData, SoftReference<ReflectionData<T>> newData);

        static boolean casAnnotationType(Class<?> clazz, AnnotationType oldType, AnnotationType newType);

        static boolean casAnnotationData(Class<?> clazz, AnnotationData oldData, AnnotationData newData);
    }

    private static class ReflectionData<T> {
    }

    native byte[] getRawAnnotations();

    native byte[] getRawTypeAnnotations();

    static byte[] getExecutableTypeAnnotationBytes(Executable ex);

    native ConstantPool getConstantPool();

    public boolean desiredAssertionStatus();

    @Pure
    public boolean isEnum(@GuardSatisfied Class<T> this);

    @Pure
    public boolean isRecord();

    @NonNull
    public T @Nullable [] getEnumConstants();

    @SuppressWarnings("removal")
    T[] getEnumConstantsShared();

    Map<String, @NonNull T> enumConstantDirectory();

    @SuppressWarnings("unchecked")
    @IntrinsicCandidate
    @PolyNull
    @Signed
    public T cast(@PolyNull Object obj);

    @SuppressWarnings("unchecked")
    public <U> Class<? extends U> asSubclass(Class<U> clazz);

    @Override
    @SuppressWarnings("unchecked")
    @Nullable
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass);

    @Pure
    @Override
    public boolean isAnnotationPresent(@GuardSatisfied Class<T> this, @GuardSatisfied Class<? extends Annotation> annotationClass);

    @Override
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass);

    @Override
    public Annotation[] getAnnotations();

    @Override
    @SuppressWarnings("unchecked")
    @Nullable
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass);

    @Override
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass);

    @Override
    public Annotation[] getDeclaredAnnotations();

    private static class AnnotationData {
    }

    boolean casAnnotationType(AnnotationType oldType, AnnotationType newType);

    AnnotationType getAnnotationType();

    Map<Class<? extends Annotation>, Annotation> getDeclaredAnnotationMap();

    public AnnotatedType getAnnotatedSuperclass();

    public AnnotatedType[] getAnnotatedInterfaces();

    @CallerSensitive
    public Class<? extends Object> getNestHost();

    public boolean isNestmateOf(Class<?> c);

    @CallerSensitive
    public Class<? extends Object>[] getNestMembers();

    @Override
    @SideEffectFree
    public String descriptorString();

    @Override
    @Pure
    @Nullable
    public Class<? extends Object> componentType();

    @Override
    @Pure
    public Class<? extends Object> arrayType();

    @Override
    public Optional<ClassDesc> describeConstable();

    @IntrinsicCandidate
    @Pure
    public native boolean isHidden();

    @CallerSensitive
    public Class<? extends Object>[] getPermittedSubclasses();

    @Pure
    public boolean isSealed();
}
