/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package org.jcp.xml.dsig.internal.dom;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignContext;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLValidateContext;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public final class DOMXMLSignature extends DOMStructure implements XMLSignature {

    public DOMXMLSignature(SignedInfo si, KeyInfo ki, List<? extends XMLObject> objs, String id, String signatureValueId) {
    }

    public DOMXMLSignature(Element sigElem, XMLCryptoContext context, Provider provider) throws MarshalException {
    }

    @Override
    public String getId();

    @Override
    public KeyInfo getKeyInfo();

    @Override
    public SignedInfo getSignedInfo();

    @Override
    public List<XMLObject> getObjects();

    @Override
    public SignatureValue getSignatureValue();

    @Override
    public KeySelectorResult getKeySelectorResult();

    @Override
    public void marshal(Node parent, String dsPrefix, DOMCryptoContext context) throws MarshalException;

    public void marshal(Node parent, Node nextSibling, String dsPrefix, DOMCryptoContext context) throws MarshalException;

    @Override
    public boolean validate(XMLValidateContext vc) throws XMLSignatureException;

    @Override
    public void sign(XMLSignContext signContext) throws MarshalException, XMLSignatureException;

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    @Override
    public int hashCode();

    public class DOMSignatureValue extends DOMStructure implements SignatureValue {

        @Override
        public String getId();

        @Override
        public byte[] getValue();

        public String getEncodedValue();

        @Override
        public boolean validate(XMLValidateContext validateContext) throws XMLSignatureException;

        @Override
        public boolean equals(Object o);

        @Override
        public int hashCode();

        @Override
        public void marshal(Node parent, String dsPrefix, DOMCryptoContext context) throws MarshalException;

        void setValue(byte[] value);
    }
}
