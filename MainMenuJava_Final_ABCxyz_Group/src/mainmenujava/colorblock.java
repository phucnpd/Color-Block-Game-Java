package mainmenujava;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Pham Dinh Phuc
 */
public class colorblock extends javax.swing.JFrame {

    public static final String SONG = "0.mp3";
    MP3Player mP3Player = new MP3Player(new File(SONG));
    public static final int ROWS = 12;
    public static final int COLS = 18;
    public static int score = 0;
    public static String string;
    Boxes[][] boxes;
    Boxes[][] delectBoxes;
    int counter = 600;
    Thread timer = null;
    public static int soundOnOff = 0;

    /**
     * Creates new form color block
     */
    public colorblock() {
        CustomCursor();
        mP3Player.play();
        LoginNew obj = new LoginNew();
        string = obj.string;
        System.out.println("+" + string);
        initComponents();
        setIcon();
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        pnlGame.setLayout(new GridLayout(ROWS, COLS, 0, 0));
        boxes = new Boxes[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Random rd = new Random();
                int number = rd.nextInt(5) + 1;
                String string = "/img/" + Integer.toString(number) + ".png";
                boxes[i][j] = new Boxes(i, j, number, this);
                boxes[i][j].setIcon(getImage(string));
                pnlGame.add(boxes[i][j]);
            }
        }
        timer = new Thread() {
            @Override
            public void run() {
                try {
                    while (counter >= 0) {
                        if (counter < 120) {
                            lblTime.setForeground(Color.red);
                        }
                        lblTime.setText(secondToTime(counter));
                        counter--;
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(colorblock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.start();

    }

    /**
     * Custom cursor mouse click
     */
    public void CustomCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("mousecontrol.png");
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        setCursor(cursor);
    }

    /**
     * Get Up box
     *
     * @param a boxes click
     * @return Up box
     */
    public Boxes tren(Boxes a) {
        if (a.getRow() == 0) {
            return null;
        } else {
            return boxes[a.getRow() - 1][a.getCol()];
        }
    }

    /**
     * Get down box
     *
     * @param a boxes click
     * @return down box
     */
    public Boxes duoi(Boxes a) {
        if (a.getRow() == 11) {
            return null;
        } else {
            return boxes[a.getRow() + 1][a.getCol()];
        }
    }

    /**
     * Get left box
     *
     * @param a boxes click
     * @return Left box
     */
    public Boxes trai(Boxes a) {
        if (a.getCol() == 0) {
            return null;
        } else {
            return boxes[a.getRow()][a.getCol() - 1];
        }
    }

    /**
     * Get right box
     *
     * @param a boxes click
     * @return Right box
     */
    public Boxes phai(Boxes a) {
        if (a.getCol() == 17) {
            return null;
        } else {
            return boxes[a.getRow()][a.getCol() + 1];
        }
    }

    /**
     * Second to time
     *
     * @param second second
     * @return a format time
     */
    public String secondToTime(int second) {
        int h = second / 3600;
        int m = (second / 60) % 60;
        int s = second % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    /**
     * set picture
     *
     * @param imagePath
     * @return
     */
    public ImageIcon getImage(String imagePath) {
        return new ImageIcon(this.getClass().getResource(imagePath));

    }

    /**
     * Check Delete
     *
     * @param a oBx click
     * @param arraylist list Box
     */
    public void CanDelete(Boxes a, List<Boxes> arraylist) {
        if (arraylist.contains(a)) {
            return;
        }
        arraylist.add(a);

        if (a == null) {
            return;
        }
        if (tren(a) != null) {
            if (a.getValue() == tren(a).getValue()) {
                CanDelete(tren(a), arraylist);
            }
        }
        if (duoi(a) != null) {
            if (a.getValue() == duoi(a).getValue()) {
                CanDelete(duoi(a), arraylist);
            }
        }

        if (trai(a) != null) {
            if (a.getValue() == trai(a).getValue()) {
                CanDelete(trai(a), arraylist);
            }
        }

        if (phai(a) != null) {
            if (a.getValue() == phai(a).getValue()) {
                CanDelete(phai(a), arraylist);
            }
        }
        delete(arraylist);
    }

    /**
     * Set score (100 point with 1 box)
     *
     * @param arraylist array save delete boxes
     */
    public void setScoreByBox(List<Boxes> arraylist) {
        if (arraylist.size() >= 2) {
            score = score + (100 * (arraylist.size()));
            lblScore.setText(Integer.toString(score));
        }
    }

    /**
     * Change delete box to empty picture
     *
     * @param arraylist
     */
    public void delete(List<Boxes> arraylist) {
        if (arraylist.size() >= 2) {
            for (int i = 0; i < arraylist.size(); i++) {
                String string = "/img/" + "00.jpg";
                boxes[arraylist.get(i).getRow()][arraylist.get(i).getCol()].setIcon(getImage(string));
            }
        }
    }

    /**
     * input all Disappear boxes to dqDrop
     */
    public void dropDowBox() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (boxes[i][j].getValue() == 0) {
                    dqDropDow(i, j);
                }
            }
        }
    }

    /**
     * Drop
     *
     * @param i Row of disappear box
     * @param j Col of disappear box
     */
    public void dqDropDow(int i, int j) {
        if (tren(boxes[i][j]) != null && tren(boxes[i][j]).getValue() != 0) {
            boxes[i][j].setValue(tren(boxes[i][j]).getValue());
            String string = "/img/" + Integer.toString(boxes[i][j].getValue()) + ".png";
            boxes[i][j].setIcon(getImage(string));
            tren(boxes[i][j]).setValue(0);
            tren(boxes[i][j]).setIcon(getImage("/img/" + "00.jpg"));
            dqDropDow(tren(boxes[i][j]).getRow(), tren(boxes[i][j]).getCol());

        } else if (tren(boxes[i][j]) != null && tren(boxes[i][j]).getValue() == 0) {
            dqDropDow(tren(boxes[i][j]).getRow(), tren(boxes[i][j]).getCol());
        }
    }

    /**
     * input all Disappear boxes to dqToRight
     */
    public void toRightBox() {
        for (int i = 0; i < COLS; i++) {
            int count = 0;
            for (int j = 0; j < ROWS; j++) {
                if (boxes[j][i].getValue() == 0) {
                    count++;
                }
                if (count == 12) {
                    for (int h = 0; h < ROWS; h++) {
                        dqToRight(h, i);
                    }
                }
            }
        }
    }

    /**
     * Go right
     *
     * @param i Row of disappear box
     * @param j Col of disappear box
     */
    public void dqToRight(int i, int j) {
        if (trai(boxes[i][j]) != null && trai(boxes[i][j]).getValue() != 0) {
            boxes[i][j].setValue(trai(boxes[i][j]).getValue());
            String string = "/img/" + Integer.toString(boxes[i][j].getValue()) + ".png";
            boxes[i][j].setIcon(getImage(string));
            trai(boxes[i][j]).setValue(0);
            trai(boxes[i][j]).setIcon(getImage("/img/" + "00.jpg"));
            dqToRight(trai(boxes[i][j]).getRow(), trai(boxes[i][j]).getCol());

        } else if (trai(boxes[i][j]) != null && trai(boxes[i][j]).getValue() == 0) {
            dqToRight(trai(boxes[i][j]).getRow(), trai(boxes[i][j]).getCol());
        }
    }

    /**
     * Check end of game ?
     */
    public void checkEnd() {
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (boxes[i][j].getValue() != 0) {
                    List<Boxes> end = new ArrayList<Boxes>();
                    dqCheckEnd(boxes[i][j], end);
                    if (end.size() >= 2) {
                        count++;
                    }
                }

            }
        }
        if (count == 0) {
            mP3Player.stop();
            try {
                DBManagement db = new DBManagement();
                Connection conn = db.getConnected();
                ScoresManagement abc = new ScoresManagement(conn);
                score = score + (10 * counter);
                System.out.println("+" + counter);
                lblScore.setText(Integer.toString(score));
                abc.insert(string, score);
                timer.stop();
                JOptionPane.showMessageDialog(this, "Game Over");
                this.dispose();
                MainMenu a = new MainMenu();
                a.setVisible(true);

                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(colorblock.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * recusion of checkEnd
     *
     * @param a every box not disappear
     * @param arraylist
     */
    public void dqCheckEnd(Boxes a, List<Boxes> arraylist) {
        if (arraylist.contains(a)) {
            return;
        }
        arraylist.add(a);

        if (a.equals(null)) {
            return;
        }
        if (tren(a) != null) {
            if (a.getValue() == tren(a).getValue()) {
                dqCheckEnd(tren(a), arraylist);
            }
        }
        if (duoi(a) != null) {
            if (a.getValue() == duoi(a).getValue()) {
                dqCheckEnd(duoi(a), arraylist);
            }
        }

        if (trai(a) != null) {
            if (a.getValue() == trai(a).getValue()) {
                dqCheckEnd(trai(a), arraylist);
            }
        }

        if (phai(a) != null) {
            if (a.getValue() == phai(a).getValue()) {
                dqCheckEnd(phai(a), arraylist);
            }
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

        pblbackground = new javax.swing.JPanel();
        pnlGame = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        pnlTime = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        pnlScore = new javax.swing.JPanel();
        lblScore = new javax.swing.JLabel();
        lblStop = new javax.swing.JLabel();
        lblReturn = new javax.swing.JLabel();
        lblMuteSound = new javax.swing.JLabel();
        lblbackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COLOR BLOCK");

        pblbackground.setLayout(null);

        pnlGame.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlGame.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 896, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );

        pblbackground.add(pnlGame);
        pnlGame.setBounds(80, 60, 900, 600);

        pnlMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Info game"));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTime.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Time", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semilight", 1, 14))); // NOI18N

        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("00:10:00");

        javax.swing.GroupLayout pnlTimeLayout = new javax.swing.GroupLayout(pnlTime);
        pnlTime.setLayout(pnlTimeLayout);
        pnlTimeLayout.setHorizontalGroup(
            pnlTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTimeLayout.setVerticalGroup(
            pnlTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenu.add(pnlTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 31, 188, -1));

        pnlScore.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Scores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semilight", 1, 14))); // NOI18N

        lblScore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("0");

        javax.swing.GroupLayout pnlScoreLayout = new javax.swing.GroupLayout(pnlScore);
        pnlScore.setLayout(pnlScoreLayout);
        pnlScoreLayout.setHorizontalGroup(
            pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addGap(55, 55, 55))
        );
        pnlScoreLayout.setVerticalGroup(
            pnlScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 128, 188, 90));

        lblStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Avatar.gif"))); // NOI18N
        lblStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStopMouseClicked(evt);
            }
        });
        pnlMenu.add(lblStop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 180, 330));

        lblReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return.png"))); // NOI18N
        lblReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReturnMouseClicked(evt);
            }
        });
        pnlMenu.add(lblReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 50, 40));

        lblMuteSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Mute.png"))); // NOI18N
        lblMuteSound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMuteSoundMouseClicked(evt);
            }
        });
        pnlMenu.add(lblMuteSound, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        pblbackground.add(pnlMenu);
        pnlMenu.setBounds(1070, 10, 200, 700);

        lblbackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/background.jpg"))); // NOI18N
        pblbackground.add(lblbackground);
        lblbackground.setBounds(0, 0, 1280, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pblbackground, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pblbackground, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReturnMouseClicked
        MainMenu a = new MainMenu();
        a.setVisible(true);
        mP3Player.stop();
        this.dispose();
    }//GEN-LAST:event_lblReturnMouseClicked

    private void lblStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStopMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblStopMouseClicked

    private void lblMuteSoundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMuteSoundMouseClicked
        if (soundOnOff % 2 == 0) {
            lblMuteSound.setIcon(getImage("/Image/SoundOn.png"));
            mP3Player.pause();
            soundOnOff++;
        } else {
            lblMuteSound.setIcon(getImage("/Image/Mute.png"));
            mP3Player.play();
            soundOnOff++;
        }
    }//GEN-LAST:event_lblMuteSoundMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMuteSound;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblStop;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblbackground;
    private javax.swing.JPanel pblbackground;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlScore;
    private javax.swing.JPanel pnlTime;
    // End of variables declaration//GEN-END:variables

    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/colorBlock.png")));
    }
}
