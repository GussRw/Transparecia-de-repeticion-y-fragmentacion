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

        
        showResult(new ArrayList<TableLocation>());
        hideCheckTables();
        setSelection("L1");
        locationSelected.setVisible(false);
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
        
        L1S.setVisible(false);
        L2S.setVisible(false);
        L3S.setVisible(false);
        L4S.setVisible(false);
        L5S.setVisible(false);
        L6S.setVisible(false);
        L7S.setVisible(false);
        L8S.setVisible(false);
        L9S.setVisible(false);
        
        l1l2.setVisible(false);
        l1l5.setVisible(false);
        l2l3.setVisible(false);
        l3l6.setVisible(false);
        l4l5.setVisible(false);
        l5l6.setVisible(false);
        l4l7.setVisible(false);
        l5l9.setVisible(false);
        l7l8.setVisible(false);
        l8l9.setVisible(false);
        hideCheckTables();
        
        for (TableLocation tl:tableLocations) {
            switch(tl.location)
            {
                case "L1": L1S.setVisible(true); break;
                case "L2": L2S.setVisible(true); break;
                case "L3": L3S.setVisible(true); break;
                case "L4": L4S.setVisible(true); break;
                case "L5": L5S.setVisible(true); break;
                case "L6": L6S.setVisible(true); break;
                case "L7": L7S.setVisible(true); break;
                case "L8": L8S.setVisible(true); break;
                case "L9": L9S.setVisible(true); break;
            }
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
            
            for(String path:paths)
            {
                System.out.println(path);
                switch(path)
                {
                    case "L1-L2": l1l2.setVisible(true); break;
                    case "L2-L1": l1l2.setVisible(true); break;
                    case "L2-L3": l2l3.setVisible(true); break;
                    case "L3-L2": l2l3.setVisible(true); break;
                    case "L1-L5": l1l5.setVisible(true); break;
                    case "L5-L1": l1l5.setVisible(true); break;
                    case "L3-L6": l3l6.setVisible(true); break;
                    case "L6-L3": l3l6.setVisible(true); break;
                    case "L4-L5": l4l5.setVisible(true); break;
                    case "L5-L4": l4l5.setVisible(true); break;
                    case "L5-L6": l5l6.setVisible(true); break;
                    case "L6-L5": l5l6.setVisible(true); break;
                    case "L4-L7": l4l7.setVisible(true); break;
                    case "L7-L4": l4l7.setVisible(true); break;
                    case "L5-L9": l5l9.setVisible(true); break;
                    case "L9-L5": l5l9.setVisible(true); break;
                    case "L7-L8": l7l8.setVisible(true); break;
                    case "L8-L7": l7l8.setVisible(true); break;
                    case "L8-L9": l8l9.setVisible(true); break;
                    case "L9-L8": l8l9.setVisible(true); break;
                            
                }
            }
            
        }
        
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

        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        databaseL9 = new javax.swing.JCheckBox();
        databaseL8 = new javax.swing.JCheckBox();
        databaseL7 = new javax.swing.JCheckBox();
        databaseL4 = new javax.swing.JCheckBox();
        databaseL1 = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        databaseL2 = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        databaseL3 = new javax.swing.JCheckBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        databaseL6 = new javax.swing.JCheckBox();
        databaseL5 = new javax.swing.JCheckBox();
        jLabell2 = new javax.swing.JLabel();
        jlabel67 = new javax.swing.JLabel();
        jsl = new javax.swing.JLabel();
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
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        enableL1 = new javax.swing.JCheckBox();
        enableL2 = new javax.swing.JCheckBox();
        enableL3 = new javax.swing.JCheckBox();
        enableL4 = new javax.swing.JCheckBox();
        enableL5 = new javax.swing.JCheckBox();
        enableL6 = new javax.swing.JCheckBox();
        enableL7 = new javax.swing.JCheckBox();
        enableL8 = new javax.swing.JCheckBox();
        enableL9 = new javax.swing.JCheckBox();
        L1 = new javax.swing.JLabel();
        L2 = new javax.swing.JLabel();
        L3 = new javax.swing.JLabel();
        L4 = new javax.swing.JLabel();
        L5 = new javax.swing.JLabel();
        L6 = new javax.swing.JLabel();
        L7 = new javax.swing.JLabel();
        L8 = new javax.swing.JLabel();
        L9 = new javax.swing.JLabel();
        L1S1 = new javax.swing.JLabel();
        L2S1 = new javax.swing.JLabel();
        L3S1 = new javax.swing.JLabel();
        L4S1 = new javax.swing.JLabel();
        L5S1 = new javax.swing.JLabel();
        L6S1 = new javax.swing.JLabel();
        L7S1 = new javax.swing.JLabel();
        L8S1 = new javax.swing.JLabel();
        L9S1 = new javax.swing.JLabel();
        L1S = new javax.swing.JLabel();
        L2S = new javax.swing.JLabel();
        L3S = new javax.swing.JLabel();
        L4S = new javax.swing.JLabel();
        L5S = new javax.swing.JLabel();
        L6S = new javax.swing.JLabel();
        L7S = new javax.swing.JLabel();
        L8S = new javax.swing.JLabel();
        L9S = new javax.swing.JLabel();
        l1l5 = new javax.swing.JLabel();
        l5l9 = new javax.swing.JLabel();
        l4l7 = new javax.swing.JLabel();
        l8l9 = new javax.swing.JLabel();
        l7l8 = new javax.swing.JLabel();
        l4l5 = new javax.swing.JLabel();
        l5l6 = new javax.swing.JLabel();
        l3l6 = new javax.swing.JLabel();
        l1l2 = new javax.swing.JLabel();
        l2l3 = new javax.swing.JLabel();
        doQuery = new javax.swing.JButton();
        locationSelected = new javax.swing.JLabel();
        l1Materias = new javax.swing.JLabel();
        l1Calificaciones = new javax.swing.JLabel();
        l4Alumnos2b = new javax.swing.JLabel();
        l4Maestros = new javax.swing.JLabel();
        l7Materias = new javax.swing.JLabel();
        l7Calificaciones = new javax.swing.JLabel();
        l2Alumnos1b = new javax.swing.JLabel();
        l2Carreras = new javax.swing.JLabel();
        l5Alumnos2b = new javax.swing.JLabel();
        l5Materias = new javax.swing.JLabel();
        l3Alumnos2a = new javax.swing.JLabel();
        l3Maestros = new javax.swing.JLabel();
        l6Alumnos1a = new javax.swing.JLabel();
        l6Calificaciones = new javax.swing.JLabel();
        l9Alumnos1b = new javax.swing.JLabel();
        l9Carreras = new javax.swing.JLabel();
        l8Maestros = new javax.swing.JLabel();
        l8Alumnos2a = new javax.swing.JLabel();
        l1Alumnos1a = new javax.swing.JLabel();
        locationsBackground = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transparencia de repeticion y fragmentacion");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel32.setToolTipText("");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 495, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel33.setToolTipText("");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 495, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel34.setToolTipText("");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 495, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel35.setToolTipText("");
        jLabel35.setRequestFocusEnabled(false);
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 335, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel36.setToolTipText("");
        jLabel36.setRequestFocusEnabled(false);
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 335, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel41.setText("Base de datos on/off");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        jLabel42.setText("Localidad on/off");
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        databaseL9.setSelected(true);
        databaseL9.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL9.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL9.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL9ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 495, -1, -1));

        databaseL8.setSelected(true);
        databaseL8.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL8.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL8.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL8ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 495, -1, -1));

        databaseL7.setSelected(true);
        databaseL7.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL7.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL7.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL7ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 495, -1, -1));

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
        getContentPane().add(databaseL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 335, -1, -1));

        databaseL1.setSelected(true);
        databaseL1.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL1.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL1.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL1ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 191, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 189, -1, -1));

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
        getContentPane().add(databaseL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 191, -1, -1));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 189, -1, -1));

        databaseL3.setSelected(true);
        databaseL3.setMaximumSize(new java.awt.Dimension(20, 20));
        databaseL3.setMinimumSize(new java.awt.Dimension(20, 20));
        databaseL3.setPreferredSize(new java.awt.Dimension(20, 20));
        databaseL3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                databaseL3ItemStateChanged(evt);
            }
        });
        getContentPane().add(databaseL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 191, -1, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 189, -1, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enadat_ico.png"))); // NOI18N
        jLabel40.setToolTipText("");
        jLabel40.setRequestFocusEnabled(false);
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 335, -1, -1));

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
        getContentPane().add(databaseL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 335, -1, -1));

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
        getContentPane().add(databaseL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 335, -1, -1));

        jLabell2.setText("Carreras");
        getContentPane().add(jLabell2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        jlabel67.setText("Alumnos1b");
        getContentPane().add(jlabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, -1));

        jsl.setText("Califica");
        getContentPane().add(jsl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jlabel.setText("Alumnos1a");
        getContentPane().add(jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel7.setText("Materias");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel5.setText("Califica");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        jLabel6.setText("Alumnos1a");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, -1, -1));

        jLabel8.setText("Carrera");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, -1, -1));

        jLabel9.setText("Alumnos1b");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, -1, -1));

        jLabel10.setText("Materias");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, -1, -1));

        jLabel11.setText("Alumnos2b");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        jLabel12.setText("Maestros");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, -1, -1));

        jLabel13.setText("Alumnos2a");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, -1, -1));

        jLabel14.setText("Maestros");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel15.setText("Alumnos2b");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel16.setText("Califica");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabel18.setText("Maestros");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, -1, -1));

        jLabel19.setText("Materias");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        jLabel17.setText("Alumnos2a");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/checkmark.png"))); // NOI18N
        jLabel20.setText("Tabla utilizada");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location_icon.png"))); // NOI18N
        jLabel21.setText("Localidad actual");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected_icon.png"))); // NOI18N
        jLabel22.setText("Localidad utilizada");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 187, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        jLabel24.setRequestFocusEnabled(false);
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 335, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 495, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 187, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        jLabel27.setRequestFocusEnabled(false);
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 335, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 495, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 495, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        jLabel30.setRequestFocusEnabled(false);
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 335, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/enaloc_ico.png"))); // NOI18N
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 187, -1, -1));

        enableL1.setSelected(true);
        enableL1.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL1.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL1.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL1ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 191, -1, -1));

        enableL2.setSelected(true);
        enableL2.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL2.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL2.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL2ItemStateChanged(evt);
            }
        });
        enableL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableL2ActionPerformed(evt);
            }
        });
        getContentPane().add(enableL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 191, -1, -1));

        enableL3.setSelected(true);
        enableL3.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL3.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL3.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL3ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 191, -1, -1));

        enableL4.setSelected(true);
        enableL4.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL4.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL4.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL4.setRequestFocusEnabled(false);
        enableL4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL4ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 335, -1, -1));

        enableL5.setSelected(true);
        enableL5.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL5.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL5.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL5.setRequestFocusEnabled(false);
        enableL5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL5ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 335, -1, -1));

        enableL6.setSelected(true);
        enableL6.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL6.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL6.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL6.setRequestFocusEnabled(false);
        enableL6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL6ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 335, -1, -1));

        enableL7.setSelected(true);
        enableL7.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL7.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL7.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL7ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 495, -1, -1));

        enableL8.setSelected(true);
        enableL8.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL8.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL8.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL8ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 495, -1, -1));

        enableL9.setSelected(true);
        enableL9.setMaximumSize(new java.awt.Dimension(20, 20));
        enableL9.setMinimumSize(new java.awt.Dimension(20, 20));
        enableL9.setPreferredSize(new java.awt.Dimension(20, 20));
        enableL9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enableL9ItemStateChanged(evt);
            }
        });
        getContentPane().add(enableL9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 495, -1, -1));

        L1.setLabelFor(L1);
        L1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L1MouseClicked(evt);
            }
        });
        getContentPane().add(L1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 60, 60));

        L2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L2MouseClicked(evt);
            }
        });
        getContentPane().add(L2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 60, 60));

        L3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L3MouseClicked(evt);
            }
        });
        getContentPane().add(L3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 60, 60));

        L4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L4MouseClicked(evt);
            }
        });
        getContentPane().add(L4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 70, 60));

        L5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L5MouseClicked(evt);
            }
        });
        getContentPane().add(L5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 60, 60));

        L6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L6MouseClicked(evt);
            }
        });
        getContentPane().add(L6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 60, 60));

        L7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L7MouseClicked(evt);
            }
        });
        getContentPane().add(L7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 60, 60));

        L8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L8MouseClicked(evt);
            }
        });
        getContentPane().add(L8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 60, 60));

        L9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L9MouseClicked(evt);
            }
        });
        getContentPane().add(L9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 60, 60));

        L1S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L1S1.setLabelFor(L1);
        L1S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L1S1MouseClicked(evt);
            }
        });
        getContentPane().add(L1S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 115, 70, 70));

        L2S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L2S1.setLabelFor(L1);
        L2S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L2S1MouseClicked(evt);
            }
        });
        getContentPane().add(L2S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 121, 70, 60));

        L3S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L3S1.setLabelFor(L1);
        L3S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L3S1MouseClicked(evt);
            }
        });
        getContentPane().add(L3S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 121, 70, 60));

        L4S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L4S1.setLabelFor(L1);
        L4S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L4S1MouseClicked(evt);
            }
        });
        getContentPane().add(L4S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 264, 70, 60));

        L5S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L5S1.setLabelFor(L1);
        L5S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L5S1MouseClicked(evt);
            }
        });
        getContentPane().add(L5S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 265, 70, 60));

        L6S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L6S1.setLabelFor(L1);
        L6S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L6S1MouseClicked(evt);
            }
        });
        getContentPane().add(L6S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 264, 70, 60));

        L7S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L7S1.setLabelFor(L1);
        L7S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L7S1MouseClicked(evt);
            }
        });
        getContentPane().add(L7S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 424, 70, 60));

        L8S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L8S1.setLabelFor(L1);
        L8S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L8S1MouseClicked(evt);
            }
        });
        getContentPane().add(L8S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 424, 70, 60));

        L9S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/location.png"))); // NOI18N
        L9S1.setLabelFor(L1);
        L9S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L9S1MouseClicked(evt);
            }
        });
        getContentPane().add(L9S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 424, 70, 60));

        L1S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L1S.setLabelFor(L1);
        L1S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L1SMouseClicked(evt);
            }
        });
        getContentPane().add(L1S, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 120, 60, 60));

        L2S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L2S.setLabelFor(L1);
        L2S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L2SMouseClicked(evt);
            }
        });
        getContentPane().add(L2S, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 120, 60, 60));

        L3S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L3S.setLabelFor(L1);
        L3S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L3SMouseClicked(evt);
            }
        });
        getContentPane().add(L3S, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 120, 60, 60));

        L4S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L4S.setLabelFor(L1);
        L4S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L4SMouseClicked(evt);
            }
        });
        getContentPane().add(L4S, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 263, 60, 60));

        L5S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L5S.setLabelFor(L1);
        L5S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L5SMouseClicked(evt);
            }
        });
        getContentPane().add(L5S, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 264, 60, 60));

        L6S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L6S.setLabelFor(L1);
        L6S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L6SMouseClicked(evt);
            }
        });
        getContentPane().add(L6S, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 263, 60, 60));

        L7S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L7S.setLabelFor(L1);
        L7S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L7SMouseClicked(evt);
            }
        });
        getContentPane().add(L7S, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 424, 60, 60));

        L8S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L8S.setLabelFor(L1);
        L8S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L8SMouseClicked(evt);
            }
        });
        getContentPane().add(L8S, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 424, 60, 60));

        L9S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/selected.png"))); // NOI18N
        L9S.setLabelFor(L1);
        L9S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L9SMouseClicked(evt);
            }
        });
        getContentPane().add(L9S, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 424, 60, 60));

        l1l5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L1-L5.png"))); // NOI18N
        getContentPane().add(l1l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 162, -1, -1));

        l5l9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L5-L9.png"))); // NOI18N
        getContentPane().add(l5l9, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 309, 190, 130));

        l4l7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L4-L7.png"))); // NOI18N
        getContentPane().add(l4l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 325, -1, 100));

        l8l9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L2-L3.png"))); // NOI18N
        getContentPane().add(l8l9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        l7l8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L1-L2.png"))); // NOI18N
        getContentPane().add(l7l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 190, -1));

        l4l5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L1-L2.png"))); // NOI18N
        getContentPane().add(l4l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 190, -1));

        l5l6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L5-L6.png"))); // NOI18N
        getContentPane().add(l5l6, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 290, 160, -1));

        l3l6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L3-L6.png"))); // NOI18N
        getContentPane().add(l3l6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, -1, -1));

        l1l2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L1-L2.png"))); // NOI18N
        getContentPane().add(l1l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 145, -1, -1));

        l2l3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/L2-L3.png"))); // NOI18N
        getContentPane().add(l2l3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 145, -1, -1));

        doQuery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/next.png"))); // NOI18N
        doQuery.setText("Realizar Consulta");
        doQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doQueryActionPerformed(evt);
            }
        });
        getContentPane().add(doQuery, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, 40));

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
        getContentPane().add(locationSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 20, 20));

        l1Materias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l1Materias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        l1Calificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l1Calificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        l4Alumnos2b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l4Alumnos2b, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        l4Maestros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l4Maestros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        l7Materias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l7Materias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        l7Calificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l7Calificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        l2Alumnos1b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l2Alumnos1b, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        l2Carreras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l2Carreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        l5Alumnos2b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l5Alumnos2b, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, -1, -1));

        l5Materias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l5Materias, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, -1, -1));

        l3Alumnos2a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l3Alumnos2a, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, -1));

        l3Maestros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l3Maestros, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, -1, -1));

        l6Alumnos1a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l6Alumnos1a, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, -1, -1));

        l6Calificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l6Calificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, -1, -1));

        l9Alumnos1b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l9Alumnos1b, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, -1, -1));

        l9Carreras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l9Carreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, -1, -1));

        l8Maestros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l8Maestros, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, -1, -1));

        l8Alumnos2a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l8Alumnos2a, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        l1Alumnos1a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png"))); // NOI18N
        getContentPane().add(l1Alumnos1a, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        locationsBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        locationsBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Localidades.png"))); // NOI18N
        locationsBackground.setRequestFocusEnabled(false);
        getContentPane().add(locationsBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 755, 523));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/path_icon.png"))); // NOI18N
        jLabel43.setText("Ruta utilizada");
        getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void L1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L1MouseClicked
    {//GEN-HEADEREND:event_L1MouseClicked
        if (enableL1.isSelected())
            locationSelected.setText("L1");
            setSelection("L1");
    }//GEN-LAST:event_L1MouseClicked

    private void L2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L2MouseClicked
    {//GEN-HEADEREND:event_L2MouseClicked
        if (enableL2.isSelected())
            locationSelected.setText("L2");
            setSelection("L2");
    }//GEN-LAST:event_L2MouseClicked

    private void L3MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L3MouseClicked
    {//GEN-HEADEREND:event_L3MouseClicked
        if (enableL3.isSelected())
        locationSelected.setText("L3");
        setSelection("L3");
    }//GEN-LAST:event_L3MouseClicked

    private void L4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L4MouseClicked
    {//GEN-HEADEREND:event_L4MouseClicked
        if (enableL4.isSelected())
            locationSelected.setText("L4");
        setSelection("L4");
    }//GEN-LAST:event_L4MouseClicked

    private void L5MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L5MouseClicked
    {//GEN-HEADEREND:event_L5MouseClicked
        if (enableL5.isSelected())
            locationSelected.setText("L5");
        setSelection("L5");
    }//GEN-LAST:event_L5MouseClicked

    private void L6MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L6MouseClicked
    {//GEN-HEADEREND:event_L6MouseClicked
        if (enableL6.isSelected())
            locationSelected.setText("L6");
        setSelection("L6");
    }//GEN-LAST:event_L6MouseClicked

    private void L7MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L7MouseClicked
    {//GEN-HEADEREND:event_L7MouseClicked
        if (enableL7.isSelected())
            locationSelected.setText("L7");
        setSelection("L7");
    }//GEN-LAST:event_L7MouseClicked

    private void L8MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L8MouseClicked
    {//GEN-HEADEREND:event_L8MouseClicked
        if (enableL8.isSelected())
            locationSelected.setText("L8");
        setSelection("L8");
    }//GEN-LAST:event_L8MouseClicked

    private void L9MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_L9MouseClicked
    {//GEN-HEADEREND:event_L9MouseClicked
        if (enableL9.isSelected())
            locationSelected.setText("L9");
            setSelection("L9");
    }//GEN-LAST:event_L9MouseClicked

    private void enableL1ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL1ItemStateChanged
    {//GEN-HEADEREND:event_enableL1ItemStateChanged
        if (locationSelected.getText().equals("L1"))
            getNextSelected();
        setEnableLocation("L1",evt.getStateChange());
    }//GEN-LAST:event_enableL1ItemStateChanged

    private void enableL2ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL2ItemStateChanged
    {//GEN-HEADEREND:event_enableL2ItemStateChanged
        if (locationSelected.getText().equals("L2"))
            getNextSelected();
        setEnableLocation("L2",evt.getStateChange());
    }//GEN-LAST:event_enableL2ItemStateChanged

    private void enableL3ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL3ItemStateChanged
    {//GEN-HEADEREND:event_enableL3ItemStateChanged
        if (locationSelected.getText().equals("L3"))
            getNextSelected();
        setEnableLocation("L3",evt.getStateChange());
    }//GEN-LAST:event_enableL3ItemStateChanged

    private void enableL4ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL4ItemStateChanged
    {//GEN-HEADEREND:event_enableL4ItemStateChanged
        if (locationSelected.getText().equals("L4"))
            getNextSelected();
        setEnableLocation("L4",evt.getStateChange());
    }//GEN-LAST:event_enableL4ItemStateChanged

    private void enableL5ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL5ItemStateChanged
    {//GEN-HEADEREND:event_enableL5ItemStateChanged
        if (locationSelected.getText().equals("L5"))
            getNextSelected();
        setEnableLocation("L5",evt.getStateChange());
    }//GEN-LAST:event_enableL5ItemStateChanged

    private void enableL6ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL6ItemStateChanged
    {//GEN-HEADEREND:event_enableL6ItemStateChanged
        if (locationSelected.getText().equals("L6"))
            getNextSelected();
        setEnableLocation("L6",evt.getStateChange());
    }//GEN-LAST:event_enableL6ItemStateChanged

    private void enableL7ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL7ItemStateChanged
    {//GEN-HEADEREND:event_enableL7ItemStateChanged
        if (locationSelected.getText().equals("L7"))
            getNextSelected();
        setEnableLocation("L7",evt.getStateChange());
    }//GEN-LAST:event_enableL7ItemStateChanged

    private void enableL8ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL8ItemStateChanged
    {//GEN-HEADEREND:event_enableL8ItemStateChanged
        if (locationSelected.getText().equals("L8"))
            getNextSelected();
        setEnableLocation("L8",evt.getStateChange());
    }//GEN-LAST:event_enableL8ItemStateChanged

    private void enableL9ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_enableL9ItemStateChanged
    {//GEN-HEADEREND:event_enableL9ItemStateChanged
        if (locationSelected.getText().equals("L9"))
            getNextSelected();
        setEnableLocation("L9",evt.getStateChange());
        
    }//GEN-LAST:event_enableL9ItemStateChanged

    public static void setTablesBusy(String location, String table)
    {
        switch(location)
        {
            case "L1":
                switch(table)
                {
                    case "Alumnos1a":
                        l1Alumnos1a.setVisible(true);
                        break;
                    case "Materias":
                        l1Materias.setVisible(true);
                        break;
                    case "Calificaciones":
                        l1Calificaciones.setVisible(true);
                        break;
                }
                break;
            case "L2":
                switch(table)
                {
                    case "Alumnos1b":
                        l2Alumnos1b.setVisible(true);
                        break;
                    case "Carreras":
                        l2Carreras.setVisible(true);
                        break;
                }
                break;
            case "L3":
                switch(table)
                {
                    case "Alumnos2a":
                        l3Alumnos2a.setVisible(true);
                        break;
                    case "Maestros":
                        l3Maestros.setVisible(true);
                        break;
                }
                break;
            case "L4":
                switch(table)
                {
                    case "Alumnos2b":
                        l4Alumnos2b.setVisible(true);
                        break;
                    case "Maestros":
                        l4Maestros.setVisible(true);
                        break;
                }
                break;
            case "L5":
                switch(table)
                {
                    case "Alumnos2b":
                        l5Alumnos2b.setVisible(true);
                        break;
                    case "Materias":
                        l5Materias.setVisible(true);
                        break;
                }
                break;
            case "L6":
                switch(table)
                {
                    case "Alumnos1a":
                        l6Alumnos1a.setVisible(true);
                        break;
                    case "Calificaciones":
                        l6Calificaciones.setVisible(true);
                        break;
                }
                break;
            case "L7":
                switch(table)
                {
                    case "Materias":
                        l7Materias.setVisible(true);
                        break;
                    case "Calificaciones":
                        l7Calificaciones.setVisible(true);
                        break;
                }
                break;
            case "L8":
                switch(table)
                {
                    case "Alumnos2a":
                        l8Alumnos2a.setVisible(true);
                        break;
                    case "Maestros":
                        l8Maestros.setVisible(true);
                        break;
                }
                break;
            case "L9":
                switch(table)
                {
                    case "Alumnos1b":
                        l9Alumnos1b.setVisible(true);
                        break;
                    case "Carreras":
                        l9Carreras.setVisible(true);
                        break;
                }
                break;
        }
    }
    
    //Hide all icons check table
    public static void hideCheckTables()
    {
        l1Alumnos1a.setVisible(false);
        l1Materias.setVisible(false);
        l1Calificaciones.setVisible(false);
        l2Alumnos1b.setVisible(false);
        l2Carreras.setVisible(false);
        l3Alumnos2a.setVisible(false);
        l3Maestros.setVisible(false);
        l4Alumnos2b.setVisible(false);
        l4Maestros.setVisible(false);
        l5Alumnos2b.setVisible(false);
        l5Materias.setVisible(false);
        l6Alumnos1a.setVisible(false);
        l6Calificaciones.setVisible(false);
        l7Materias.setVisible(false);
        l7Calificaciones.setVisible(false);
        l8Alumnos2a.setVisible(false);
        l8Maestros.setVisible(false);
        l9Alumnos1b.setVisible(false);
        l9Carreras.setVisible(false);
        
    }
    private void locationSelectedPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_locationSelectedPropertyChange
    {//GEN-HEADEREND:event_locationSelectedPropertyChange
        showResult(new ArrayList<TableLocation>());        
        if (locationSelected.getText().equals("Ø"))
            doQuery.setEnabled(false);
        else
            doQuery.setEnabled(true);
        
    }//GEN-LAST:event_locationSelectedPropertyChange

    private void L1SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L1SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L1SMouseClicked

    private void L9SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L9SMouseClicked

    private void L8SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L8SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L8SMouseClicked

    private void L7SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L7SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L7SMouseClicked

    private void L6SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L6SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L6SMouseClicked

    private void L5SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L5SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L5SMouseClicked

    private void L4SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L4SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L4SMouseClicked

    private void L3SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L3SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L3SMouseClicked

    private void L2SMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L2SMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L2SMouseClicked

    private void L1S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L1S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L1S1MouseClicked

    private void L2S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L2S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L2S1MouseClicked

    private void L3S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L3S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L3S1MouseClicked

    private void L4S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L4S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L4S1MouseClicked

    private void L5S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L5S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L5S1MouseClicked

    private void L6S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L6S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L6S1MouseClicked

    private void L7S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L7S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L7S1MouseClicked

    private void L8S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L8S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L8S1MouseClicked

    private void L9S1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9S1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_L9S1MouseClicked

    private void enableL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableL2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enableL2ActionPerformed

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
        
        
        if (enableL1.isSelected() && enableL2.isSelected()){
            addLane("L1-L2", 0, 1, 1);    addLane("L2-L1", 1, 0, 1);
        }
        if (enableL2.isSelected() && enableL3.isSelected()){
            addLane("L2-L3", 1, 2, 1);    addLane("L3-L2", 2, 1, 1);
        }
        if (enableL1.isSelected() && enableL5.isSelected()){
            addLane("L1-L5", 0, 4, 1);    addLane("L5-L1", 4, 0, 1);
        }
        if (enableL3.isSelected() && enableL6.isSelected()){
            addLane("L3-L6", 2, 5, 1);    addLane("L6-L3", 5, 2, 1);
        }
        if (enableL4.isSelected() && enableL5.isSelected()){
            addLane("L4-L5", 3, 4, 1);    addLane("L5-L4", 4, 3, 1);
        }
        if (enableL5.isSelected() && enableL6.isSelected()){
            addLane("L5-L6", 4, 5, 1);    addLane("L6-L5", 5, 4, 1);
        }
        if (enableL4.isSelected() && enableL7.isSelected()){
            addLane("L4-L7", 3, 6, 1);    addLane("L7-L4", 6, 3, 1);
        }
        if (enableL5.isSelected() && enableL9.isSelected()){
            addLane("L5-L9", 4, 8, 1);    addLane("L9-L5", 8, 4, 1);
        }
        if (enableL7.isSelected() && enableL8.isSelected()){
            addLane("L7-L8", 6, 7, 1);    addLane("L8-L7", 7, 6, 1);
        }
        if (enableL8.isSelected() && enableL9.isSelected()){
            addLane("L8-L9", 7, 8, 1);    addLane("L9-L8", 8, 7, 1);
        }
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
    
    public void setSelection(String location)
    {
        L1S1.setVisible(false);
        L2S1.setVisible(false);
        L3S1.setVisible(false);
        L4S1.setVisible(false);
        L5S1.setVisible(false);
        L6S1.setVisible(false);
        L7S1.setVisible(false);
        L8S1.setVisible(false);
        L9S1.setVisible(false);
        
        switch(location)
            {
                case "L1": L1S1.setVisible(true); break;
                case "L2": L2S1.setVisible(true); break;
                case "L3": L3S1.setVisible(true); break;
                case "L4": L4S1.setVisible(true); break;
                case "L5": L5S1.setVisible(true); break;
                case "L6": L6S1.setVisible(true); break;
                case "L7": L7S1.setVisible(true); break;
                case "L8": L8S1.setVisible(true); break;
                case "L9": L9S1.setVisible(true); break;
            }
        
    }
    public void getNextSelected()
    {
        if (enableL1.isSelected()){
            locationSelected.setText("L1");
            setSelection("L1");}
        else if (enableL2.isSelected()){
            locationSelected.setText("L2");
            setSelection("L2");}
        else if (enableL3.isSelected()){
            locationSelected.setText("L3");
            setSelection("L3");}
        else if (enableL4.isSelected()){
            locationSelected.setText("L4");
            setSelection("L4");}
        else if (enableL5.isSelected()){
            locationSelected.setText("L5");
            setSelection("L5");}
        else if (enableL6.isSelected()){
            locationSelected.setText("L6");
            setSelection("L6");}
        else if (enableL7.isSelected()){
            locationSelected.setText("L7");
            setSelection("L7");}
        else if (enableL8.isSelected()){
            locationSelected.setText("L8");
            setSelection("L8");}
        else if (enableL9.isSelected()){
            locationSelected.setText("L9");
            setSelection("L9");}
        else
        {            
            locationSelected.setText("Ø");
            setSelection("Ø");
        }
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
    public static javax.swing.JLabel L1S;
    public static javax.swing.JLabel L1S1;
    private javax.swing.JLabel L2;
    public static javax.swing.JLabel L2S;
    public static javax.swing.JLabel L2S1;
    private javax.swing.JLabel L3;
    public static javax.swing.JLabel L3S;
    public static javax.swing.JLabel L3S1;
    private javax.swing.JLabel L4;
    public static javax.swing.JLabel L4S;
    public static javax.swing.JLabel L4S1;
    private javax.swing.JLabel L5;
    public static javax.swing.JLabel L5S;
    public static javax.swing.JLabel L5S1;
    private javax.swing.JLabel L6;
    public static javax.swing.JLabel L6S;
    public static javax.swing.JLabel L6S1;
    private javax.swing.JLabel L7;
    public static javax.swing.JLabel L7S;
    public static javax.swing.JLabel L7S1;
    private javax.swing.JLabel L8;
    public static javax.swing.JLabel L8S;
    public static javax.swing.JLabel L8S1;
    private javax.swing.JLabel L9;
    public static javax.swing.JLabel L9S;
    public static javax.swing.JLabel L9S1;
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
    private javax.swing.JCheckBox enableL1;
    private javax.swing.JCheckBox enableL2;
    private javax.swing.JCheckBox enableL3;
    private javax.swing.JCheckBox enableL4;
    private javax.swing.JCheckBox enableL5;
    private javax.swing.JCheckBox enableL6;
    private javax.swing.JCheckBox enableL7;
    private javax.swing.JCheckBox enableL8;
    private javax.swing.JCheckBox enableL9;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabell2;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel67;
    private javax.swing.JLabel jsl;
    public static javax.swing.JLabel l1Alumnos1a;
    public static javax.swing.JLabel l1Calificaciones;
    public static javax.swing.JLabel l1Materias;
    public static javax.swing.JLabel l1l2;
    public static javax.swing.JLabel l1l5;
    public static javax.swing.JLabel l2Alumnos1b;
    public static javax.swing.JLabel l2Carreras;
    public static javax.swing.JLabel l2l3;
    public static javax.swing.JLabel l3Alumnos2a;
    public static javax.swing.JLabel l3Maestros;
    public static javax.swing.JLabel l3l6;
    public static javax.swing.JLabel l4Alumnos2b;
    public static javax.swing.JLabel l4Maestros;
    public static javax.swing.JLabel l4l5;
    public static javax.swing.JLabel l4l7;
    public static javax.swing.JLabel l5Alumnos2b;
    public static javax.swing.JLabel l5Materias;
    public static javax.swing.JLabel l5l6;
    public static javax.swing.JLabel l5l9;
    public static javax.swing.JLabel l6Alumnos1a;
    public static javax.swing.JLabel l6Calificaciones;
    public static javax.swing.JLabel l7Calificaciones;
    public static javax.swing.JLabel l7Materias;
    public static javax.swing.JLabel l7l8;
    public static javax.swing.JLabel l8Alumnos2a;
    public static javax.swing.JLabel l8Maestros;
    public static javax.swing.JLabel l8l9;
    public static javax.swing.JLabel l9Alumnos1b;
    public static javax.swing.JLabel l9Carreras;
    public static javax.swing.JLabel locationSelected;
    private javax.swing.JLabel locationsBackground;
    // End of variables declaration//GEN-END:variables
}
