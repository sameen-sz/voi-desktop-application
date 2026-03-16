package com.mycompany.VOI;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Box;


public class MakesAndModels extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(MakesAndModels.class.getName());
    private VehiclesOfInterestController controller;
    

    public MakesAndModels() {
	controller = new VehiclesOfInterestController(); 
        initComponents();


	loadVehicleMakes();           // Your existing method to load first table (Make only)
    	loadVehicleModelsWithMakes(); 


        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        toFront();

       
    
    }

    private void addMake() {
    String newMake = JOptionPane.showInputDialog(this, "Enter new vehicle make:", "Add Make", JOptionPane.PLAIN_MESSAGE);
    if (newMake != null) {
        newMake = newMake.trim();
        if (newMake.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Make cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if the make already exists
        String[] existingMakes = controller.allVehicleMakeKey();
        for (String make : existingMakes) {
            if (make.equalsIgnoreCase(newMake)) {
                JOptionPane.showMessageDialog(this, "Make already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Insert the new make into DB
        try {
            controller.insertVehicleMake(newMake);
            loadVehicleMakes();  // Refresh the makes table
            JOptionPane.showMessageDialog(this, "Make added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding make: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void deleteMake() {
    int selectedRow = jTable2.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a make to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String makeToDelete = (String) jTable2.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete make: " + makeToDelete + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            controller.deleteVehicleMake(makeToDelete);
            loadVehicleMakes();  // Refresh the makes table
            JOptionPane.showMessageDialog(this, "Make deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting make: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void addModel() {
    JPanel panel = new JPanel();
    JTextField modelField = new JTextField(10);
    JComboBox<String> makeCombo = new JComboBox<>(controller.allVehicleMakeKey());

    panel.add(new JLabel("Model:"));
    panel.add(modelField);
    panel.add(Box.createHorizontalStrut(15)); // spacer
    panel.add(new JLabel("Make:"));
    panel.add(makeCombo);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Model", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        String newModel = modelField.getText().trim();
        String selectedMake = (String) makeCombo.getSelectedItem();

        if (newModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Model cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if model+make already exists
        String[][] existingModels = controller.getAllVehicleModelsWithMakes();
        for (String[] pair : existingModels) {
            if (pair[0].equalsIgnoreCase(newModel) && pair[1].equalsIgnoreCase(selectedMake)) {
                JOptionPane.showMessageDialog(this, "This model for the selected make already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Insert into DB
        try {
            controller.insertVehicleModel(newModel, selectedMake);
            loadVehicleModelsWithMakes();  // Refresh the model+make table
            JOptionPane.showMessageDialog(this, "Model added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding model: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void deleteModel() {
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a model to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String modelToDelete = (String) jTable1.getValueAt(selectedRow, 0);
    String makeOfModel = (String) jTable1.getValueAt(selectedRow, 1);

    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete model: " + modelToDelete + " for make: " + makeOfModel + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            controller.deleteVehicleModel(modelToDelete, makeOfModel);
            loadVehicleModelsWithMakes();  // Refresh the model+make table
            JOptionPane.showMessageDialog(this, "Model deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting model: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    


     private void loadVehicleMakes() {
        try {
            String[][] makes = controller.getAllVehicleMakes();
            DefaultTableModel makeModel = (DefaultTableModel) jTable2.getModel();
            makeModel.setRowCount(0);
            for (String[] make : makes) {
                makeModel.addRow(make);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load Makes", e);
            JOptionPane.showMessageDialog(this, "Failed to load Makes: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadVehicleModelsWithMakes() {

	String[][] data = controller.getAllVehicleModelsWithMakes();
	
    
    
    	DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); // Replace yourMakeModelTable with your actual JTable variable name
   	model.setRowCount(0);

    	for (String[] row : data) {
             model.addRow(row);
    	}
     }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Dashboard");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Vehicles Of Interest");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reasons For Interest");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(147, 220, 255));
        jButton4.setText("Makes and Models");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton4.setFocusPainted(false);

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Help");
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
	jButton5.addActionListener(evt -> jButton5ActionPerformed(evt));
	

        jButton6.setText("Logout");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
	jButton6.addActionListener(evt -> jButton6ActionPerformed(evt));

	jButton7.addActionListener(e -> addMake());
	jButton8.addActionListener(e -> deleteMake());
	jButton9.addActionListener(e -> addModel());
	jButton10.addActionListener(e -> deleteModel());

        URL icon1 = getClass().getResource("/com/mycompany/VOI/Resources/PoliceDeptIcon_resized.png");
        if (icon1 != null) {
            try {
                java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(icon1);
                if (img != null) {
                    jLabel1.setIcon(new javax.swing.ImageIcon(img));
                    System.out.println("Image loaded successfully using ImageIO.");
                } else {
                    System.err.println("ImageIO.read returned null for: " + icon1);
                }
            } catch (Exception e) {
                System.err.println("Failed to load image using ImageIO:");
                e.printStackTrace();
            }
        } else {
            System.err.println("Image resource not found: PoliceDeptIcon_resized.png");
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jButton5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(384, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, -1));

        jPanel3.setBackground(new java.awt.Color(142, 217, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Model(s)", "Make"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Make"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton7.setBackground(new java.awt.Color(147, 220, 255));
        jButton7.setText("Add Make");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton7.setFocusPainted(false);

	jButton7.addActionListener(e -> addMake());

        jButton8.setBackground(new java.awt.Color(147, 220, 255));
        jButton8.setText("Delete Make");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton8.setFocusPainted(false);
	
	jButton8.addActionListener(e -> deleteMake());

        jButton9.setBackground(new java.awt.Color(147, 220, 255));
        jButton9.setText("Add Model");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton9.setFocusPainted(false);

        jButton9.addActionListener(e -> addModel());

        jButton10.setBackground(new java.awt.Color(147, 220, 255));
        jButton10.setText("Delete Model");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton10.setFocusPainted(false);
	
	jButton10.addActionListener(e -> deleteModel());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 93, 470, 280));

        try {
            URL icon2 = getClass().getResource("/com/mycompany/VOI/Resources/user.png");
            if (icon2 != null) {
                java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(icon2);
                if (img != null) {
                    jLabel2.setIcon(new javax.swing.ImageIcon(img));
                    System.out.println("jLabel2 icon set successfully.");
                } else {
                    System.err.println("ImageIO.read returned null for: " + icon2);
                }
            } else {
                System.err.println("Image not found: user.png");
            }
        } catch (Exception e) {
            System.err.println("Exception while loading jLabel2 image:");
            e.printStackTrace();
        }

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 50, 30));

        try {
            URL icon3 = getClass().getResource("/com/mycompany/VOI/Resources/English2.png");
            if (icon3 != null) {
                java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(icon3);
                if (img != null) {
                    jLabel3.setIcon(new javax.swing.ImageIcon(img));
                    System.out.println("jLabel3 icon set successfully.");
                } else {
                    System.err.println("ImageIO.read returned null for: " + icon3);
                }
            } else {
                System.err.println("Image not found: English2.png");
            }
        } catch (Exception e) {
            System.err.println("Exception while loading jLabel3 image:");
            e.printStackTrace();
        }

        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 50, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        new Dashboard().setVisible(true);
        this.dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        new VehiclesOfInterest().setVisible(true);
        this.dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        new ReasonsForInterest().setVisible(true);
        this.dispose();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        new MakesAndModels().setVisible(true);
        this.dispose();
    }

    // Help button

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
   	javax.swing.JOptionPane.showMessageDialog(
       	     this,
       	     "Vehicles Of Interest System\n\nUse the sidebar to manage vehicles, reasons and vehicle makes/models.",
             "Help",
             javax.swing.JOptionPane.INFORMATION_MESSAGE
    	);
    }

	// Logout button
	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    		System.exit(0);
	}









    /* private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Add Model clicked");
        // TODO: Implement Add Model functionality
    } */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MakesAndModels.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MakesAndModels().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
