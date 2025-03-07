/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.security.signature.reference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ReferenceSubTreeData implements ReferenceNodeSetData {

    public ReferenceSubTreeData(Node root, boolean excludeComments) {
    }

    @Override
    public Iterator<Node> iterator();

    public Node getRoot();

    public boolean excludeComments();

    static class DelayedNodeIterator implements Iterator<Node> {

        @Override
        @Pure
        public boolean hasNext();

        @Override
        @SideEffectsOnly("this")
        public Node next();

        @Override
        public void remove();
    }
}
