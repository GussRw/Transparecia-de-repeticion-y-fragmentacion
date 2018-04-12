/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Career;
import models.Location;
import models.Score;
import models.Student;
import models.StudentA;
import models.StudentB;
import models.Subject;
import models.Table;
import models.TableLocation;
import models.Teacher;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import plugins.dijkstra.DijkstraAlgorithm;
import plugins.dijkstra.Edge;
import plugins.dijkstra.Graph;
import plugins.dijkstra.Vertex;
/**
 *
 * @author guss
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public static ArrayList<Location> locations;
    public static ArrayList<Table> tables;
    
    public static List<Vertex> nodes;
    public static List<Edge> edges;
    public Main() 
    {
        initComponents();
        Table career = new Table("Carreras",new Career().getFillable());
        Table subject = new Table("Materias",new Subject().getFillable());
        Table teacher =  new Table("Maestros",new Teacher().getFillable());
        Table score = new Table("Calificaciones",new Score().getFillable());
        Table student = new Table("Alumnos",new Student().getFillable());
        Table student1a = new Table("Alumnos1a",new StudentA("Lic").getFillable());
        Table student1b = new Table("Alumnos1b",new StudentB("Lic").getFillable());
        Table student2a = new Table("Alumnos2a",new StudentA("Ing").getFillable());
        Table student2b = new Table("Alumnos2b",new StudentB("Ing").getFillable());
        
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 1; i <= 9; i++) {
            Vertex location = new Vertex("L" + i, "L" + i);
            nodes.add(location);
        }
        
        tables = new ArrayList<Table>()
        {{
            
            add(career);
            add(subject); 
            add(teacher);
            add(score);  
            add(student);
            add(student1a);
            add(student1b);
            add(student2a);
            add(student2b);
        }};
        
        locations = new ArrayList<Location>(this.defaultL());

        
        //showResult(new ArrayList<TableLocation>());
    }
    
    public ArrayList<Location> defaultL()
    {
        Table career = new Table("Carreras",new Career().getFillable());
        Table subject = new Table("Materias",new Subject().getFillable());
        Table teacher =  new Table("Maestros",new Teacher().getFillable());
        Table score = new Table("Calificaciones",new Score().getFillable());
        Table student = new Table("Alumnos",new Student().getFillable());
        Table student1a = new Table("Alumnos1a",new StudentA("Lic").getFillable());
        Table student1b = new Table("Alumnos1b",new StudentB("Lic").getFillable());
        Table student2a = new Table("Alumnos2a",new StudentA("Ing").getFillable());
        Table student2b = new Table("Alumnos2b",new StudentB("Ing").getFillable());
        
        
        ArrayList<Location> ll = new ArrayList<Location>()
        {{
            add(new Location("L1", true, new ArrayList<Table>(){{  add(subject);   add(score);  add(student1a); }},new String[]{"L2","L5"}));
            add(new Location("L2", true, new ArrayList<Table>(){{  add(career);    add(student1b);  }}, new String[]{"L1","L3"}));
            add(new Location("L3", true, new ArrayList<Table>(){{  add(teacher);   add(student2a);  }}, new String[]{"L2","L6"}));
            add(new Location("L4", true, new ArrayList<Table>(){{  add(teacher);   add(student2b);  }}, new String[]{"L5","L7"}));
            add(new Location("L5", true, new ArrayList<Table>(){{  add(subject);   add(student2b);  }}, new String[]{"L1","L6","L9","L4"}));
            add(new Location("L6", true, new ArrayList<Table>(){{  add(score);     add(student1a);  }}, new String[]{"L3","L5"}));
            add(new Location("L7", true, new ArrayList<Table>(){{  add(subject);   add(score);      }}, new String[]{"L4","L8"}));
            add(new Location("L8", true, new ArrayList<Table>(){{  add(teacher);   add(student2a);  }}, new String[]{"L7","L9"}));
            add(new Location("L9", true, new ArrayList<Table>(){{  add(career);    add(student1b);  }}, new String[]{"L5","L8"}));
        }};
        return ll;
    }
    public static ArrayList<String> linkLocalLocations(String name,ArrayList<String> temp)
    {   
        temp.add(name);
        for (String connected : directlyConnectedLocations(name))
        {  
            if (locations.get(getLocationIndex(connected)).isStatus() && temp.indexOf(connected) == -1)
            {       
                if (linkLocalLocations(connected,temp) != temp)
                {
                    temp.addAll(linkLocalLocations(connected,temp));
                }  
            }
            
        }
        return temp;      
    }
    
    public static void showResult(ArrayList<TableLocation> tableLocations)
    {
        String locationsSelecteds ="Localidades Usadas: ";

        sourceUsed ="La consulta se completo correctamente\n";
        for (TableLocation tl:tableLocations) {
            locationsSelecteds+=tl.location+" ";
            setTablesBusy(tl.location,tl.table);
            ArrayList<String> paths = new ArrayList<String>();
            int cont = 1;
            String p = locationSelected.getText();
            for (Vertex vert : getEdges(locationSelected.getText(),tl.location)) {
                //System.out.println(vert.getName());
                if (cont==1) {
                    p+="-"+vert.getName();
                    paths.add(p);
                    cont =0;
                    p=vert.getName();
                }else
                {
                    p=vert.getName();
                   
                }
                
                cont++;
                
            }
                       
        }
        JOptionPane.showMessageDialog(null, sourceUsed);
        
    }
    
    public static ArrayList<String> distanceLocation(String name,String name2,ArrayList<String> temp,int distance)
    {   
        temp.add(name);
        for (String connected : directlyConnectedLocations(name))
        {  
            if (locations.get(getLocationIndex(connected)).isStatus() && temp.indexOf(connected) == -1)
            {       
                distance ++;
                if (connected==name2) {
                    //System.out.println(distance);
                    return temp; 
                }
                if (distanceLocation(connected,name2,temp,distance) != temp)
                {
                    temp.addAll(distanceLocation(connected,name2,temp,distance));
                }  
            }
            
        }
        
             return temp; 
    }
    
    public static ArrayList<String> directlyConnectedLocations(String name)
    {
        Location location =  locations.get(getLocationIndex(name));
        ArrayList<String> enableConnectedLocations = new ArrayList<String>();
        for (String connected : location.getLocationsConnected())
        {
            if (locations.get(getLocationIndex(connected)).isStatus())
            {
                enableConnectedLocations.add(connected);
            }
        }
        return enableConnectedLocations;
    }
    
    public static  int getLocationIndex(String name)
    {
        for (Location location: locations)
        {
            if (location.getName().equals(name))
            {
                return locations.indexOf(location);
            }
        }
        return -1;
    }
    
    public static int getTableIndex(String name)
    {
        for (Table table: tables)
        {
            if (table.getName().equals(name))
            {
                return tables.indexOf(table);
            }
        }
        return -1;
    }
    
    public void setEnableLocation(String name, int status)
    {
        if (status==1)
            locations.get(getLocationIndex(name)).setStatus(true);
        else
            locations.get(getLocationIndex(name)).setStatus(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        databaseL9 = new javax.swing.JCheckBox();
        databaseL8 = new javax.swing.JCheckBox();
        databaseL7 = new javax.swing.JCheckBox();
        databaseL4 = new javax.swing.JCheckBox();
        databaseL1 = new javax.swing.JCheckBox();
        databaseL2 = new javax.swing.JCheckBox();
        databaseL3 = new javax.swing.JCheckBox();
        databaseL6 = new javax.swing.JCheckBox();
        databaseL5 = new javax.swing.JCheckBox();
        jLabell2 = new javax.swing.JLabel();
        jlabel67 = new javax.swing.JLabel();
        jsl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        L1 = new javax.swing.JLabel();
        L2 = new javax.swing.JLabel();
        L3 = new javax.swing.JLabel();
        L4 = new javax.swing.JLabel();
        L5 = new javax.swing.JLabel();
        L6 = new javax.swing.JLabel();
        L7 = new javax.swing.JLabel();
        L8 = new javax.swing.JLabel();
        L9 = new javax.swing.JLabel();
        doQuery = new javax.swing.JButton();
        locationSelected = new javax.swing.JLabel();
        locationsBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transparencia de repeticion y fragmentacion");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        databaseL9.setSelected(true);
        databaseL9.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL9.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL9.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL9ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, -1, -1));

        databaseL8.setSelected(true);
        databaseL8.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL8.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL8.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL8ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, -1, -1));

        databaseL7.setSelected(true);
        databaseL7.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL7.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL7.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL7ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, -1, -1));

        databaseL4.setSelected(true);
        databaseL4.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL4.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL4.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL4.setRequestFocusEnabled(false);
        databaseL4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL4ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        databaseL1.setSelected(true);
        databaseL1.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL1.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL1.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL1ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        databaseL2.setSelected(true);
        databaseL2.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL2.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL2.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL2ItemStateChanged(evt);
            }
        });
        databaseL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseL2ActionPerformed(evt);
            }
        });
        getContentPane().add(databaseL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        databaseL3.setSelected(true);
        databaseL3.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL3.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL3.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL3ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, -1, -1));

        databaseL6.setSelected(true);
        databaseL6.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL6.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL6.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL6.setRequestFocusEnabled(false);
        databaseL6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL6ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

        databaseL5.setSelected(true);
        databaseL5.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL5.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL5.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL5.setRequestFocusEnabled(false);
        databaseL5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL5ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        jLabell2.setText("Carreras");
        getContentPane().add(jLabell2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        jlabel67.setText("Alumnos1b");
        getContentPane().add(jlabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        jsl.setText("Califica");
        getContentPane().add(jsl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel2.setText("Seleccione la localidad dando click en ella.    Habilite y Deshabilite marcando el recuadro");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, -1, -1));

        jLabel1.setText("Localidad actual: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jlabel.setText("Alumnos1a");
        getContentPane().add(jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel7.setText("Materias");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel5.setText("Califica");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, -1, -1));

        jLabel6.setText("Alumnos1a");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, -1, -1));

        jLabel8.setText("Carrera");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 490, -1, -1));

        jLabel9.setText("Alumnos1b");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, -1, -1));

        jLabel10.setText("Materias");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        jLabel11.setText("Alumnos2b");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, -1, -1));

        jLabel12.setText("Maestros");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, -1, -1));

        jLabel13.setText("Alumnos2a");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, -1, -1));

        jLabel14.setText("Maestros");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel15.setText("Alumnos2b");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel16.setText("Califica");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        jLabel18.setText("Maestros");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, -1, -1));

        jLabel19.setText("Materias");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, -1, -1));

        jLabel17.setText("Alumnos2a");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, -1));

        L1.setBackground(new java.awt.Color(1, 1, 1));
        L1.setLabelFor(L1);
        L1.setText("L1");
        L1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L1MouseClicked(evt);
            }
        });
        getContentPane().add(L1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 60, 60));

        L2.setText("L2");
        L2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L2MouseClicked(evt);
            }
        });
        getContentPane().add(L2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 60, 60));

        L3.setText("L3");
        L3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L3MouseClicked(evt);
            }
        });
        getContentPane().add(L3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 60, 60));

        L4.setText("L4");
        L4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L4MouseClicked(evt);
            }
        });
        getContentPane().add(L4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 70, 60));

        L5.setText("L5");
        L5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L5MouseClicked(evt);
            }
        });
        getContentPane().add(L5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 60, 60));

        L6.setText("L6");
        L6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L6MouseClicked(evt);
            }
        });
        getContentPane().add(L6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 60, 60));

        L7.setText("L7");
        L7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L7MouseClicked(evt);
            }
        });
        getContentPane().add(L7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 60, 60));

        L8.setText("L8");
        L8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L8MouseClicked(evt);
            }
        });
        getContentPane().add(L8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 60, 60));

        L9.setText("L9");
        L9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L9MouseClicked(evt);
            }
        });
        getContentPane().add(L9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 60, 60));

        doQuery.setText("Realizar Consulta");
        doQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doQueryActionPerformed(evt);
            }
        });
        getContentPane().add(doQuery, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, 40));

        locationSelected.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        locationSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        locationSelected.setText("L1");
        locationSelected.setMaximumSize(new java.awt.Dimension(20, 20));
        locationSelected.setMinimumSize(new java.awt.Dimension(20, 20));
        locationSelected.setPreferredSize(new java.awt.Dimension(20, 20));
        locationSelected.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                locationSelectedPropertyChange(evt);
            }
        });
        getContentPane().add(locationSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 20, 20));

        locationsBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        locationsBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Localidades.png"))); // NOI18N
        locationsBackground.setRequestFocusEnabled(false);
        getContentPane().add(locationsBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 755, 523));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void L4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L4MouseClicked
    {//GEN-HEADEREND:event_L4MouseClicked
            locationSelected.setText("L4");
    }//GEN-LAST:event_L4MouseClicked

    private void L5MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L5MouseClicked
    {//GEN-HEADEREND:event_L5MouseClicked
            locationSelected.setText("L5");
    }//GEN-LAST:event_L5MouseClicked

    private void L6MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L6MouseClicked
    {//GEN-HEADEREND:event_L6MouseClicked
            locationSelected.setText("L6");
    }//GEN-LAST:event_L6MouseClicked

    private void L7MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L7MouseClicked
    {//GEN-HEADEREND:event_L7MouseClicked
            locationSelected.setText("L7");
    }//GEN-LAST:event_L7MouseClicked

    private void L8MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L8MouseClicked
    {//GEN-HEADEREND:event_L8MouseClicked
            locationSelected.setText("L8");
    }//GEN-LAST:event_L8MouseClicked

    private void L9MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L9MouseClicked
    {//GEN-HEADEREND:event_L9MouseClicked
            locationSelected.setText("L9");
    }//GEN-LAST:event_L9MouseClicked

    public static String sourceUsed ="La consulta se completo correctamente\n";
    public static void setTablesBusy(String location, String table)
    {
        sourceUsed += location + ": " + table + "\n";         
    }
    

    private void locationSelectedPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_locationSelectedPropertyChange
    {//GEN-HEADEREND:event_locationSelectedPropertyChange
        //showResult(new ArrayList<TableLocation>());        
        if (locationSelected.getText().equals("Ø"))
            doQuery.setEnabled(false);
        else
            doQuery.setEnabled(true);
        
    }//GEN-LAST:event_locationSelectedPropertyChange

    private void databaseL9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL9ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL9ItemStateChanged

    private void databaseL8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL8ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL8ItemStateChanged

    private void databaseL7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL7ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL7ItemStateChanged

    private void databaseL4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL4ItemStateChanged

    private void databaseL1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL1ItemStateChanged

    private void databaseL2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL2ItemStateChanged

    private void databaseL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databaseL2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL2ActionPerformed

    private void databaseL3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL3ItemStateChanged

    private void databaseL6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL6ItemStateChanged

    private void databaseL5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_databaseL5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseL5ItemStateChanged

    private void doQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doQueryActionPerformed
        // TODO add your handling code here:
        createEdges();
        setDatabases();
        CreateQuery selectTables = new CreateQuery(this,true);
        selectTables.setVisible(true);
        
        
    }//GEN-LAST:event_doQueryActionPerformed

    private void L1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L1MouseClicked
        locationSelected.setText("L1");
    }//GEN-LAST:event_L1MouseClicked

    private void L2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L2MouseClicked
        locationSelected.setText("L2");
    }//GEN-LAST:event_L2MouseClicked

    private void L3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L3MouseClicked
        locationSelected.setText("L3");
    }//GEN-LAST:event_L3MouseClicked
    public void removeDataBase(String loca)
    {
        Location location = new Location(loca,true,new ArrayList<Table>(),new String[]{""});
        for(Location l : locations){
            if(l.getName().equals(loca))
                location=l;
        }
        locations.remove(location);
        location.setTables(new ArrayList<Table>());
        locations.add(location);        
    }
    public void setDatabases()
    {
        
        //System.out.println(ld);
        locations.clear();
        locations.addAll(this.defaultL());
        if (!databaseL1.isSelected()){
            removeDataBase("L1");
            System.out.println("nosta la 1");}
        if (!databaseL2.isSelected())
            removeDataBase("L2");
        if (!databaseL3.isSelected())
            removeDataBase("L3");
        if (!databaseL4.isSelected())
            removeDataBase("L4");
        if (!databaseL5.isSelected())
            removeDataBase("L5");
        if (!databaseL6.isSelected())
            removeDataBase("L6");
        if (!databaseL7.isSelected())
            removeDataBase("L7");
        if (!databaseL8.isSelected())
            removeDataBase("L8");
        if (!databaseL9.isSelected())
            removeDataBase("L9");
        
        System.out.println(locations);
    }
    public void createEdges()
    {
        edges.removeAll(edges);

            addLane("L1-L2", 0, 1, 1);    addLane("L2-L1", 1, 0, 1);

            addLane("L2-L3", 1, 2, 1);    addLane("L3-L2", 2, 1, 1);
        
            addLane("L1-L5", 0, 4, 1);    addLane("L5-L1", 4, 0, 1);
        
            addLane("L3-L6", 2, 5, 1);    addLane("L6-L3", 5, 2, 1);
        
            addLane("L4-L5", 3, 4, 1);    addLane("L5-L4", 4, 3, 1);

            addLane("L5-L6", 4, 5, 1);    addLane("L6-L5", 5, 4, 1);
        
            addLane("L4-L7", 3, 6, 1);    addLane("L7-L4", 6, 3, 1);
        
            addLane("L5-L9", 4, 8, 1);    addLane("L9-L5", 8, 4, 1);
        
            addLane("L7-L8", 6, 7, 1);    addLane("L8-L7", 7, 6, 1);
        
            addLane("L8-L9", 7, 8, 1);    addLane("L9-L8", 8, 7, 1);
        
    }
    
    public static int getDistance(String origin, String destination)
    {
        if (origin.equals(destination)) 
            return 1;
        
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(getNumberLocation(origin)-1));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(getNumberLocation(destination)-1));
        try{
        assertNotNull(path);
        assertTrue(path.size() > 0);
        }catch(AssertionError ex)
        {
            return 1000;
        }
        
        return path.size();
    }
    
    public static LinkedList<Vertex> getEdges(String origin, String destination)
    {
        if (origin.equals(destination)) 
            new LinkedList<Vertex>().add(new Vertex(origin,origin));
        
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(getNumberLocation(origin)-1));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(getNumberLocation(destination)-1));
        try{
        assertNotNull(path);
        assertTrue(path.size() > 0);
        }catch(AssertionError ex)
        {
            return new LinkedList<Vertex>();
            
        }
        
        return path;
    }
    
    public static int getNumberLocation(String location)
    {
        switch(location)
        {
            case "L1": return 1;
            case "L2": return 2;
            case "L3": return 3;
            case "L4": return 4;
            case "L5": return 5;
            case "L6": return 6;
            case "L7": return 7;
            case "L8": return 8;
            case "L9": return 9;
        }
        return -1;
    }
    

    
    static void addLane(String laneId, int sourceLocNo, int destLocNo,
            int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L3;
    private javax.swing.JLabel L4;
    private javax.swing.JLabel L5;
    private javax.swing.JLabel L6;
    private javax.swing.JLabel L7;
    private javax.swing.JLabel L8;
    private javax.swing.JLabel L9;
    private javax.swing.JCheckBox databaseL1;
    private javax.swing.JCheckBox databaseL2;
    private javax.swing.JCheckBox databaseL3;
    private javax.swing.JCheckBox databaseL4;
    private javax.swing.JCheckBox databaseL5;
    private javax.swing.JCheckBox databaseL6;
    private javax.swing.JCheckBox databaseL7;
    private javax.swing.JCheckBox databaseL8;
    private javax.swing.JCheckBox databaseL9;
    private javax.swing.JButton doQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabell2;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel67;
    private javax.swing.JLabel jsl;
    public static javax.swing.JLabel locationSelected;
    private javax.swing.JLabel locationsBackground;
    // End of variables declaration//GEN-END:variables
}
