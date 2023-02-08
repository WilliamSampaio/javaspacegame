/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javaspacegame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

/**
 *
 * @author william
 */
public class Javaspacegame extends JFrame {

    public Javaspacegame() throws LineUnavailableException, IOException {

        Fases fase1 = new Fases();

        add(fase1);

        setTitle("Java Space Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            Javaspacegame javaspacegame = new Javaspacegame();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Javaspacegame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Javaspacegame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
