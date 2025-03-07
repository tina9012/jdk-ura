/*
 * Copyright (c) 2017, 2021, Oracle and/or its affiliates. All rights reserved.
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import java.io.DataOutputStream;
import java.io.IOException;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {

    @Deprecated
    protected int[] match;

    @Deprecated
    protected int[] indices;

    @Deprecated
    protected InstructionHandle[] targets;

    @Deprecated
    protected int fixed_length;

    @Deprecated
    protected int match_length;

    @Deprecated
    protected int padding;

    @Override
    protected Object clone() throws CloneNotSupportedException;

    @Override
    @Pure
    public boolean containsTarget(final InstructionHandle ih);

    @Override
    void dispose();

    @Override
    public void dump(final DataOutputStream out) throws IOException;

    final int getFixedLength();

    public int[] getIndices();

    final int getIndices(final int index);

    final int getMatch(final int index);

    final int getMatchLength();

    public int[] getMatchs();

    final int getPadding();

    final InstructionHandle getTarget(final int index);

    public InstructionHandle[] getTargets();

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException;

    final void setFixedLength(final int fixedLength);

    final int setIndices(final int i, final int value);

    final void setIndices(final int[] array);

    final void setMatch(final int index, final int value);

    final void setMatches(final int[] array);

    final int setMatchLength(final int matchLength);

    public void setTarget(final int i, final InstructionHandle target);

    final void setTargets(final InstructionHandle[] array);

    @Override
    public String toString(final boolean verbose);

    @Override
    protected int updatePosition(final int offset, final int maxOffset);

    @Override
    public void updateTarget(final InstructionHandle oldIh, final InstructionHandle newIh);
}
