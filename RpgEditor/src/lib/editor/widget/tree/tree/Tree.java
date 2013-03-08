/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

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
        /*
        addTreeExpansionListener(new javax.swing.event.TreeExpansionListener() {
            public void treeCollapsed(javax.swing.event.TreeExpansionEvent evt) {
                itemCollapsed((TreeItem)evt.getPath().getLastPathComponent());
            }
            public void treeExpanded(javax.swing.event.TreeExpansionEvent evt) {
                itemExpanded((TreeItem)evt.getPath().getLastPathComponent());
            }
        });*/
        
        addTreeWillExpandListener(new TreeWillExpandListener () {

            @Override
            public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
                boolean Expand = itemExpanded((TreeItem)evt.getPath().getLastPathComponent());
                if(!Expand){
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
    }
    
    public List<TreeItem> getAllItems(){
        return itemCache;
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
