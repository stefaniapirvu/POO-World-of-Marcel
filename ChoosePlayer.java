package GAME.player;

import GAME.info.Account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChoosePlayer  implements ActionListener {
    JFrame frame;
    JLabel  message, message2;
    JLabel  warrior, mage, rogue;
    JButton buttonW, buttonR, buttonM;
    Account Account ;

    public ChoosePlayer (Account Account) {
        this.Account = Account;

        frame = new JFrame();
        BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File("src/GAME/warrior1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic1 = new JLabel(new ImageIcon(img1));
        pic1.setBounds(650, 75, 100, 106);

        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File("src/GAME/mage1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic2 = new JLabel(new ImageIcon(img2));
        pic2.setBounds(650, 175, 100, 100);

        BufferedImage img3 = null;
        try {
            img3 = ImageIO.read(new File("src/GAME/rogue1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic3 = new JLabel(new ImageIcon(img3));
        pic3.setBounds(650, 275, 100, 97);

        buttonW = new JButton("Warrior");
        buttonW.setBounds(850, 110, 100, 30);

        buttonM = new JButton("Mage");
        buttonM.setBounds(850, 210, 100, 30);

        buttonR = new JButton("Rogue");
        buttonR.setBounds(850, 310, 100, 30);

        message = new JLabel();
        message.setBounds(350, 50, 300, 30);
        message.setText(" Hello " + Account.getInfo().getName() + "! \n Choose a player");

        warrior = new JLabel();
        warrior.setBounds(100, 100, 700, 50);
        warrior.setText(" Warrior " + Account.getPersonaje().get(0).afisareinfo() );
        mage = new JLabel();
        mage.setBounds(100, 200, 700, 50);
        mage.setText(" Mage " + Account.getPersonaje().get(1).afisareinfo());
        rogue = new JLabel();
        rogue.setBounds(100, 300, 700, 50);
        rogue.setText(" Rogue "  + Account.getPersonaje().get(2).afisareinfo());

        message2 = new JLabel();
        message2.setBounds(350, 400, 300, 30);

        frame.add(pic1);
        frame.add(pic2);
        frame.add(pic3);
        frame.add(message);
        frame.add(buttonM);
        frame.add(buttonR);
        frame.add(buttonW);
        frame.add(warrior);
        frame.add(rogue);
        frame.add(mage);
        frame.add(message2);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonM.addActionListener(this);
        buttonW.addActionListener(this);
        buttonR.addActionListener(this);

        frame.setLayout(null);
        frame.setTitle("Choose a player");
        frame.setSize(1000, 600);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if( ae.getSource().equals(buttonM)) {
            message2.setText("Ai ales Mage");
            Start start = new Start(Account.personaje.get(1));

        }
        else if( ae.getSource().equals(buttonW)) {
            message2.setText("Ai ales Warrior");
            Start start = new Start(Account.personaje.get(0));
        }
        else if( ae.getSource().equals(buttonR)) {
            message2.setText("Ai ales Rogue");
            Start start = new Start(Account.personaje.get(2));
        }
    }
}
