
package views.panels;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import models.Location;
import models.Table;
import models.TableLocation;
import views.CreateQuery;
import views.Main;

/**
 *
 * @author guss
 */
public class SelectCondition extends javax.swing.JPanel
{
    public SelectCondition()
    {
        initComponents();
    }
    
    public void updateTables(ArrayList<String> selectedTables)
    {
        tablesBox.removeAllItems();
        for(String table : selectedTables)
        {
            tablesBox.addItem(table);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        tablesBox = new javax.swing.JComboBox<>();
        columnBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        conditionBox = new javax.swing.JComboBox<>();
        conditionText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(700, 300));
        setMinimumSize(new java.awt.Dimension(700, 300));
        setPreferredSize(new java.awt.Dimension(700, 300));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setMaximumSize(new java.awt.Dimension(700, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 300));

        jButton1.setText("Finalizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablesBox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tablesBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tablesBoxItemStateChanged(evt);
            }
        });

        columnBox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        columnBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Columna");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tabla");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Condicion");

        conditionBox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        conditionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "!=", ">", ">=", "<", "<=" }));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Operador");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tablesBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(columnBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conditionBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conditionText))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(6, 6, 6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tablesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(columnBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(conditionText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(conditionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablesBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tablesBoxItemStateChanged
        columnBox.removeAllItems();
        for (Table table : CreateQuery.selectedColumns) 
        {
            if (evt.getItem().toString()==table.getName()) 
            {
                for (String column : table.getFillable()) 
                {
                    columnBox.addItem(column);
                }
            }
        }
    }//GEN-LAST:event_tablesBoxItemStateChanged

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        ((JDialog) this.getRootPane().getParent()).setTitle("Seleccionar Condicion");
    }//GEN-LAST:event_formPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean success = true;
        ArrayList<Table> successTable = new ArrayList<Table>();
        ArrayList<String> successName = new ArrayList<String>();
        
        ArrayList<String> tables = new ArrayList<String>();
        ArrayList<String> locations = new ArrayList<String>();
        
        
        ArrayList<TableLocation> tableLocation = new ArrayList<TableLocation>();
        int countColumns = 0;
        for(Table table : CreateQuery.selectedColumns)
        {
            //System.out.println(table.getName() + ": " +table.getFillable().toString());
            for(String st : table.getFillable()) 
            {
                countColumns++;
            }
        }
        for(Table tableSelected :CreateQuery.selectedColumns)
        {
            
            for (Location location : Main.locations) 
            {
                if (Main.linkLocalLocations(Main.locationSelected.getText(),new ArrayList<String>()).indexOf(location.getName())!=-1)
                {
                    
                    tables :for (Table table : location.getTables()) 
                    {
                        
                        String nameTable = tableSelected.getName();
                        if (    tableSelected.getName().equals("Alumnos") &&
                                tablesBox.getSelectedItem().toString()=="Alumnos" &&
                                columnBox.getSelectedItem().toString()=="titulo") 
                        {
                            tableSelected.getFillable().add(conditionText.getText());
                            switch(conditionBox.getSelectedIndex())
                            {
                                case 0:
                                    if (conditionText.getText().equalsIgnoreCase("lic")) 
                                        nameTable = "Alumnos1";
                                    if (conditionText.getText().equalsIgnoreCase("ing")) 
                                        nameTable = "Alumnos2";   
                                break;
                                case 1:
                                    if (conditionText.getText().equalsIgnoreCase("lic")) 
                                        nameTable = "Alumnos2";
                                    if (conditionText.getText().equalsIgnoreCase("ing")) 
                                        nameTable = "Alumnos1";   
                                break;
                                default:
                                    nameTable = "Alumnos3";  
                                break;
                                        
                            }
                            
                        }
                        
                        if (table.getName().indexOf(nameTable) != -1) 
                        {
//                            successTable.add(new Table(table.getName(),new ArrayList<String>()));
//                            if (successName.indexOf(table.getName()) != -1) 
//                            continue tables;
//                            
//                            successName.add(table.getName());
                            for(String column : tableSelected.getFillable())
                            {
                                if (table.getFillable().indexOf(column) != -1) 
                                {
                                    System.out.println(tableSelected.getName()+":"+ column+" en "+ location.getName()+" : "+table.getName());
                                    tables.add(table.getName());
                                    locations.add(location.getName());
                                    countColumns--;
                                    for (Table t : successTable) 
                                    {
                                        
                                        if (t.getName()==table.getName()) 
                                        {
                                            Table tab = successTable.get(successTable.indexOf(t));
                                            ArrayList<String> fill = tab.getFillable();
                                            fill.add(column);
                                            tab.setFillable(fill);
                                            successTable.set(successTable.indexOf(t),tab);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if (countColumns<=0) {
            System.out.println("La consulta se Completo correctamente");
            int cont = 0;
            for(String table : tables)
            {
                int index = -1;
                for(TableLocation tl : tableLocation)
                {
                    if(tl.table==table)
                        index= tableLocation.indexOf(tl);
                }
                if(index==-1)
                    tableLocation.add(new TableLocation(locations.get(cont),table));
                else
                {
                    if(Main.getDistance(Main.locationSelected.getText(), locations.get(cont))<
                       Main.getDistance(Main.locationSelected.getText(), tableLocation.get(index).location)){
                       
                    tableLocation.remove(index);
                    tableLocation.add(new TableLocation(locations.get(cont),table));
                    }
                }
                cont++;
            }
            Main.showResult(tableLocation);
            ((JDialog) this.getRootPane().getParent()).dispose();
        }else{
                JOptionPane.showMessageDialog(null, "No se puede completar la consulta");
                ((JDialog) this.getRootPane().getParent()).dispose();
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> columnBox;
    public static javax.swing.JComboBox<String> conditionBox;
    public static javax.swing.JTextField conditionText;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JComboBox<String> tablesBox;
    // End of variables declaration//GEN-END:variables
}
