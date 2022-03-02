package GAME.player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FinishGUI {
    JFrame frame;
    JLabel label , label2;

    public FinishGUI(Character character, int nrenemys){

        frame = new JFrame();
        label = new JLabel("AI CASTIGAAAAAAT !!!!!!");
        label2 = new JLabel("Nivelul tau curent este " +  character.nivelcurent+ "Ai "+ character.inventory.nrmonede + " monede ");

        label.setBounds(200,10,200,30);
        label2.setBounds(250,45,300,30);

        BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File("src/GAME/win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic1 = new JLabel(new ImageIcon(img1));
        pic1.setBounds(0, 75, 800, 422);



        frame.add(label);
        frame.add(label2);
        frame.add(pic1);

        frame.setLayout(null);
        frame.setTitle("FINISH");
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
}
