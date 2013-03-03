package lib.editor.ui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetan
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        toolBarNewProject = new javax.swing.JButton();
        toolBarOpenProject = new javax.swing.JButton();
        toolBarSaveProject = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        statusBarLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        fileMenuNewProject = new javax.swing.JMenuItem();
        fileMenuOpenProject = new javax.swing.JMenuItem();
        fileMenuSaveProject = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        fileMenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        toolBarNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        toolBarNewProject.setToolTipText("Create a new project.");
        toolBarNewProject.setFocusable(false);
        toolBarNewProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarNewProject.setMaximumSize(null);
        toolBarNewProject.setMinimumSize(null);
        toolBarNewProject.setPreferredSize(new java.awt.Dimension(28, 28));
        toolBarNewProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarNewProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolBarNewProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolBarNewProjectMouseExited(evt);
            }
        });
        jToolBar1.add(toolBarNewProject);

        toolBarOpenProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        toolBarOpenProject.setToolTipText("Open an existing project.");
        toolBarOpenProject.setFocusable(false);
        toolBarOpenProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarOpenProject.setMaximumSize(null);
        toolBarOpenProject.setMinimumSize(null);
        toolBarOpenProject.setPreferredSize(new java.awt.Dimension(28, 28));
        toolBarOpenProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarOpenProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolBarOpenProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolBarOpenProjectMouseExited(evt);
            }
        });
        jToolBar1.add(toolBarOpenProject);

        toolBarSaveProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        toolBarSaveProject.setToolTipText("Save the current project.");
        toolBarSaveProject.setFocusable(false);
        toolBarSaveProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarSaveProject.setPreferredSize(new java.awt.Dimension(28, 28));
        toolBarSaveProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarSaveProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolBarSaveProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolBarSaveProjectMouseExited(evt);
            }
        });
        jToolBar1.add(toolBarSaveProject);
        jToolBar1.add(jSeparator1);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cut.png"))); // NOI18N
        jButton4.setToolTipText("Cut the selection and put it on the clipboard.");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/copy.png"))); // NOI18N
        jButton5.setToolTipText("Copy the selection and put it on the clipboard.");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/paste.png"))); // NOI18N
        jButton6.setToolTipText("Insert clipboard contents.");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/delete.png"))); // NOI18N
        jButton7.setToolTipText("Delete the selection.");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);
        jToolBar1.add(jSeparator2);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        jButton8.setToolTipText("Undo the last action.");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        jButton9.setToolTipText("Redo the last undo action.");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton9);
        jToolBar1.add(jSeparator3);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setMaximumSize(null);
        jPanel1.setMinimumSize(null);
        jPanel1.setPreferredSize(new java.awt.Dimension(35, 24));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(statusBarLabel);
        statusBarLabel.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        fileMenuNewProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        fileMenuNewProject.setText("New Project...");
        fileMenuNewProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuNewProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuNewProjectMouseExited(evt);
            }
        });
        fileMenuNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuNewProjectActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuNewProject);

        fileMenuOpenProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuOpenProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        fileMenuOpenProject.setText("Open project...");
        fileMenuOpenProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuOpenProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuOpenProjectMouseExited(evt);
            }
        });
        jMenu1.add(fileMenuOpenProject);

        fileMenuSaveProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuSaveProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        fileMenuSaveProject.setText("Save project");
        fileMenuSaveProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuSaveProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuSaveProjectMouseExited(evt);
            }
        });
        jMenu1.add(fileMenuSaveProject);
        jMenu1.add(jSeparator4);

        fileMenuExit.setText("Exit");
        fileMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuExitMouseExited(evt);
            }
        });
        fileMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuExitActionPerformed(evt);
            }
        });
        jMenu1.add(fileMenuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        jMenuItem5.setText("Undo");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        jMenuItem6.setText("Redo");
        jMenu2.add(jMenuItem6);
        jMenu2.add(jSeparator5);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cut.png"))); // NOI18N
        jMenuItem7.setText("Cut");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/copy.png"))); // NOI18N
        jMenuItem8.setText("Copy");
        jMenu2.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/paste.png"))); // NOI18N
        jMenuItem9.setText("Paste");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/delete.png"))); // NOI18N
        jMenuItem10.setText("Delete");
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileMenuNewProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuNewProjectMouseEntered
        statusBarLabel.setText("Create a new project.");
    }//GEN-LAST:event_fileMenuNewProjectMouseEntered

    private void fileMenuNewProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuNewProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_fileMenuNewProjectMouseExited

    private void fileMenuOpenProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuOpenProjectMouseEntered
        statusBarLabel.setText("Open an existing project.");
    }//GEN-LAST:event_fileMenuOpenProjectMouseEntered

    private void fileMenuOpenProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuOpenProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_fileMenuOpenProjectMouseExited

    private void fileMenuSaveProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuSaveProjectMouseEntered
        statusBarLabel.setText("Save the current project.");
    }//GEN-LAST:event_fileMenuSaveProjectMouseEntered

    private void fileMenuSaveProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuSaveProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_fileMenuSaveProjectMouseExited

    private void fileMenuExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuExitMouseEntered
        statusBarLabel.setText("Exit the application.");
    }//GEN-LAST:event_fileMenuExitMouseEntered

    private void fileMenuExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuExitMouseExited
        
    }//GEN-LAST:event_fileMenuExitMouseExited

    private void toolBarNewProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarNewProjectMouseEntered
        statusBarLabel.setText("Create a new project.");
    }//GEN-LAST:event_toolBarNewProjectMouseEntered

    private void toolBarNewProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarNewProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_toolBarNewProjectMouseExited

    private void toolBarOpenProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarOpenProjectMouseEntered
        statusBarLabel.setText("Open an existing project.");
    }//GEN-LAST:event_toolBarOpenProjectMouseEntered

    private void toolBarOpenProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarOpenProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_toolBarOpenProjectMouseExited

    private void toolBarSaveProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarSaveProjectMouseEntered
        statusBarLabel.setText("Save the current project.");
    }//GEN-LAST:event_toolBarSaveProjectMouseEntered

    private void toolBarSaveProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarSaveProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_toolBarSaveProjectMouseExited

    private void fileMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_fileMenuExitActionPerformed

    private void fileMenuNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuNewProjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileMenuNewProjectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem fileMenuExit;
    private javax.swing.JMenuItem fileMenuNewProject;
    private javax.swing.JMenuItem fileMenuOpenProject;
    private javax.swing.JMenuItem fileMenuSaveProject;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel statusBarLabel;
    private javax.swing.JButton toolBarNewProject;
    private javax.swing.JButton toolBarOpenProject;
    private javax.swing.JButton toolBarSaveProject;
    // End of variables declaration//GEN-END:variables
}
