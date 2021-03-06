/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import views.CreateQuery;
import views.Main;

/**
 *
 * @author guss
 */
public class SelectTables extends javax.swing.JPanel
{

    /**
     * Creates new form SelectTables
     */
    ArrayList<JButton> tables = new ArrayList<JButton>()
    {{
        add(new JButton("Carreras"));
        add(new JButton("Materias"));
        add(new JButton("Maestros"));
        add(new JButton("Calificaciones"));
        add(new JButton("Alumnos"));
    }};
    public SelectTables()
    {
        initComponents();
        selectTablesPanel.setLayout(new FlowLayout());
        selectedTablesPanel.setLayout(new FlowLayout());
        for (JButton button: tables)
        {
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    if ( ((JButton)e.getSource()).getParent().equals(selectTablesPanel) )
                    {
                        selectedTablesPanel.add(((JButton)e.getSource()));
                        selectedTablesPanel.revalidate();
                        selectedTablesPanel.repaint();
                        selectTablesPanel.remove((JButton)e.getSource());
                        selectTablesPanel.revalidate();
                        selectTablesPanel.repaint();
                        
                    }else
                    {
                        selectedTablesPanel.remove(((JButton)e.getSource()));
                        selectedTablesPanel.revalidate();
                        selectedTablesPanel.repaint();
                        selectTablesPanel.add((JButton)e.getSource());
                        selectTablesPanel.revalidate();
                        selectTablesPanel.repaint();
                    }
                    enableNextPanel();
                }
            });
        }
        for (JButton button: tables)
        {
            selectTablesPanel.add(button);   
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectedTablesPanel = new javax.swing.JPanel();
        selectTablesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        nextPanel = new javax.swing.JButton();

        setAlignmentX(0.0F);
        setMaximumSize(new java.awt.Dimension(700, 300));
        setMinimumSize(new java.awt.Dimension(700, 300));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        selectedTablesPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectedTablesPanel.setMaximumSize(new java.awt.Dimension(520, 70));
        selectedTablesPanel.setMinimumSize(new java.awt.Dimension(520, 70));
        selectedTablesPanel.setPreferredSize(new java.awt.Dimension(520, 70));

        javax.swing.GroupLayout selectedTablesPanelLayout = new javax.swing.GroupLayout(selectedTablesPanel);
        selectedTablesPanel.setLayout(selectedTablesPanelLayout);
        selectedTablesPanelLayout.setHorizontalGroup(
            selectedTablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        selectedTablesPanelLayout.setVerticalGroup(
            selectedTablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        selectTablesPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectTablesPanel.setMaximumSize(new java.awt.Dimension(520, 70));
        selectTablesPanel.setMinimumSize(new java.awt.Dimension(520, 70));
        selectTablesPanel.setPreferredSize(new java.awt.Dimension(520, 70));

        javax.swing.GroupLayout selectTablesPanelLayout = new javax.swing.GroupLayout(selectTablesPanel);
        selectTablesPanel.setLayout(selectTablesPanelLayout);
        selectTablesPanelLayout.setHorizontalGroup(
            selectTablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        selectTablesPanelLayout.setVerticalGroup(
            selectTablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setText("Seleccionar Tablas :");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Tablas Seleccionadas: ");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/back.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        nextPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/next.png"))); // NOI18N
        nextPanel.setText("Siguiente");
        nextPanel.setEnabled(false);
        nextPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(447, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextPanel)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(selectedTablesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(selectTablesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                    .addGap(24, 24, 24)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextPanel)
                    .addComponent(jButton2))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(selectTablesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(selectedTablesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        ((JDialog) this.getRootPane().getParent()).dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void nextPanelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nextPanelActionPerformed
    {//GEN-HEADEREND:event_nextPanelActionPerformed
        CreateQuery.selectedTables.removeAll(CreateQuery.selectedTables);
        for (JButton button: tables)
        {
            if (button.getParent().equals(selectedTablesPanel))
            {
                CreateQuery.selectedTables.add(button.getText());
            }
        }
        CreateQuery.mainPanel.removeAll();
        CreateQuery.mainPanel.add(CreateQuery.selectColumns);
        CreateQuery.mainPanel.revalidate();
        CreateQuery.mainPanel.repaint();
    }//GEN-LAST:event_nextPanelActionPerformed

    
    private void formPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_formPropertyChange
    {//GEN-HEADEREND:event_formPropertyChange
        ((JDialog) this.getRootPane().getParent()).setTitle("Seleccionar Tablas");
        selectTablesPanel.removeAll();
        selectedTablesPanel.removeAll();
        for (JButton button: tables)
        {
            if (CreateQuery.selectedTables.indexOf(button.getText()) != -1)
            {
                selectedTablesPanel.add(button);   
            }else
                selectTablesPanel.add(button); 
            
        }
    }//GEN-LAST:event_formPropertyChange

    public void enableNextPanel()
    {
        int cont = 0;
        for (JButton button: tables)
        {
            if (button.getParent().equals(selectedTablesPanel))
               cont++;
        }
        if (cont>0)
            nextPanel.setEnabled(true);
        else
            nextPanel.setEnabled(false);
            
        
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton nextPanel;
    public javax.swing.JPanel selectTablesPanel;
    public javax.swing.JPanel selectedTablesPanel;
    // End of variables declaration//GEN-END:variables
}
