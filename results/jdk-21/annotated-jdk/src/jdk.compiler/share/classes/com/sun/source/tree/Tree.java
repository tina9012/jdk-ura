/*
 * Copyright (c) 2005, 2022, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.source.tree;

import jdk.internal.javac.PreviewFeature;
import org.checkerframework.dataflow.qual.Pure;

public interface Tree {

    public enum Kind {

        ANNOTATED_TYPE(AnnotatedTypeTree.class),
        ANNOTATION(AnnotationTree.class),
        TYPE_ANNOTATION(AnnotationTree.class),
        ARRAY_ACCESS(ArrayAccessTree.class),
        ARRAY_TYPE(ArrayTypeTree.class),
        ASSERT(AssertTree.class),
        ASSIGNMENT(AssignmentTree.class),
        BLOCK(BlockTree.class),
        BREAK(BreakTree.class),
        CASE(CaseTree.class),
        CATCH(CatchTree.class),
        CLASS(ClassTree.class),
        COMPILATION_UNIT(CompilationUnitTree.class),
        CONDITIONAL_EXPRESSION(ConditionalExpressionTree.class),
        CONTINUE(ContinueTree.class),
        DO_WHILE_LOOP(DoWhileLoopTree.class),
        ENHANCED_FOR_LOOP(EnhancedForLoopTree.class),
        EXPRESSION_STATEMENT(ExpressionStatementTree.class),
        MEMBER_SELECT(MemberSelectTree.class),
        MEMBER_REFERENCE(MemberReferenceTree.class),
        FOR_LOOP(ForLoopTree.class),
        IDENTIFIER(IdentifierTree.class),
        IF(IfTree.class),
        IMPORT(ImportTree.class),
        INSTANCE_OF(InstanceOfTree.class),
        @PreviewFeature(feature = PreviewFeature.Feature.STRING_TEMPLATES, reflective = true)
        TEMPLATE(StringTemplateTree.class),
        LABELED_STATEMENT(LabeledStatementTree.class),
        METHOD(MethodTree.class),
        METHOD_INVOCATION(MethodInvocationTree.class),
        MODIFIERS(ModifiersTree.class),
        NEW_ARRAY(NewArrayTree.class),
        NEW_CLASS(NewClassTree.class),
        LAMBDA_EXPRESSION(LambdaExpressionTree.class),
        PACKAGE(PackageTree.class),
        PARENTHESIZED(ParenthesizedTree.class),
        @PreviewFeature(feature = PreviewFeature.Feature.UNNAMED)
        ANY_PATTERN(AnyPatternTree.class),
        BINDING_PATTERN(BindingPatternTree.class),
        DEFAULT_CASE_LABEL(DefaultCaseLabelTree.class),
        CONSTANT_CASE_LABEL(ConstantCaseLabelTree.class),
        PATTERN_CASE_LABEL(PatternCaseLabelTree.class),
        DECONSTRUCTION_PATTERN(DeconstructionPatternTree.class),
        PRIMITIVE_TYPE(PrimitiveTypeTree.class),
        RETURN(ReturnTree.class),
        EMPTY_STATEMENT(EmptyStatementTree.class),
        SWITCH(SwitchTree.class),
        SWITCH_EXPRESSION(SwitchExpressionTree.class),
        SYNCHRONIZED(SynchronizedTree.class),
        THROW(ThrowTree.class),
        TRY(TryTree.class),
        PARAMETERIZED_TYPE(ParameterizedTypeTree.class),
        UNION_TYPE(UnionTypeTree.class),
        INTERSECTION_TYPE(IntersectionTypeTree.class),
        TYPE_CAST(TypeCastTree.class),
        TYPE_PARAMETER(TypeParameterTree.class),
        VARIABLE(VariableTree.class),
        WHILE_LOOP(WhileLoopTree.class),
        POSTFIX_INCREMENT(UnaryTree.class),
        POSTFIX_DECREMENT(UnaryTree.class),
        PREFIX_INCREMENT(UnaryTree.class),
        PREFIX_DECREMENT(UnaryTree.class),
        UNARY_PLUS(UnaryTree.class),
        UNARY_MINUS(UnaryTree.class),
        BITWISE_COMPLEMENT(UnaryTree.class),
        LOGICAL_COMPLEMENT(UnaryTree.class),
        MULTIPLY(BinaryTree.class),
        DIVIDE(BinaryTree.class),
        REMAINDER(BinaryTree.class),
        PLUS(BinaryTree.class),
        MINUS(BinaryTree.class),
        LEFT_SHIFT(BinaryTree.class),
        RIGHT_SHIFT(BinaryTree.class),
        UNSIGNED_RIGHT_SHIFT(BinaryTree.class),
        LESS_THAN(BinaryTree.class),
        GREATER_THAN(BinaryTree.class),
        LESS_THAN_EQUAL(BinaryTree.class),
        GREATER_THAN_EQUAL(BinaryTree.class),
        EQUAL_TO(BinaryTree.class),
        NOT_EQUAL_TO(BinaryTree.class),
        AND(BinaryTree.class),
        XOR(BinaryTree.class),
        OR(BinaryTree.class),
        CONDITIONAL_AND(BinaryTree.class),
        CONDITIONAL_OR(BinaryTree.class),
        MULTIPLY_ASSIGNMENT(CompoundAssignmentTree.class),
        DIVIDE_ASSIGNMENT(CompoundAssignmentTree.class),
        REMAINDER_ASSIGNMENT(CompoundAssignmentTree.class),
        PLUS_ASSIGNMENT(CompoundAssignmentTree.class),
        MINUS_ASSIGNMENT(CompoundAssignmentTree.class),
        LEFT_SHIFT_ASSIGNMENT(CompoundAssignmentTree.class),
        RIGHT_SHIFT_ASSIGNMENT(CompoundAssignmentTree.class),
        UNSIGNED_RIGHT_SHIFT_ASSIGNMENT(CompoundAssignmentTree.class),
        AND_ASSIGNMENT(CompoundAssignmentTree.class),
        XOR_ASSIGNMENT(CompoundAssignmentTree.class),
        OR_ASSIGNMENT(CompoundAssignmentTree.class),
        INT_LITERAL(LiteralTree.class),
        LONG_LITERAL(LiteralTree.class),
        FLOAT_LITERAL(LiteralTree.class),
        DOUBLE_LITERAL(LiteralTree.class),
        BOOLEAN_LITERAL(LiteralTree.class),
        CHAR_LITERAL(LiteralTree.class),
        STRING_LITERAL(LiteralTree.class),
        NULL_LITERAL(LiteralTree.class),
        UNBOUNDED_WILDCARD(WildcardTree.class),
        EXTENDS_WILDCARD(WildcardTree.class),
        SUPER_WILDCARD(WildcardTree.class),
        ERRONEOUS(ErroneousTree.class),
        INTERFACE(ClassTree.class),
        ENUM(ClassTree.class),
        ANNOTATION_TYPE(ClassTree.class),
        MODULE(ModuleTree.class),
        EXPORTS(ExportsTree.class),
        OPENS(OpensTree.class),
        PROVIDES(ProvidesTree.class),
        RECORD(ClassTree.class),
        REQUIRES(RequiresTree.class),
        USES(UsesTree.class),
        OTHER(null),
        YIELD(YieldTree.class);

        Kind(Class<? extends Tree> intf) {
        }

        public Class<? extends Tree> asInterface();

        private final Class<? extends Tree> associatedInterface;
    }

    @Pure
    Kind getKind();

    <R, D> R accept(TreeVisitor<R, D> visitor, D data);
}
