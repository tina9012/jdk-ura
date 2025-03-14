/*
 * Copyright (c) 2005, 2022, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.java.accessibility.internal;

import org.checkerframework.dataflow.qual.Pure;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.IllegalComponentStateException;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleEditableText;
import javax.accessibility.AccessibleExtendedComponent;
import javax.accessibility.AccessibleExtendedTable;
import javax.accessibility.AccessibleHyperlink;
import javax.accessibility.AccessibleHypertext;
import javax.accessibility.AccessibleIcon;
import javax.accessibility.AccessibleKeyBinding;
import javax.accessibility.AccessibleRelation;
import javax.accessibility.AccessibleRelationSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleTable;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.TreeUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import com.sun.java.accessibility.util.AWTEventMonitor;
import com.sun.java.accessibility.util.AccessibilityEventMonitor;
import com.sun.java.accessibility.util.EventQueueMonitor;
import com.sun.java.accessibility.util.SwingEventMonitor;
import com.sun.java.accessibility.util.Translator;
import sun.awt.AWTAccessor;
import sun.awt.AppContext;
import sun.awt.SunToolkit;

public final class AccessBridge {

    public AccessBridge() {
    }

    private class dllRunner implements Runnable {

        public void run();
    }

    private class shutdownHook implements Runnable {

        public void run();
    }

    private interface NativeWindowHandler {

        public Accessible getAccessibleFromNativeWindowHandle(int nativeHandle);
    }

    private class DefaultNativeWindowHandler implements NativeWindowHandler {

        public Accessible getAccessibleFromNativeWindowHandle(int nativeHandle);
    }

    private class ObjectReferences {

        private class Reference {

            public String toString();
        }

        String dump();

        void increment(Object o);

        void decrement(Object o);
    }

    private class EventHandler implements PropertyChangeListener, FocusListener, CaretListener, MenuListener, PopupMenuListener, MouseListener, WindowListener, ChangeListener {

        public void windowOpened(WindowEvent e);

        public void windowClosing(WindowEvent e);

        public void windowClosed(WindowEvent e);

        public void windowIconified(WindowEvent e);

        public void windowDeiconified(WindowEvent e);

        public void windowActivated(WindowEvent e);

        public void windowDeactivated(WindowEvent e);

        void addJavaEventNotification(long type);

        void removeJavaEventNotification(long type);

        void addAccessibilityEventNotification(long type);

        void removeAccessibilityEventNotification(long type);

        public void propertyChange(PropertyChangeEvent e);

        public void focusGained(FocusEvent e);

        public void stateChanged(ChangeEvent e);

        public void focusLost(FocusEvent e);

        public void caretUpdate(CaretEvent e);

        public void mouseClicked(MouseEvent e);

        public void mouseEntered(MouseEvent e);

        public void mouseExited(MouseEvent e);

        public void mousePressed(MouseEvent e);

        public void mouseReleased(MouseEvent e);

        public void menuCanceled(MenuEvent e);

        public void menuDeselected(MenuEvent e);

        public void menuSelected(MenuEvent e);

        public void popupMenuCanceled(PopupMenuEvent e);

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e);

        public void popupMenuWillBecomeVisible(PopupMenuEvent e);
    }

    private class AccessibleJTreeNode extends AccessibleContext implements Accessible, AccessibleComponent, AccessibleSelection, AccessibleAction {

        public AccessibleContext getAccessibleContext();

        public String getAccessibleName();

        public void setAccessibleName(String s);

        public String getAccessibleDescription();

        public void setAccessibleDescription(String s);

        public AccessibleRole getAccessibleRole();

        public AccessibleStateSet getAccessibleStateSet();

        public Accessible getAccessibleParent();

        public int getAccessibleIndexInParent();

        public int getAccessibleChildrenCount();

        public Accessible getAccessibleChild(int i);

        public Locale getLocale();

        public void addPropertyChangeListener(PropertyChangeListener l);

        public void removePropertyChangeListener(PropertyChangeListener l);

        public AccessibleAction getAccessibleAction();

        public AccessibleComponent getAccessibleComponent();

        public AccessibleSelection getAccessibleSelection();

        public AccessibleText getAccessibleText();

        public AccessibleValue getAccessibleValue();

        public Color getBackground();

        public void setBackground(Color c);

        public Color getForeground();

        public void setForeground(Color c);

        public Cursor getCursor();

        public void setCursor(Cursor c);

        public Font getFont();

        public void setFont(Font f);

        public FontMetrics getFontMetrics(Font f);

        public boolean isEnabled();

        public void setEnabled(boolean b);

        public boolean isVisible();

        public void setVisible(boolean b);

        public boolean isShowing();

        @Pure
        public boolean contains(Point p);

        public Point getLocationOnScreen();

        public Point getLocation();

        public void setLocation(Point p);

        public Rectangle getBounds();

        public void setBounds(Rectangle r);

        public Dimension getSize();

        public void setSize(Dimension d);

        public Accessible getAccessibleAt(Point p);

        public boolean isFocusTraversable();

        public void requestFocus();

        public void addFocusListener(FocusListener l);

        public void removeFocusListener(FocusListener l);

        public int getAccessibleSelectionCount();

        public Accessible getAccessibleSelection(int i);

        public boolean isAccessibleChildSelected(int i);

        public void addAccessibleSelection(int i);

        public void removeAccessibleSelection(int i);

        public void clearAccessibleSelection();

        public void selectAllAccessibleSelection();

        public int getAccessibleActionCount();

        public String getAccessibleActionDescription(int i);

        public boolean doAccessibleAction(int i);
    }

    private static class InvocationUtils {

        public static <T> T invokeAndWait(final Callable<T> callable, final AccessibleExtendedTable accessibleTable);

        public static <T> T invokeAndWait(final Callable<T> callable, final Accessible accessible);

        public static <T> T invokeAndWait(final Callable<T> callable, final Component component);

        public static <T> T invokeAndWait(final Callable<T> callable, final AccessibleContext accessibleContext);

        public static void registerAccessibleContext(final AccessibleContext accessibleContext, final AppContext targetContext);

        private static class CallableWrapper<T> implements Runnable {

            public void run();

            T getResult() throws Exception;
        }
    }

    private static abstract class AccessibilityGraphicsEnvironment extends GraphicsEnvironment {

        public static GraphicsConfiguration getGraphicsConfigurationAtPoint(double x, double y);

        public static GraphicsConfiguration getGraphicsConfigurationAtPoint(GraphicsConfiguration current, double x, double y);

        public static GraphicsConfiguration getGraphicsConfigurationAtDevicePoint(double x, double y);

        public static GraphicsConfiguration getGraphicsConfigurationAtDevicePoint(GraphicsConfiguration current, double x, double y);

        public static Point toUserSpace(int x, int y);

        public static Point toUserSpace(GraphicsConfiguration gc, int x, int y);

        public static Rectangle toDeviceSpaceAbs(Rectangle rect);

        public static Rectangle toDeviceSpaceAbs(GraphicsConfiguration gc, int x, int y, int w, int h);
    }
}
