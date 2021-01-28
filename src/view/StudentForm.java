/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import onlineassessment.entity.Schedulemt;
import onlineassessment.entity.Studentmt;
import onlineassessment.entity.Studentregistration;
//import onlineassessment.entity.Studentregistration;
import org.hibernate.Session;
import onlineassessment.util.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author Vikram
 */
public class StudentForm extends javax.swing.JFrame {

    static boolean practiceTest = false, moduleTest = false;

    public StudentForm() {
        initComponents();
        buttonGroup1.add(radioModuleTest);
        lblUserName.setText("Online Test Portal ");
        radioPracticeTest.setSelected(true);
        buttonGroup1.add(radioPracticeTest);
        buttonGroup1.add(radioModuleTest);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblStudentId = new javax.swing.JLabel();
        txtStudentId = new javax.swing.JTextField();
        btnStudentId = new javax.swing.JButton();
        radioPracticeTest = new javax.swing.JRadioButton();
        radioModuleTest = new javax.swing.JRadioButton();
        lblUserName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblStudentId.setText("Enter Student Id ");

        btnStudentId.setText("Submit");
        btnStudentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentIdActionPerformed(evt);
            }
        });

        radioPracticeTest.setText("Practice Test");
        radioPracticeTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPracticeTestActionPerformed(evt);
            }
        });

        radioModuleTest.setText("Module Test");
        radioModuleTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioModuleTestActionPerformed(evt);
            }
        });

        lblUserName.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblStudentId)
                        .addGap(56, 56, 56)
                        .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnStudentId))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioModuleTest)
                            .addComponent(radioPracticeTest)
                            .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName)
                .addGap(35, 35, 35)
                .addComponent(radioPracticeTest)
                .addGap(18, 18, 18)
                .addComponent(radioModuleTest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStudentId)
                    .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(btnStudentId)
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static String dbStudentId;
    public static String userName;
    public static String userEmail;
    public static String userId;
    public static String userModuleTestID;
    private static Date testDate;
    private void btnStudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentIdActionPerformed
//            if (radioModuleTest.isSelected()) {
//                        TestInstructions ti = new TestInstructions();
//                        ti.setVisible(true);
//                        this.dispose();
//            }
        try {
            if (radioModuleTest.isSelected()) {
                try {
                    long HOUR = 3600 * 1000;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:00");
                    String s2 = sdf.format(new Date());
                    Date d2 = sdf.parse(s2);
                    System.out.println("date in string is " + s2);
                    Date d3 = new Date(d2.getTime() + 1 * HOUR);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
                    String s3 = sdf1.format(d3);
                    System.out.println("after 8 hours date is " + s3);
                    String studentId = txtStudentId.getText();
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Query query1 = session.createSQLQuery("select sch.from_date , sch.to_date,"
                            + "smt.s_id ,sch.schmtid  from Schedulemt sch join  Studentmt smt "
                            + "on sch.schmtid = smt.schmtid where smt.s_id =:s_id and smt.mt_active=true "
                            + "and sch.from_date>date_sub(now(),interval 20 minute)");
                    query1.setParameter("s_id", studentId);
                    List<?> list = query1.list();
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Your test is not scheduled you can go for practice test");
                    } else {
                        List<Object[]> listItems = query1.list();
                        for (Object[] a : listItems) {
                            System.out.println(a[0] + "," + a[1] + "," + a[2] + "," + a[3]);
                            userModuleTestID = a[3].toString();
                            userId = a[2].toString();
                                
                            System.out.println("user mtid " + a[3] + " user id is " + a[2]);
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StudentForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        TestInstructions ti = new TestInstructions();
                        ti.setVisible(true);
                        this.dispose();
                        session.close();
                    }
                } catch (Exception e) {
                    System.out.println("some issue " + e);
                }
            }
            if (radioPracticeTest.isSelected()) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    System.out.println("in radio practice test");
                    Query query2 = session.createQuery("from Studentregistration where s_id = :s_id");
                    String studentId = txtStudentId.getText();
                    query2.setParameter("s_id", studentId);
                    List<?> list = query2.list();
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Your are not a registered student");
                        System.exit(0);
                    }
                    Studentregistration student = (Studentregistration) list.get(0);
                    System.out.println(student.getSId() + " " + student.getEmail());
                    userId = student.getSId();
                    dbStudentId = student.getSId();
                    if (studentId.equals(dbStudentId)) {
                        System.out.println("its working");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StudentForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("this is for practice form");//TestInstructions ti = new TestInstructions();
                        PracticeTest frame = new PracticeTest();
                        frame.setTitle("PRACTICE TEST");
                        frame.getContentPane().setBackground(Color.white);
                        frame.setExtendedState(ModuleTest.MAXIMIZED_BOTH);
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                        frame.setVisible(true);
                        frame.addWindowListener(frame.getWindowAdapter());
                        this.dispose();
                    }
                    session.close();
                } catch (Exception e) {
                    System.out.println("some issue " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("some issue " + e);
        }
    }//GEN-LAST:event_btnStudentIdActionPerformed

    private void radioPracticeTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPracticeTestActionPerformed
        // TODO add your handling code here:
        practiceTest = true;
    }//GEN-LAST:event_radioPracticeTestActionPerformed

    private void radioModuleTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioModuleTestActionPerformed
        // TODO add your handling code here:
        moduleTest = true;
    }//GEN-LAST:event_radioModuleTestActionPerformed

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
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStudentId;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblStudentId;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JRadioButton radioModuleTest;
    private javax.swing.JRadioButton radioPracticeTest;
    private javax.swing.JTextField txtStudentId;
    // End of variables declaration//GEN-END:variables
}
