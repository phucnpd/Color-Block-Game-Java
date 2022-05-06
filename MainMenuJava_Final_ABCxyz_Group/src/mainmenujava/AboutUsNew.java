package mainmenujava;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author Vo Tue Nam CE140557
 */
public class AboutUsNew extends javax.swing.JFrame {

    /**
     * Creates new form AboutUsNew
     */
    public AboutUsNew() {
        CustomCursor();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/2.png")));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    public void CustomCursor(){
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

        exit = new javax.swing.JLabel();
        backgound = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("About Us Form");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 130, 70));

        backgound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/newAboutUs.png"))); // NOI18N
        backgound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgoundMouseClicked(evt);
            }
        });
        getContentPane().add(backgound, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backgoundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgoundMouseClicked
        MainMenu m = new MainMenu();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backgoundMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgound;
    private javax.swing.JLabel exit;
    // End of variables declaration//GEN-END:variables
}