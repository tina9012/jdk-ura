/*
 * Copyright (c) 2002-2021, the original author(s).
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * https://opensource.org/licenses/BSD-3-Clause
 */
package jdk.internal.org.jline.utils;

import org.checkerframework.dataflow.qual.Pure;
import java.util.ArrayList;
import java.util.List;
import jdk.internal.org.jline.terminal.Terminal;
import jdk.internal.org.jline.terminal.impl.AbstractWindowsTerminal;
import jdk.internal.org.jline.utils.InfoCmp.Capability;
import static jdk.internal.org.jline.terminal.TerminalBuilder.PROP_DISABLE_ALTERNATE_CHARSET;
import static jdk.internal.org.jline.utils.AttributedStyle.BG_COLOR;
import static jdk.internal.org.jline.utils.AttributedStyle.BG_COLOR_EXP;
import static jdk.internal.org.jline.utils.AttributedStyle.FG_COLOR;
import static jdk.internal.org.jline.utils.AttributedStyle.FG_COLOR_EXP;
import static jdk.internal.org.jline.utils.AttributedStyle.F_BACKGROUND;
import static jdk.internal.org.jline.utils.AttributedStyle.F_BACKGROUND_IND;
import static jdk.internal.org.jline.utils.AttributedStyle.F_BACKGROUND_RGB;
import static jdk.internal.org.jline.utils.AttributedStyle.F_BLINK;
import static jdk.internal.org.jline.utils.AttributedStyle.F_BOLD;
import static jdk.internal.org.jline.utils.AttributedStyle.F_CONCEAL;
import static jdk.internal.org.jline.utils.AttributedStyle.F_CROSSED_OUT;
import static jdk.internal.org.jline.utils.AttributedStyle.F_FAINT;
import static jdk.internal.org.jline.utils.AttributedStyle.F_FOREGROUND;
import static jdk.internal.org.jline.utils.AttributedStyle.F_FOREGROUND_IND;
import static jdk.internal.org.jline.utils.AttributedStyle.F_FOREGROUND_RGB;
import static jdk.internal.org.jline.utils.AttributedStyle.F_HIDDEN;
import static jdk.internal.org.jline.utils.AttributedStyle.F_INVERSE;
import static jdk.internal.org.jline.utils.AttributedStyle.F_ITALIC;
import static jdk.internal.org.jline.utils.AttributedStyle.F_UNDERLINE;
import static jdk.internal.org.jline.utils.AttributedStyle.MASK;

public abstract class AttributedCharSequence implements CharSequence {

    public static final int TRUE_COLORS;

    public enum ForceMode {

        None, Force256Colors, ForceTrueColors
    }

    public void print(Terminal terminal);

    public void println(Terminal terminal);

    public String toAnsi();

    public String toAnsi(Terminal terminal);

    @Deprecated
    public String toAnsi(int colors, boolean force256colors);

    @Deprecated
    public String toAnsi(int colors, boolean force256colors, String altIn, String altOut);

    public String toAnsi(int colors, ForceMode force);

    public String toAnsi(int colors, ForceMode force, ColorPalette palette);

    public String toAnsi(int colors, ForceMode force, ColorPalette palette, String altIn, String altOut);

    @Deprecated
    public static int rgbColor(int col);

    @Deprecated
    public static int roundColor(int col, int max);

    @Deprecated
    public static int roundRgbColor(int r, int g, int b, int max);

    public abstract AttributedStyle styleAt(int index);

    long styleCodeAt(int index);

    public boolean isHidden(int index);

    public int runStart(int index);

    public int runLimit(int index);

    @Override
    public abstract AttributedString subSequence(int start, int end);

    public AttributedString substring(int start, int end);

    protected abstract char[] buffer();

    protected abstract int offset();

    @Override
    public char charAt(int index);

    public int codePointAt(int index);

    @Pure
    public boolean contains(char c);

    public int codePointBefore(int index);

    public int codePointCount(int index, int length);

    public int columnLength();

    public AttributedString columnSubSequence(int start, int stop);

    public List<AttributedString> columnSplitLength(int columns);

    public List<AttributedString> columnSplitLength(int columns, boolean includeNewlines, boolean delayLineWrap);

    @Override
    public String toString();

    public AttributedString toAttributedString();
}
