/*
 * Copyright (c) 2017, 2023, Oracle and/or its affiliates. All rights reserved.
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import java.io.DataOutputStream;
import java.io.IOException;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

public abstract class BranchInstruction extends Instruction implements InstructionTargeter {

    static void notifyTarget(final InstructionHandle oldIh, final InstructionHandle newIh, final InstructionTargeter t);

    @Deprecated
    protected int index;

    @Deprecated
    protected InstructionHandle target;

    @Deprecated
    protected int position;

    protected BranchInstruction(final short opcode, final InstructionHandle target) {
    }

    @Override
    @Pure
    public boolean containsTarget(final InstructionHandle ih);

    @Override
    void dispose();

    @Override
    public void dump(final DataOutputStream out) throws IOException;

    public final int getIndex();

    protected int getPosition();

    public InstructionHandle getTarget();

    protected int getTargetOffset();

    protected int getTargetOffset(final InstructionHandle target);

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException;

    protected void setIndex(final int index);

    protected void setPosition(final int position);

    @Override
    void setOpcode(final short opcode);

    public void setTarget(final InstructionHandle target);

    @Override
    public String toString(final boolean verbose);

    protected int updatePosition(final int offset, final int maxOffset);

    @Override
    public void updateTarget(final InstructionHandle oldIh, final InstructionHandle newIh);
}
