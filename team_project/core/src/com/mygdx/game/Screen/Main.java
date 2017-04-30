package com.mygdx.game.Screen;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Created by ALwyn on 2017/4/30.
 */

public class main implements ActionListener {
    main(){
        JFrame f = new JFrame("Menu Demo");
        f.setSize(220,200);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar jmb = new JMenuBar();

        JMenu jmOptions = new JMenu("Options");
        JMenu a = new JMenu("Main");
        JMenuItem b = new JMenuItem("play");
        JMenuItem c = new JMenuItem("Exit");
        a.add(b);
        a.add(c);
        jmOptions.add(a);
        jmb.add(jmOptions);

        jmiOpen.addActionListener(this);
        jmiExit.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);

        f.setJMenuBar(jmb);
        f.setVisible(true);
    }

    public void play(){
        playscreen();
    }

    public void exit(){
       System.exit();
    }
}
