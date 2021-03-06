package mainmenujava;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vo Tue Nam CE140557
 */
public final class Welcome extends javax.swing.JFrame {
    Thread timer = null;
    public static int  counter = 0;
    /**
     * Creates new form Welcome
     */
    public Welcome() {
        this.CustomCursor();
        initComponents();
        setIcon();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        timer = new Thread() {
            @Override
            public void run() {
                try {
                    while (counter <= 100) {
                        lblLoad.setText(Integer.toString(counter)+"%");
                        counter=counter+1;                        
                        sleep(50);
                        if(counter==101){
                            sleep(300);
                            MainMenu m = new MainMenu();
                            m.setVisible(true);
                            close();                            
                        }
                        
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(colorblock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.start();
    }
    public void close(){

        this.dispose();
    }
    public void CustomCursor(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("mousecontrol.png");
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        setCursor(cursor);    
    }
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/2.png")));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgound = new javax.swing.JPanel();
        lblLoad = new javax.swing.JLabel();
        gif = new javax.swing.JLabel();
        wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome Form");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        backgound.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLoad.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblLoad.setForeground(new java.awt.Color(255, 255, 255));
        lblLoad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgound.add(lblLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 150, 50));

        gif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/giphy (3).gif"))); // NOI18N
        backgound.add(gif, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 750, 510));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/LOADING....png"))); // NOI18N
        backgound.add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        getContentPane().add(backgound);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
                
            }
        });
    }
    public String secondToTime(int second) {
        int h = second / 3600;
        int m = (second / 60) % 60;
        int s = second % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgound;
    private javax.swing.JLabel gif;
    private javax.swing.JLabel lblLoad;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
}
