package GAME.player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyGUI implements ActionListener {

    JFrame frame;
    Character character;
    Enemy enemy;
    JLabel message;
    JLabel strenght , dexterity, charisma;
    JLabel viata, mana, abilitati, protectii,potiuni, viataE, manaE, abilitatiE, protectiiE;
    JButton buttonready, buttonpotiune, buttonabilitate, buttonatac;


    public EnemyGUI(Character character){
        enemy = new Enemy();
        this.character = character;
        frame = new JFrame();

            BufferedImage img1 = null;
        try {
            if (character.getClass().getSimpleName().equalsIgnoreCase("warrior")) {
                img1 = ImageIO.read(new File("src/GAME/LUPTAWarrior1.png"));
            }
            else if (character.getClass().getSimpleName().equalsIgnoreCase("rogue")) {
                img1 = ImageIO.read(new File("src/GAME/LUPTARogue1.png"));
            }
            else {
                img1 = ImageIO.read(new File("src/GAME/LUPTAMage.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic1 = new JLabel(new ImageIcon(img1));
        pic1.setBounds(25, 200, 200, 200);

        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File("src/GAME/LUPTAEnemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic2 = new JLabel(new ImageIcon(img2));
        pic2.setBounds(975, 200, 200, 200);


        message = new JLabel();
        message.setText("Your turn");
        message.setBounds(300, 100, 600, 50);

        strenght = new JLabel();
        strenght.setText("Strenght: " + character.strength);
        strenght.setBounds(50,50,200,50);
        dexterity = new JLabel();
        dexterity.setText("Dexterity: " + character.dexterity);
        dexterity.setBounds(50,100,200,50);
        charisma = new JLabel();
        charisma.setText("Charisma: " + character.charisma);
        charisma.setBounds(50,150,200,50);


        viata = new JLabel();
        viata.setText("Viata curenta: " + character.viatacurenta);
        viata.setBounds(250,200,325,40);
        mana = new JLabel();
        mana.setText("Mana curenta: " + character.manacurenta);
        mana.setBounds(250,250,325,40);
        protectii = new JLabel();
        String s= new String();
         if(character.earthprotection)
             s = s +" earth ";
        if(character.fireprotection)
            s = s +" fire ";
        if(character.iceprotection)
            s = s +" ice ";

        protectii.setText("Protectii: "+ s );
        protectii.setBounds(250,300,325,40);

        abilitati = new JLabel();
        abilitati.setText("Abilitati " + character.afisareAbilitati());
        abilitati.setBounds(250, 350, 325,40);

        potiuni = new JLabel();
        potiuni.setText("Potiuni: "+ character.inventory.listaPotiuni());
        potiuni.setBounds(250,400, 325, 40);


        viataE = new JLabel();
        viataE.setText("Viata curenta: " + enemy.viatacurenta);
        viataE.setBounds(625,200,325,40);
        manaE = new JLabel();
        manaE.setText("Mana curenta: " + enemy.manacurenta);
        manaE.setBounds(625,250,325,40);
        protectiiE = new JLabel();
        String sE= new String();
        if(enemy.earthprotection)
            sE = sE +" earth ";
        if(enemy.fireprotection)
            sE = sE +" fire ";
        if(enemy.iceprotection)
            sE = sE +" ice ";

        protectiiE.setText("Protectii: "+ sE );
        protectiiE.setBounds(625,300,325,40);

        abilitatiE = new JLabel();
        abilitatiE.setText("Abilitati " + enemy.afisareAbilitati());
        abilitatiE.setBounds(625, 350, 325,40);


        buttonabilitate = new JButton("Abilitate");
        buttonatac = new JButton("Atac normal");
        buttonpotiune= new JButton("Potiune");
        buttonready =new JButton("Gata pentru atacul inamicului");
        buttonready.setBackground(Color.cyan);
        buttonabilitate.setBounds( 50, 500, 200, 50 );
        buttonatac.setBounds(275,500,200,50);
        buttonpotiune.setBounds(500, 500, 200, 50);
        buttonready.setBounds(825, 500, 200, 50);



        frame.add(buttonabilitate);
        frame.add(buttonatac);
        frame.add(buttonpotiune);
        frame.add(buttonready);
        frame.add(viata);
        frame.add(viataE);
        frame.add(manaE);
        frame.add(mana);
        frame.add(protectiiE);
        frame.add(protectii);
        frame.add(abilitatiE);
        frame.add(abilitati);
        frame.add(potiuni);
        frame.add(strenght);
        frame.add(dexterity);
        frame.add(charisma);
        frame.add(pic1);
        frame.add(pic2);
        frame.add(message);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonready.addActionListener(this);
        buttonabilitate.addActionListener(this);
        buttonatac.addActionListener(this);
        buttonpotiune.addActionListener(this);


        frame.setLayout(null);
        frame.setTitle("FIGHT");
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }



    public void actionPerformed(ActionEvent ae){

        if(ae.getSource().equals(buttonabilitate)){
            if(!character.abilitati.isEmpty()){
                message.setText("Ai folosit :" + character.abilitati.get(0).getClass().getSimpleName());
                character.folosireAbilitate(character.abilitati.get(0),enemy );
                mana.setText("Mana curenta: " + character.manacurenta);
                viataE.setText("Viata curenta: " + enemy.viatacurenta);
                abilitati.setText("Abilitati " + character.afisareAbilitati());
            }else
                message.setText("Nu mai ai abilitati");
        }

        if(ae.getSource().equals(buttonpotiune)){
            if(!character.inventory.potiuni.isEmpty()){
                character.usePotion(character.inventory.potiuni.get(0));
                character.inventory.potiuni.remove(0);
                potiuni.setText("Potiuni: "+ character.inventory.listaPotiuni());
                viata.setText("Viata curenta: " + character.viatacurenta);
                mana.setText("Mana curenta: " + character.manacurenta);
            }else
                message.setText("Nu mai ai potiuni");
        }
        if (ae.getSource().equals(buttonatac)){
            enemy.receiveDamage(character.getDamage());
            viataE.setText("Viata curenta: " + enemy.viatacurenta);
            message.setText("Ai atacat ");

        }
        if (ae.getSource().equals(buttonready)){
            int damage;
            if(enemy.viatacurenta >0){
                if (enemy.abilitati.isEmpty()) {
                    character.receiveDamage(enemy.getDamage());
                    viata.setText("Viata curenta: " + character.viatacurenta);
                    damage= enemy.getDamage();
                }
                else{
                    if (enemy.manacurenta > enemy.abilitati.get(0).getCostmana() ) {
                        damage = enemy.abilitati.get(0).getDamage();
                        enemy.folosireAbilitate(enemy.abilitati.get(0), character);
                        viata.setText("Viata curenta: " + character.viatacurenta);
                    }
                    else {
                        character.receiveDamage(enemy.getDamage());
                        damage= enemy.getDamage();
                        viata.setText("Viata curenta: " + character.viatacurenta);
                    }

                 }
                message.setText("Ai primit damage de "+ damage +" E randul tau!");
            }
            else {
                frame.dispose();
            }



    }
}
}
