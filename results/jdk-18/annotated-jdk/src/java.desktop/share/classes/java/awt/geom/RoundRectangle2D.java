/*
 * Copyright (c) 1997, 2021, Oracle and/or its affiliates. All rights reserved.
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
package java.awt.geom;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.Serial;
import java.io.Serializable;

public abstract class RoundRectangle2D extends RectangularShape {

    public static class Float extends RoundRectangle2D implements Serializable {

        public float x;

        public float y;

        public float width;

        public float height;

        public float arcwidth;

        public float archeight;

        public Float() {
        }

        public Float(float x, float y, float w, float h, float arcw, float arch) {
        }

        public double getX();

        public double getY();

        public double getWidth();

        public double getHeight();

        public double getArcWidth();

        public double getArcHeight();

        public boolean isEmpty();

        public void setRoundRect(float x, float y, float w, float h, float arcw, float arch);

        public void setRoundRect(double x, double y, double w, double h, double arcw, double arch);

        public void setRoundRect(RoundRectangle2D rr);

        public Rectangle2D getBounds2D();
    }

    public static class Double extends RoundRectangle2D implements Serializable {

        public double x;

        public double y;

        public double width;

        public double height;

        public double arcwidth;

        public double archeight;

        public Double() {
        }

        public Double(double x, double y, double w, double h, double arcw, double arch) {
        }

        public double getX();

        public double getY();

        public double getWidth();

        public double getHeight();

        public double getArcWidth();

        public double getArcHeight();

        public boolean isEmpty();

        public void setRoundRect(double x, double y, double w, double h, double arcw, double arch);

        public void setRoundRect(RoundRectangle2D rr);

        public Rectangle2D getBounds2D();
    }

    protected RoundRectangle2D() {
    }

    public abstract double getArcWidth();

    public abstract double getArcHeight();

    public abstract void setRoundRect(double x, double y, double w, double h, double arcWidth, double arcHeight);

    public void setRoundRect(RoundRectangle2D rr);

    public void setFrame(double x, double y, double w, double h);

    public boolean contains(double x, double y);

    public boolean intersects(double x, double y, double w, double h);

    public boolean contains(double x, double y, double w, double h);

    public PathIterator getPathIterator(AffineTransform at);

    public int hashCode();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);
}
