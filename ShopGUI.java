package GAME.player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopGUI   implements ActionListener {
    JFrame frame;
    JLabel  message, message2;
    JLabel mana_pret, viata_pret;
    Character character;
    JButton  buttongata;
    JButton buttons[] = new JButton[6];
    ArrayList index = new ArrayList();
    Shop shop ;
    JLabel greutatecurenta, nrmonedecurent, inventar, potiuni ;

    public ShopGUI (Character character) {
        shop = new Shop();
        this.character = character;

        frame = new JFrame();
        BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File("src/GAME/store.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic1 = new JLabel(new ImageIcon(img1));
        pic1.setBounds(280, 21, 640, 309);

        for (int i=0 ; i< shop.potionlist.size(); i++){
            if(shop.selectPotion(i).getClass().getSimpleName().equalsIgnoreCase("ManaPotion"))
                buttons[i] = new JButton("ManaPotion");
            else
                buttons[i] = new JButton("HealthPotion");

            buttons[i].setBackground(Color.green);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);

        }

        inventar = new JLabel();
        inventar.setBounds(50,325, 200,50);
        inventar.setText("Inventar ");

        greutatecurenta = new JLabel();
        greutatecurenta.setBounds(50,375, 200,50);
        greutatecurenta.setText("Greutate ramasa: "+ character.inventory.greutateramasa);

        nrmonedecurent = new JLabel();
        nrmonedecurent.setBounds(50,425, 200,50);
        nrmonedecurent.setText("Nr monede: "+ character.inventory.nrmonede);

        JLabel listapoptiuni = new JLabel();
        listapoptiuni.setBounds(50,500,100, 25);
        listapoptiuni.setText("Lista potiuni: ");

        potiuni = new JLabel();
        potiuni.setBounds(75,530, 400,50);
        potiuni.setText(character.inventory.listaPotiuni());

        for( int i =0; i< shop.potionlist.size(); i++)
            buttons[i].setBounds( 225+i*150, 490, 125, 30);

        buttongata= new JButton("Exit Shop");
        buttongata.setBounds(800, 550, 100, 30);

        message2 = new JLabel();
        message2.setBounds(350, 500, 300, 40);


        message = new JLabel();
        message.setBounds(350, 340, 300, 40);
        message.setText("Alege potiunile pe care vrei sa le cumperi ");


        mana_pret = new JLabel();
        mana_pret.setBounds(275, 390,200,30);
        mana_pret.setText("ManaPotion Pret: "+ shop.selectPotion(1).preluarePret()+" greutate: " + shop.selectPotion(1).preluareGreutate());

        viata_pret = new JLabel();
        viata_pret.setBounds(625, 390,200,30);
        viata_pret.setText("HealthPotion Pret: "+ shop.selectPotion(0).preluarePret()+" greutate: " + shop.selectPotion(0).preluareGreutate());



        frame.add(greutatecurenta);
        frame.add(nrmonedecurent);
        frame.add(inventar);
        frame.add(listapoptiuni);
        frame.add(potiuni);
        frame.add(pic1);
        frame.add(message);
        frame.add(buttongata);
        frame.add(mana_pret);
        frame.add(viata_pret);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttongata.addActionListener(this);


        frame.setLayout(null);
        frame.setTitle("SHOP");
        frame.setSize(1200, 800);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {

        for( int i = 0; i< shop.potionlist.size(); i ++)
            if( ae.getSource().equals(buttons[i])) {
                if (character.inventory.greutateramasa > shop.selectPotion(i).preluareGreutate() ) {
                    if(character.inventory.nrmonede > shop.selectPotion(i).preluarePret()){
                        if( ! index.contains(i) ) {
                            buttons[i].setBackground(Color.red);
                            index.add(i);
                            character.inventory.addPotiune(shop.selectPotion(i));
                            character.inventory.greutateramasa= character.inventory.greutateramasa -shop.selectPotion(i).preluareGreutate();
                            character.inventory.nrmonede= character.inventory.nrmonede -shop.selectPotion(i).preluarePret();
                            greutatecurenta.setText("Greutate ramasa: "+ character.inventory.greutateramasa);
                            nrmonedecurent.setText("Nr monede: "+ character.inventory.nrmonede);
                            potiuni.setText(character.inventory.listaPotiuni());
                            message2.setText("Ai cumaprat " + shop.selectPotion(i).getClass().getSimpleName() );
                        }
                    }
                    else {
                        message2.setText("Nu mai ai suficiente monede");
                    }

                }
                else {
                    message2.setText("Nu mai ai loc in inventar");
                 }
            }

        if( ae.getSource().equals(buttongata)) {
            for (int i = 0; i< index.size(); i++){
                shop.potionlist.remove(index.get(i));
                frame.dispose();
            }
            return;
        }
    }
}
