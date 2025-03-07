/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;

public interface InstructionTargeter {

    @Pure
    boolean containsTarget(InstructionHandle ih);

    void updateTarget(InstructionHandle oldIh, InstructionHandle newIh) throws ClassGenException;
}
