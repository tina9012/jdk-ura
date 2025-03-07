/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import java.util.Objects;
import com.sun.org.apache.bcel.internal.classfile.LineNumber;

public class LineNumberGen implements InstructionTargeter, Cloneable {

    public LineNumberGen(final InstructionHandle ih, final int srcLine) {
    }

    @Override
    public Object clone();

    @Override
    @Pure
    public boolean containsTarget(final InstructionHandle ih);

    public InstructionHandle getInstruction();

    public LineNumber getLineNumber();

    public int getSourceLine();

    public void setInstruction(final InstructionHandle instructionHandle);

    public void setSourceLine(final int srcLine);

    @Override
    public void updateTarget(final InstructionHandle oldIh, final InstructionHandle newIh);
}
