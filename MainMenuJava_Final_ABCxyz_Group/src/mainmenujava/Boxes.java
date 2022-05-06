/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenujava;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Nguyen Pham Dinh Phuc
 */
public class Boxes extends JLabel {
    public static final String clicknull = "NOPE.mp3";
    MP3Player mP3Player3 = new MP3Player(new File(clicknull));
    public static final String clickdung = "clickdung.mp3";
    MP3Player mP3Player1 = new MP3Player(new File(clickdung));
    public static final String clicksai = "clicksai.mp3";
    MP3Player mP3Player2 = new MP3Player(new File(clicksai));
    Thread timer = null;
    private int row;
    private int col;
    private int value;
    private MouseAdapter mouseEvent;
    private colorblock parent;

    public Boxes(int row, int col, int value, colorblock parent) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.mouseEvent = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                boxesClick();
            }

        };
        this.addMouseListener(mouseEvent);
        this.parent = parent;
    }
    /**
     * get your click
     */
    private void boxesClick() {
        List<Boxes> arraylist = new ArrayList<Boxes>();
        parent.CanDelete(this, arraylist);
        if (value != 0) {
            parent.setScoreByBox(arraylist);
        }
        
        if(value==0){
            mP3Player3.play();//tieng bam vao o trong
            return;
        }
        if (arraylist.size() >= 2) {
            mP3Player1.play();
            for (int i = 0; i < arraylist.size(); i++) {
                parent.boxes[arraylist.get(i).getRow()][arraylist.get(i).getCol()].setValue(0);
            }
        } else {
            mP3Player2.play();
        }
        
        timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(500);
                    parent.dropDowBox();
                    parent.toRightBox();
                    parent.checkEnd();
                } catch (InterruptedException ex) {
                    Logger.getLogger(colorblock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.start();
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void show() {
        System.out.println(this.getValue() + " " + this.getRow() + " " + this.getCol() + " ");
    }

}
