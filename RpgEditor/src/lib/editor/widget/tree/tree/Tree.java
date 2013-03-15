/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import lib.editor.data.editor.DataEditorTreeItem;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.option.TreeFilter;
import org.jdesktop.swingx.JXTree;


/**
 *
 * @author gaetan
 */
public abstract class Tree extends JXTree{
    
    //public boolean itemCacheEnabled;
    //public List<TreeItem> itemCache;
    //int topLevelItemCount;
    public TreeFilter filter;
    
    public Tree(){
        //topLevelItemCount = 0;
        //itemCacheEnabled = true;
        
        TreeItem root = new TreeItem(this, "", null);
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.setRoot(root);
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        //root.removeAllChildren();
        //root.add(new TreeItem("another_child"));
        //expandRow(0);
        
        //itemCache = new ArrayList<TreeItem>();
        
        setRootVisible(false);
        setCellRenderer(new TreeItemRenderer());
        
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                if(e.getNewLeadSelectionPath() == null){
                    currentItemChanged(null);
                }
                else{
                    currentItemChanged((TreeItem)e.getNewLeadSelectionPath().getLastPathComponent());
                }
            }
        });
        
        addTreeExpansionListener(new TreeExpansionListener () {

            @Override
            public void treeExpanded(TreeExpansionEvent evt) {
                itemExpanded((TreeItem)evt.getPath().getLastPathComponent());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
            }
        });
                        
        addTreeWillExpandListener(new TreeWillExpandListener () {

            @Override
            public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
                boolean expand = itemWillExpanded((TreeItem)evt.getPath().getLastPathComponent());
                if(!expand){
                    throw new ExpandVetoException(evt);
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent evt) throws ExpandVetoException {
                boolean collapse = itemWillCollapsed((TreeItem)evt.getPath().getLastPathComponent());
                if(!collapse){
                    throw new ExpandVetoException(evt);
                }
            }
        });
        
        //improve item selection (click is detected on all the row of the iem)
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(e.getY() > getRowHeight()*getRowCount()){
                    return;
                }
                
                int selRow = getClosestRowForLocation( e.getX(), e.getY());
                
                if( selRow != -1) {
                    Rectangle bounds = getRowBounds( selRow);
                    boolean outside = e.getX() < bounds.x || e.getX() > bounds.x + bounds.width || e.getY() < bounds.y || e.getY() >= bounds.y + bounds.height;
                    if( outside) {
                        setSelectionRow(selRow);

                        if( e.getClickCount() == 2) {
                                if( isCollapsed(selRow)){
                                        expandRow( selRow);}
                                else if( isExpanded( selRow)){
                                        collapseRow( selRow);}
                        }
                    }
                }
            }
        };
        addMouseListener(ml);
        
    }
    
    public void currentItemChanged(TreeItem newItem){
    }
    
    public void itemExpanded(TreeItem item){
    }
        

    
    public TreeItem getRoot(){
        return (TreeItem) getModel().getRoot();
    }
    
    public void clear(){
        //topLevelItemCount = 0;
        //itemCache.clear();
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        getRoot().removeAllChildren();
        model.reload(getRoot());
    }
    /*
    public void visualClear(){
        //topLevelItemCount = 0;
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        getRoot().removeAllChildren();
        model.reload(getRoot());
    }*/
    
    public int getTopLevelItemCount(){
        return getRoot().getChildCount();
    }
    
    public void addTopLevelItem(TreeItem item){
        //topLevelItemCount += 1;
        //addItem(item, getRoot());
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.insertNodeInto(item, getRoot(), getRoot().getChildCount());
        //model.reload(getRoot());
    }
    
    public TreeItem getTopLevelItem(int index){
        //if(index >= topLevelItemCount){
        //    return null;
        //}
        return (TreeItem) getRoot().getChildAt(index);
    }
    
    //public void insertTopLevelitem(int index, TreeItem item){
        //DefaultTreeModel model = (DefaultTreeModel)getModel();
        //model.insertNodeInto(item, getRoot(), index);
        //model.reload(getRoot());
    //}
    
    public TreeItem getCurrentItem(){
        if(isSelectionEmpty()){
            return null;
        }
        
        return (TreeItem) getLastSelectedPathComponent();
    }
    
    public void setCurrentItem(TreeItem item){
        setSelectionPath(new TreePath(item.getPath()));
    }
    
    public List<TreeItem> getItems(){
        if(filter != null && filter.items != null){
            return new ArrayList<TreeItem>(filter.items);
        }
        
        List<TreeItem> result = new ArrayList<TreeItem>();
        Enumeration e = getRoot().breadthFirstEnumeration();
        e.nextElement();
        while(e.hasMoreElements()){
            result.add((TreeItem) e.nextElement());
        }
        return result;
    }
    
    public void expandPath(TreePath path) {
        for(Object node : path.getPath()){
            TreeItem parentItem = (TreeItem) node;
            if(parentItem != path.getLastPathComponent() && !isExpanded(new TreePath(parentItem.getPath()))){
                return;
            }
        }
        super.expandPath(path);
    }
    
    public void setItemExpanded(TreeItem item, boolean expanded){
        if(expanded){
            expandPath(new TreePath(item.getPath()));
        }
        else{
            collapsePath(new TreePath(item.getPath()));
        }
        
    }
    
    public boolean itemWillExpanded(TreeItem item){
        /*
        System.out.println("--------------------------------" + item.getText());
        for(TreeNode node : item.getPath()){
            TreeItem parentItem = (TreeItem) node;
            boolean parentExpanded = isExpanded(new TreePath(parentItem.getPath()));
            System.out.println(parentItem.getText() + "   " + parentExpanded);
            if(parentItem != item && !parentExpanded){
                return false;
            }
        }*/
        return true;
    }
    
    public boolean itemWillCollapsed(TreeItem item){
        return true;
    }
    
}
