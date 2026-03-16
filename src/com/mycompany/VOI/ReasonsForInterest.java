package com.mycompany.VOI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ReasonsForInterest extends javax.swing.JFrame {

    private JTable reasonTable;
    private JScrollPane reasonTableScrollPane;
    private JButton addReasonButton;
    private JButton editReasonButton;
    private JButton deleteReasonButton;

    private JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6;
    private JLabel jLabel1, jLabel2, jLabel3;
    private JPanel jPanel1, jPanel2, jPanel3;

    private static final Logger logger = Logger.getLogger(ReasonsForInterest.class.getName());

    public ReasonsForInterest() {
        initComponents();
        populateReasonTable();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        toFront();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        jButton1 = new JButton("Dashboard");
        jButton2 = new JButton("Vehicles Of Interest");
        jButton3 = new JButton("Reasons For Interest");
        jButton4 = new JButton("Makes and Models");
        jButton5 = new JButton("Help");
        jButton6 = new JButton("Logout");

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();

        reasonTable = new JTable();
        reasonTableScrollPane = new JScrollPane(reasonTable);
        addReasonButton = new JButton("Add Reason");
        editReasonButton = new JButton("Edit Reason");
        deleteReasonButton = new JButton("Delete Reason");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        JButton[] sidebarButtons = {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6};
        for (JButton btn : sidebarButtons) {
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Highlight Reasons For Interest button like the other buttons (blue background and border)
        jButton3.setBackground(new java.awt.Color(147, 220, 255));  // #93DCFF
        jButton3.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.setFocusPainted(false);
        jButton3.setContentAreaFilled(true);  // Make sure background shows

        jButton1.addActionListener(evt -> { new Dashboard().setVisible(true); this.dispose(); });
        jButton2.addActionListener(evt -> { new VehiclesOfInterest().setVisible(true); this.dispose(); });
        jButton3.addActionListener(evt -> { new ReasonsForInterest().setVisible(true); this.dispose(); });
        jButton4.addActionListener(evt -> { new MakesAndModels().setVisible(true); this.dispose(); });
	jButton5.addActionListener(evt -> jButton5ActionPerformed(evt));
	jButton6.addActionListener(evt -> jButton6ActionPerformed(evt));


        try {
            URL icon1 = getClass().getResource("/com/mycompany/VOI/Resources/PoliceDeptIcon_resized.png");
            if (icon1 != null) {
                BufferedImage img = javax.imageio.ImageIO.read(icon1);
                if (img != null) {
                    jLabel1.setIcon(new ImageIcon(img));
                }
            }
        } catch (Exception e) {
            logger.severe("Error loading PoliceDeptIcon_resized.png: " + e.getMessage());
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(13, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(347, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 480));

        jPanel3.setBackground(new java.awt.Color(142, 217, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DefaultTableModel model = new DefaultTableModel(new String[]{"Reason", "Description"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reasonTable.setModel(model);

        jPanel3.add(reasonTableScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 29, 600, 154));
        jPanel3.add(addReasonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 120, 30));
        jPanel3.add(editReasonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 120, 30));
        jPanel3.add(deleteReasonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 93, 730, 307));

        try {
            URL icon2 = getClass().getResource("/com/mycompany/VOI/Resources/user.png");
            if (icon2 != null) {
                BufferedImage img = javax.imageio.ImageIO.read(icon2);
                if (img != null) {
                    jLabel2.setIcon(new ImageIcon(img));
                }
            }
        } catch (Exception e) {
            logger.severe("Error loading user.png: " + e.getMessage());
        }
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 30));

        try {
            URL icon3 = getClass().getResource("/com/mycompany/VOI/Resources/English2.png");
            if (icon3 != null) {
                BufferedImage img = javax.imageio.ImageIO.read(icon3);
                if (img != null) {
                    jLabel3.setIcon(new ImageIcon(img));
                }
            }
        } catch (Exception e) {
            logger.severe("Error loading English2.png: " + e.getMessage());
        }
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 50, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 400));

        addReasonButton.setBackground(new java.awt.Color(147, 220, 255));
        addReasonButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addReasonButton.setFocusPainted(false);

        editReasonButton.setBackground(new java.awt.Color(147, 220, 255));
        editReasonButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editReasonButton.setFocusPainted(false);

        deleteReasonButton.setBackground(new java.awt.Color(147, 220, 255));
        deleteReasonButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        deleteReasonButton.setFocusPainted(false);

        addReasonButton.addActionListener(e -> addReason());
        editReasonButton.addActionListener(e -> editReason());
        deleteReasonButton.addActionListener(e -> deleteReason());

        pack();
    }

    private void addReason() {
        String reason = JOptionPane.showInputDialog(this, "Enter reason:");
        if (reason == null || reason.trim().isEmpty()) return;

        String description = JOptionPane.showInputDialog(this, "Enter description:");
        if (description == null || description.trim().isEmpty()) return;

        VehiclesOfInterestController controller = new VehiclesOfInterestController();
        try {
            Connection conn = controller.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO reason_for_interest (reason, description) VALUES (?, ?)"
            );
            pstmt.setString(1, reason.trim());
            pstmt.setString(2, description.trim());
            pstmt.executeUpdate();
            pstmt.close();

            populateReasonTable();  // Refresh table
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding reason: " + ex.getMessage());
        }
    }

    private void editReason() {
        int selectedRow = reasonTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            return;
        }

        String oldReason = (String) reasonTable.getValueAt(selectedRow, 0);
        String newDescription = JOptionPane.showInputDialog(this, "Enter new description:", reasonTable.getValueAt(selectedRow, 1));
        if (newDescription == null || newDescription.trim().isEmpty()) return;

        VehiclesOfInterestController controller = new VehiclesOfInterestController();
        try {
            Connection conn = controller.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE reason_for_interest SET description = ? WHERE reason = ?"
            );
            pstmt.setString(1, newDescription.trim());
            pstmt.setString(2, oldReason);
            pstmt.executeUpdate();
            pstmt.close();

            populateReasonTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error editing reason: " + ex.getMessage());
        }
    }

    private void deleteReason() {
        int selectedRow = reasonTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        String reason = (String) reasonTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete \"" + reason + "\"?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        VehiclesOfInterestController controller = new VehiclesOfInterestController();
        try {
            Connection conn = controller.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM reason_for_interest WHERE reason = ?"
            );
            pstmt.setString(1, reason);
            pstmt.executeUpdate();
            pstmt.close();

            populateReasonTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting reason: " + ex.getMessage());
        }
    }

    private void populateReasonTable() {
        VehiclesOfInterestController controller = new VehiclesOfInterestController();
        String[][] data = controller.getAllReasonsForInterest();

        DefaultTableModel model = (DefaultTableModel) reasonTable.getModel();
        model.setRowCount(0);

        for (String[] row : data) {
            model.addRow(row);
        }
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
   



    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ReasonsForInterest().setVisible(true));
    }
}
