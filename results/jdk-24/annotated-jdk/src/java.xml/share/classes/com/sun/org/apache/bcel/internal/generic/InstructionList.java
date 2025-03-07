/*
 * Copyright (c) 2017, 2023, Oracle and/or its affiliates. All rights reserved.
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

public class InstructionList implements Iterable<InstructionHandle> {

    public static InstructionHandle findHandle(final InstructionHandle[] ihs, final int[] pos, final int count, final int target);

    public InstructionList() {
    }

    public InstructionList(final BranchInstruction i) {
    }

    public InstructionList(final byte[] code) {
    }

    public InstructionList(final CompoundInstruction c) {
    }

    public InstructionList(final Instruction i) {
    }

    public void addObserver(final InstructionListObserver o);

    public BranchHandle append(final BranchInstruction i);

    public InstructionHandle append(final CompoundInstruction c);

    public InstructionHandle append(final Instruction i);

    public InstructionHandle append(final Instruction i, final CompoundInstruction c);

    public InstructionHandle append(final Instruction i, final Instruction j);

    public InstructionHandle append(final Instruction i, final InstructionList il);

    public BranchHandle append(final InstructionHandle ih, final BranchInstruction i);

    public InstructionHandle append(final InstructionHandle ih, final CompoundInstruction c);

    public InstructionHandle append(final InstructionHandle ih, final Instruction i);

    public InstructionHandle append(final InstructionHandle ih, final InstructionList il);

    public InstructionHandle append(final InstructionList il);

    @Pure
    public boolean contains(final Instruction i);

    @Pure
    public boolean contains(final InstructionHandle i);

    public InstructionList copy();

    public void delete(final Instruction i) throws TargetLostException;

    public void delete(final Instruction from, final Instruction to) throws TargetLostException;

    public void delete(final InstructionHandle ih) throws TargetLostException;

    public void delete(final InstructionHandle from, final InstructionHandle to) throws TargetLostException;

    public void dispose();

    public InstructionHandle findHandle(final int pos);

    public byte[] getByteCode();

    public InstructionHandle getEnd();

    public InstructionHandle[] getInstructionHandles();

    public int[] getInstructionPositions();

    public Instruction[] getInstructions();

    public int getLength();

    public InstructionHandle getStart();

    public BranchHandle insert(final BranchInstruction i);

    public InstructionHandle insert(final CompoundInstruction c);

    public InstructionHandle insert(final Instruction i);

    public InstructionHandle insert(final Instruction i, final CompoundInstruction c);

    public InstructionHandle insert(final Instruction i, final Instruction j);

    public InstructionHandle insert(final Instruction i, final InstructionList il);

    public BranchHandle insert(final InstructionHandle ih, final BranchInstruction i);

    public InstructionHandle insert(final InstructionHandle ih, final CompoundInstruction c);

    public InstructionHandle insert(final InstructionHandle ih, final Instruction i);

    public InstructionHandle insert(final InstructionHandle ih, final InstructionList il);

    public InstructionHandle insert(final InstructionList il);

    public boolean isEmpty();

    @Override
    public Iterator<InstructionHandle> iterator();

    public void move(final InstructionHandle ih, final InstructionHandle target);

    public void move(final InstructionHandle start, final InstructionHandle end, final InstructionHandle target);

    public void redirectBranches(final InstructionHandle oldTarget, final InstructionHandle newTarget);

    public void redirectExceptionHandlers(final CodeExceptionGen[] exceptions, final InstructionHandle oldTarget, final InstructionHandle newTarget);

    public void redirectLocalVariables(final LocalVariableGen[] lg, final InstructionHandle oldTarget, final InstructionHandle newTarget);

    public void removeObserver(final InstructionListObserver o);

    public void replaceConstantPool(final ConstantPoolGen oldCp, final ConstantPoolGen newCp);

    public void setPositions();

    public void setPositions(final boolean check);

    public int size();

    @Override
    public String toString();

    public String toString(final boolean verbose);

    public void update();
}
