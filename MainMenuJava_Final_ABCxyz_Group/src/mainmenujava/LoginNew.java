package mainmenujava;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author Vo Tue Nam CE140557
 */
public class LoginNew extends javax.swing.JFrame {
    
    public static String string = "Unknown Player";

    /**
     * Creates new form LoginNew
     */
    public LoginNew() {
        CustomCursor();
        //this.setSize(600,450);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/2.png")));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void CustomCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("mousecontrol.png");
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        setCursor(cursor);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        passPassword = new javax.swing.JPasswordField();
        lblPlay = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        lblException = new javax.swing.JLabel();
        lblRegister = new javax.swing.JLabel();
        Backgound = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login Form");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setBackground(new java.awt.Color(204, 255, 153));
        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 330, 30));

        passPassword.setBackground(new java.awt.Color(204, 255, 153));
        passPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(passPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 330, -1));

        lblPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPlayMouseClicked(evt);
            }
        });
        getContentPane().add(lblPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 100, 50));

        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        getContentPane().add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 150, 70));

        lblException.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblException.setForeground(new java.awt.Color(255, 0, 0));
        lblException.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lblException, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 360, 30));

        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterMouseClicked(evt);
            }
        });
        getContentPane().add(lblRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 130, 50));

        Backgound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/LoginForm.png"))); // NOI18N
        getContentPane().add(Backgound, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPlayMouseClicked
        UserManagemnet obj = new UserManagemnet();
        obj.Update();
        String username = txtUsername.getText();
        String password = passPassword.getText();
        if (obj.correctPin(username, password) == true) {
            string = username;
            this.setVisible(false);
        } else {
            lblException.setText("Invalid username and password");
        }
    }//GEN-LAST:event_lblPlayMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        MainMenu mainm = new MainMenu();
        this.setVisible(false);
        mainm.setVisible(true);
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseClicked
        this.setVisible(false);
        RegisterNew obj = new RegisterNew();
        obj.setVisible(true);
    }//GEN-LAST:event_lblRegisterMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgound;
    private javax.swing.JLabel lblException;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblPlay;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JPasswordField passPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
