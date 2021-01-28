/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import onlineassessment.entity.Module1questions;
import onlineassessment.entity.Practicetestresult;
import onlineassessment.entity.Practicequestions;
import onlineassessment.util.HibernateUtil;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Vikram
 */
public class PracticeTest extends javax.swing.JFrame {

    private static String get10RandomQuestions = "from q questionstable q order by rand() limit 10";
    Map<Integer, Practicequestions> map = new HashMap<>();
    Map<Integer, Integer> answerMap = new HashMap<>();
    ButtonGroup buttonGroup = new ButtonGroup();
    String sno;
    Timer time;
    int k = 0;
    private long startTime = -1;
    private long duration = 5000;
    //private static ModuleTest frame = new ModuleTest();
    boolean isRunning = false;
    Webcam webcam;
    int correct, incorrect;
    float percentage;

    /**
     * Creates new form PracticeTest
     */
    public PracticeTest() {
        System.out.println("test constructor called");
        int val = 0;
             initComponents();
//            this.setLayout(null);
//       jPanel1.add(lblQuestionNo);
//       jPanel1.add(jTextArea1);
//       jPanel1.add(radioOpt1);
//       jPanel1.add(radioOpt2);
//       jPanel1.add(radioOpt3);
//       jPanel1.add(radioOpt4);
//       jPanel1.add(btnPrev);
//       jPanel1.add(btnNext);
//       jPanel1.add(btnEnd);
//       jPanel1.add(lblTime);
//       jScrollPane1.setViewportView(jPanel1);
//       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//this.add(jScrollPane1);
//jScrollPane2.setVerticalScrollBar(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        executeHQLQuery();
        currentQuestion();
        
        buttonGroup.add(radioOpt1);
        buttonGroup.add(radioOpt2);
        buttonGroup.add(radioOpt3);
        buttonGroup.add(radioOpt4);

        for (int i = 1; i <= 10; i++) {
            answerMap.put(i, val);
        }
        testTimer();


    }

