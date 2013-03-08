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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import lib.editor.widget.tree.item.TreeItem;
import org.jdesktop.swingx.JXTree;


/**
 *
 * @author gaetan
 */
public abstract class Tree extends JXTree{
    
    public boolean itemCacheEnabled;
    public List<TreeItem> itemCache;
    int topLevelItemCount;
    
    
    
    public Tree(){
        topLevelItemCount = 0;
        itemCacheEnabled = true;
        
        TreeItem root = new TreeItem("", null);
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.setRoot(root);
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        //root.removeAllChildren();
        //root.add(new TreeItem("another_child"));
        //expandRow(0);
        
        itemCache = new ArrayList<TreeItem>();
        
        setRootVisible(false);
        setCellRenderer(new TreeItemRenderer());
        
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                currentItemChanged();
            }
        });
        
        addTreeWillExpandListener(new TreeWillExpandListener () {

            @Override
            public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
                boolean expand = itemExpanded((TreeItem)evt.getPath().getLastPathComponent());
                if(!expand){
                    throw new ExpandVetoException(evt);
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent evt) throws ExpandVetoException {
                boolean collapse = itemCollapsed((TreeItem)evt.getPath().getLastPathComponent());
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
    
    public void currentItemChanged(){
        
    }
    
    public boolean itemExpanded(TreeItem item){
        return true;
    }
        
    public boolean itemCollapsed(TreeItem item){
        return true;
    }
    
    public TreeItem getRoot(){
        return (TreeItem) getModel().getRoot();
    }
    
    public void clear(){
        topLevelItemCount = 0;
        itemCache.clear();
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        getRoot().removeAllChildren();
        model.reload(getRoot());
    }
    
    public void visualClear(){
        topLevelItemCount = 0;
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        getRoot().removeAllChildren();
        model.reload(getRoot());
    }
    
    public int getTopLevelItemCount(){
        return topLevelItemCount;
    }
    
    public void addTopLevelItem(TreeItem item){
        topLevelItemCount += 1;
        addItem(item, getRoot());
        //DefaultTreeModel model = (DefaultTreeModel)getModel();
        //model.insertNodeInto(item, getRoot(), getRoot().getChildCount());
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
    
    public void addItem(TreeItem item, TreeItem parentItem){
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.insertNodeInto(item, parentItem, parentItem.getChildCount());
        //model.reload(getRoot());
        if(itemCacheEnabled){
            itemCache.add(item);
        }
        
    }
    
    public void removeItem(TreeItem item){
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.removeNodeFromParent(item);
        //model.reload(getRoot());
        if(itemCacheEnabled){
            itemCache.remove(item);
        }
        
        for(int i=0; i<item.getChildCount(); i++){
            removeItem((TreeItem) item.getChildAt(i));
        }
    }
    
    public List<TreeItem> getAllItems(){
        return new ArrayList<TreeItem>(itemCache);
    }
    /*
    public List<TreeItem> getAllItems(){
        List<TreeItem> result = new ArrayList<TreeItem>();
        getAllItemsRec(getRoot(), result);
        return result;
    }
    
    private void getAllItemsRec(TreeItem parentItem, List<TreeItem> result){
        for(int i=0; i<parentItem.getChildCount(); i++){
            TreeItem item = (TreeItem) parentItem.getChildAt(i);
            result.add(item);
            getAllItemsRec(item, result);
        }
    }*/
    
    public void setItemExpanded(TreeItem item){
        expandPath(new TreePath(item.getPath()));
    }
    
    public void setItemCollapsed(TreeItem item){
        collapsePath(new TreePath(item.getPath()));
    }
    
    
  
}
