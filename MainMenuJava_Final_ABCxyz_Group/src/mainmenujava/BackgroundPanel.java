/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenujava;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
  class BackgroundPanel extends Panel
    {
        // The Image to store the background image in.
        Image img;
        public BackgroundPanel()
        {
            // Loads the background image and stores in img object.
            img = new ImageIcon(getClass().getResource("1.png")).getImage();
        }

        public void paint(Graphics g)
        {
            // Draws the img to the BackgroundPanel.
            g.drawImage(img, 0, 0, null);
        }
    }