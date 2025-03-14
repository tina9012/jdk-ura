/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.LocalVariable;

public class LocalVariableGen implements InstructionTargeter, NamedAndTyped, Cloneable {

    public LocalVariableGen(final int index, final String name, final Type type, final InstructionHandle start, final InstructionHandle end) {
    }

    public LocalVariableGen(final int index, final String name, final Type type, final InstructionHandle start, final InstructionHandle end, final int origIndex) {
    }

    @Override
    public Object clone();

    @Override
    @Pure
    public boolean containsTarget(final InstructionHandle ih);

    void dispose();

    @Override
    public boolean equals(final Object o);

    public InstructionHandle getEnd();

    public int getIndex();

    public boolean getLiveToEnd();

    public LocalVariable getLocalVariable(final ConstantPoolGen cp);

    @Override
    public String getName();

    public int getOrigIndex();

    public InstructionHandle getStart();

    @Override
    public Type getType();

    @Override
    public int hashCode();

    public void setEnd(final InstructionHandle end);

    public void setIndex(final int index);

    public void setLiveToEnd(final boolean liveToEnd);

    @Override
    public void setName(final String name);

    public void setStart(final InstructionHandle start);

    @Override
    public void setType(final Type type);

    @Override
    public String toString();

    @Override
    public void updateTarget(final InstructionHandle oldIh, final InstructionHandle newIh);
}