    int sec = 200;
    public void testTimer() {
        try {
            System.out.println("in test timer method");
            long start = System.currentTimeMillis();
            Border border = BorderFactory.createLineBorder(Color.WHITE);
            lblTime.setFont(new Font("Calibri", Font.BOLD, 25));
            lblTime.setBorder(border);
            time = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date d = new Date(sec * 1000L);
                    SimpleDateFormat df = new SimpleDateFormat("mm:ss"); // HH for 0-23
                    df.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String time = df.format(d);
                    if (sec >= 0) {
                        lblTime.setText(" " + time);
                    }
                    if (sec == 300) {
                        JOptionPane.showMessageDialog(PracticeTest.this, "YOU HAVE ONLY FIVE MINUTES LEFT");
                    }
                    if (sec < 10) {
                        lblTime.setFont(new Font("Calibri", Font.BOLD, 30));
                        lblTime.setBackground(Color.red);
                        lblTime.setText("YOUR TEST IS GOING TO END IN " + time);
                        if (sec == 0) {
                            endTest();
                        }
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ModuleTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("frame is " + sec);
                    sec = sec - 1;
                }
            });
            time.start();
        } catch (Exception e) {
            System.out.println("Webcam is not there " + e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblQuestionNo = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        radioOpt1 = new javax.swing.JRadioButton();
        radioOpt2 = new javax.swing.JRadioButton();
        radioOpt3 = new javax.swing.JRadioButton();
        radioOpt4 = new javax.swing.JRadioButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnEnd = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        lblQuestionNo.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lblQuestionNo.setText("jLabel1");

        lblTime.setBackground(new java.awt.Color(255, 255, 255));
        lblTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 51, 51));
        lblTime.setText("jLabel1");

        radioOpt1.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        radioOpt1.setText("jRadioButton1");
        radioOpt1.setAutoscrolls(true);
        radioOpt1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        radioOpt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpt1ActionPerformed(evt);
            }
        });

        radioOpt2.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        radioOpt2.setText("jRadioButton2");
        radioOpt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpt2ActionPerformed(evt);
            }
        });

        radioOpt3.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        radioOpt3.setText("jRadioButton3");
        radioOpt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpt3ActionPerformed(evt);
            }
        });

        radioOpt4.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        radioOpt4.setText("jRadioButton4");
        radioOpt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpt4ActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(255, 255, 51));
        btnPrev.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnPrev.setForeground(new java.awt.Color(0, 0, 255));
        btnPrev.setText("Prev");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(255, 255, 0));
        btnNext.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnNext.setForeground(new java.awt.Color(51, 51, 255));
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnEnd.setBackground(new java.awt.Color(255, 0, 0));
        btnEnd.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnEnd.setForeground(new java.awt.Color(255, 255, 255));
        btnEnd.setText("End Test");
        btnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblQuestionNo)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnPrev)
                                    .addGap(45, 45, 45)
                                    .addComponent(btnNext)
                                    .addGap(146, 146, 146)
                                    .addComponent(btnEnd))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioOpt4, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(281, 281, 281)))
                .addGap(253, 253, 253))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuestionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(radioOpt1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(radioOpt2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioOpt3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioOpt4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnEnd))
                .addGap(153, 153, 153))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioOpt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOpt1ActionPerformed
        // TODO add your handling code here:
        answerMap.put(qNo, 1);
    }//GEN-LAST:event_radioOpt1ActionPerformed

    private void radioOpt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOpt2ActionPerformed
        // TODO add your handling code here:
        answerMap.put(qNo, 2);
    }//GEN-LAST:event_radioOpt2ActionPerformed

    private void radioOpt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOpt3ActionPerformed
        // TODO add your handling code here:
        answerMap.put(qNo, 3);
    }//GEN-LAST:event_radioOpt3ActionPerformed

    private void radioOpt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOpt4ActionPerformed
        // TODO add your handling code here:
        answerMap.put(qNo, 4);
        System.out.println(qNo + "radio button 4 value is" + answerMap.get(qNo));
    }//GEN-LAST:event_radioOpt4ActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prevQuestion();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here
        nextQuestion();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(PracticeTest.this, "Are you sure you want to end the test ?");
        if (a == JOptionPane.YES_OPTION) {
            endTest();
        }
    }//GEN-LAST:event_btnEndActionPerformed

    private void executeHQLQuery() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("SELECT e FROM Practicequestions e ORDER BY RAND()");
            q.setMaxResults(10);
            List<Practicequestions> resultList = q.list();
               if (resultList.isEmpty()) {
                System.out.println("no question is in table");
                JOptionPane.showMessageDialog(PracticeTest.this, "No question is in database");
                System.exit(0);
            }
            
            displayResult(resultList);
            System.out.println("result list is "+resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    int qNo = 0;

    private void displayResult(List resultList) {
        ArrayList<Practicequestions> al = new ArrayList<>();
        al.addAll(resultList);
        System.out.println(al);
        Iterator<Practicequestions> li = al.iterator();
        while (li.hasNext()) {
            qNo = qNo + 1;
            Practicequestions qt = li.next();
            System.out.println(qt.getQuestion() + " " + qt.getAns());
            map.put(qNo, qt);
        }
        qNo = 0;
    }

    public void currentQuestion() {
        buttonGroup.clearSelection();
        qNo = qNo + 1;
        if (answerMap.size() != 0) {
            if (answerMap.containsKey(qNo)) {
                if (1 == answerMap.get(qNo)) {
                    radioOpt1.doClick();
                }
                if (2 == answerMap.get(qNo)) {
                    radioOpt2.doClick();
                }
                if (3 == answerMap.get(qNo)) {
                    radioOpt3.doClick();
                }
                if (4 == answerMap.get(qNo)) {
                    radioOpt4.doClick();
                }
            }
        }
        System.out.println("q no in current is " + qNo);
       // lblQuestion.setText("<html>"+map.get(qNo).getQuestion()+"</html>");
        jTextArea1.setText(map.get(qNo).getQuestion());
        sno = String.valueOf(qNo);
        lblQuestionNo.setText(sno);
            radioOpt1.setText("<html>"+map.get(qNo).getOpt1()+"</html>");
            radioOpt2.setText("<html>"+map.get(qNo).getOpt2()+"</html>");
            radioOpt3.setText("<html>"+map.get(qNo).getOpt3()+"</html>");
            radioOpt4.setText("<html>"+map.get(qNo).getOpt4()+"</html>");
        btnPrev.setEnabled(false);
    }

    public void prevQuestion() {
        buttonGroup.clearSelection();
        qNo = qNo - 1;
        sno = String.valueOf(qNo);
        lblQuestionNo.setText(sno);
       
        System.out.println("q no in prev is " + qNo);
        if (qNo <= 1) {
            if (answerMap.size() != 0) {
                if (answerMap.containsKey(qNo)) {
                    if (1 == answerMap.get(qNo)) {
                        radioOpt1.doClick();
                    }
                    if (2 == answerMap.get(qNo)) {
                        radioOpt2.doClick();
                    }
                    if (3 == answerMap.get(qNo)) {
                        radioOpt3.doClick();
                    }
                    if (4 == answerMap.get(qNo)) {
                        radioOpt4.doClick();
                    }
                }
            }
          //  lblQuestion.setText(map.get(qNo).getQuestion());
          jTextArea1.setText(map.get(qNo).getQuestion());
            radioOpt1.setText("<html>"+map.get(qNo).getOpt1()+"</html>");
            radioOpt2.setText("<html>"+map.get(qNo).getOpt2()+"</html>");
            radioOpt3.setText("<html>"+map.get(qNo).getOpt3()+"</html>");
            radioOpt4.setText("<html>"+map.get(qNo).getOpt4()+"</html>");
            btnPrev.setEnabled(false);
            return;
        } else {
            //    System.out.println("this is prev questions");
            if (answerMap.size() != 0) {
                if (answerMap.containsKey(qNo)) {
                    if (1 == answerMap.get(qNo)) {
                        radioOpt1.doClick();
                    }
                    if (2 == answerMap.get(qNo)) {
                        radioOpt2.doClick();
                    }
                    if (3 == answerMap.get(qNo)) {
                        radioOpt3.doClick();
                    }
                    if (4 == answerMap.get(qNo)) {
                        radioOpt4.doClick();
                    }
                }
            }
          //  lblQuestion.setText(map.get(qNo).getQuestion());
          jTextArea1.setText(map.get(qNo).getQuestion());
            radioOpt1.setText("<html>"+map.get(qNo).getOpt1()+"</html>");
            radioOpt2.setText("<html>"+map.get(qNo).getOpt2()+"</html>");
            radioOpt3.setText("<html>"+map.get(qNo).getOpt3()+"</html>");
            radioOpt4.setText("<html>"+map.get(qNo).getOpt4()+"</html>");
            btnPrev.setEnabled(true);
            btnNext.setEnabled(true);
        }
    }

    public void nextQuestion() {
        buttonGroup.clearSelection();
        qNo = qNo + 1;
        sno = String.valueOf(qNo);
        lblQuestionNo.setText(sno);
        System.out.println("q no in next is " + qNo);
        if (qNo == 10) {
            System.out.println("in else of qNo" + qNo);
            btnNext.setEnabled(false);
            if (answerMap.size() != 0) {
                if (answerMap.containsKey(qNo)) {
                    if (1 == answerMap.get(qNo)) {
                        radioOpt1.doClick();
                    }
                    if (2 == answerMap.get(qNo)) {
                        radioOpt2.doClick();
                    }
                    if (3 == answerMap.get(qNo)) {
                        radioOpt3.doClick();
                    }
                    if (4 == answerMap.get(qNo)) {
                        radioOpt4.doClick();
                    }
                }
            }
      //      lblQuestion.setText(map.get(qNo).getQuestion());
      jTextArea1.setText(map.get(qNo).getQuestion());
            radioOpt1.setText("<html>"+map.get(qNo).getOpt1()+"</html>");
            radioOpt2.setText("<html>"+map.get(qNo).getOpt2()+"</html>");
            radioOpt3.setText("<html>"+map.get(qNo).getOpt3()+"</html>");
            radioOpt4.setText("<html>"+map.get(qNo).getOpt4()+"</html>");
            return;
        } else if (qNo < 10) {
          //  lblQuestion.setText(map.get(qNo).getQuestion());
          jTextArea1.setText(map.get(qNo).getQuestion());
            radioOpt1.setText("<html>"+map.get(qNo).getOpt1()+"</html>");
            radioOpt2.setText("<html>"+map.get(qNo).getOpt2()+"</html>");
            radioOpt3.setText("<html>"+map.get(qNo).getOpt3()+"</html>");
            radioOpt4.setText("<html>"+map.get(qNo).getOpt4()+"</html>");
            if (answerMap.size() != 0) {
                if (answerMap.containsKey(qNo)) {
                    if (1 == answerMap.get(qNo)) {
                        radioOpt1.doClick();
                    }
                    if (2 == answerMap.get(qNo)) {
                        radioOpt2.doClick();
                    }
                    if (3 == answerMap.get(qNo)) {
                        radioOpt3.doClick();
                    }
                    if (4 == answerMap.get(qNo)) {
                        radioOpt4.doClick();
                    }
                }
            }
            btnNext.setEnabled(true);
            btnPrev.setEnabled(true);
        }
    }

    public void endTest() {
        try
        {
        System.out.println(answerMap);
        int a = 1;
        for (Map.Entry<Integer, Practicequestions> q : map.entrySet()) {
            int answer = q.getValue().getAns();
            if (answer == answerMap.get(a)) {
                correct++;
            } else {
                incorrect++;
            }
            percentage = correct * 100 / 10;
            a++;
        }
        System.out.println("correct answer are " + correct + "\n" + "incorrect answers are" + incorrect
                + "\n" + "percentage is " + percentage);
       // isRunning = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //  Query q = session.createQuery("SELECT e FROM Practicequestions e ORDER BY RAND()");
        Practicetestresult ptr = new Practicetestresult();
        ptr.setCorrectQuestions(correct);
        ptr.setIncorrectQuestions(incorrect);
        ptr.setPercentage(percentage);
        ptr.setPracticeTestDate(new Date());
        ptr.setStudentId(StudentForm.userId);
        ptr.setTotalQuestions(correct + incorrect);

        session.save(ptr);
        session.getTransaction().commit();
        System.out.println("data saved in practice table");

        System.exit(0);
        }
        catch(Exception e)
        {
            System.out.println("issue while saving practice data "+e);
        }
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
            java.util.logging.Logger.getLogger(PracticeTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PracticeTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PracticeTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PracticeTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PracticeTest().setVisible(true);
//            }
//        });
    }

    public WindowAdapter getWindowAdapter() {
        return new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we);
                JOptionPane.showMessageDialog(PracticeTest.this, "Cant Exit");
            }

            @Override
            public void windowIconified(WindowEvent we) {
                PracticeTest.this.setState(JFrame.NORMAL);
                JOptionPane.showMessageDialog(PracticeTest.this, "Cant Minimize");
            }
        };
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnd;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblQuestionNo;
    private javax.swing.JLabel lblTime;
    private javax.swing.JRadioButton radioOpt1;
    private javax.swing.JRadioButton radioOpt2;
    private javax.swing.JRadioButton radioOpt3;
    private javax.swing.JRadioButton radioOpt4;
    // End of variables declaration//GEN-END:variables
}
