/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import javax.swing.JLabel;
import lib.editor.ui.MainWindow;
import lib.editor.widget.inspector.Inspector;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import lib.editor.widget.tree.tree.MapTree;

/**
 *
 * @author gaetan
 */
public class WidgetMgr {
    
    public static MainWindow MAIN_WINDOW;
    public static MapEditorGraphicsView MAP_EDITOR;
    public static MapTree MAP_TREE;
    
    public static JLabel STATUS_LABEL;
    
    public static Inspector INSPECTOR;
}
