/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import com.sun.org.apache.bcel.internal.classfile.CodeException;

public final class CodeExceptionGen implements InstructionTargeter, Cloneable {

    public CodeExceptionGen(final InstructionHandle startPc, final InstructionHandle endPc, final InstructionHandle handlerPc, final ObjectType catchType) {
    }

    @Override
    public Object clone();

    @Override
    @Pure
    public boolean containsTarget(final InstructionHandle ih);

    public ObjectType getCatchType();

    public CodeException getCodeException(final ConstantPoolGen cp);

    public InstructionHandle getEndPC();

    public InstructionHandle getHandlerPC();

    public InstructionHandle getStartPC();

    public void setCatchType(final ObjectType catchType);

    public void setEndPC(final InstructionHandle endPc);

    public void setHandlerPC(final InstructionHandle handlerPc);

    public void setStartPC(final InstructionHandle startPc);

    @Override
    public String toString();

    @Override
    public void updateTarget(final InstructionHandle oldIh, final InstructionHandle newIh);
}
