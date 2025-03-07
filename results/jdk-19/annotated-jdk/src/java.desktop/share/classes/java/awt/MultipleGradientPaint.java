/*
 * Copyright (c) 2006, 2011, Oracle and/or its affiliates. All rights reserved.
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
package java.awt;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.lang.ref.SoftReference;
import java.util.Arrays;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract sealed class MultipleGradientPaint implements Paint permits LinearGradientPaint, RadialGradientPaint {

    public static enum CycleMethod {

        NO_CYCLE, REFLECT, REPEAT
    }

    public static enum ColorSpaceType {

        SRGB, LINEAR_RGB
    }

    public final float[] getFractions();

    public final Color[] getColors();

    public final CycleMethod getCycleMethod();

    public final ColorSpaceType getColorSpace();

    public final AffineTransform getTransform();

    public final int getTransparency();
}
