package GAME.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Start implements ActionListener {
    JFrame frame;
    JPanel panel;
    Character character;
    JButton buttons_empty[] =new JButton[21];
    JButton buttons_enemy[] =new JButton[4];
    JButton buttons_shop[] =new JButton[4];
    JLabel story ;
    JLabel info;
    JButton button_finish;
    Icon icon1;
    int curent;
    ArrayList shops = new ArrayList();
    ArrayList enemys = new ArrayList();

    JLabel message;

    public Start (Character character) {
        this.character = character;
        setIcon();
        curent=0;
        frame = new JFrame();
        panel = new JPanel();
        Icon icon = new ImageIcon("src/GAME/qmark.png");

        panel.setBounds(325, 50, 350, 350);
        panel.setLayout(new GridLayout(5,5));
        panel.setBackground(Color.BLUE);
        int em=0, en =0, s =0;

        story = new JLabel();
        story.setBounds(300, 500,400,50 );

        info = new JLabel();
        info.setText(" Nr monede:"+ character.inventory.nrmonede);
        info.setBounds(250,450,400,50);

        message = new JLabel();
        message.setBounds(300,550,400,50);

        Random rand = new Random();
        s = rand.nextInt(2,4);
        en = rand.nextInt(3, 5);
        for (int i =0 ;i< s;i++){
            int x = rand.nextInt(1,24);
            shops.add(x);
        }
        for (int i =0 ;i< en;i++){
            int x = rand.nextInt(1,24);
            if(shops.contains(x)){
                boolean ok= false;
                while (! ok){
                    x = rand.nextInt(1,24);
                    if( !shops.contains(x))
                        ok= true;
                }
            }
            enemys.add(x);
        }


        s=0;
        en=0;

        for (int i = 0 ;i <24 ; i++){
            if( shops.contains(i) ){
                buttons_shop[s] = new JButton(icon);
                panel.add(buttons_shop[s]);
                buttons_shop[s].addActionListener(this);
                s++;
            }
            else if(enemys.contains(i) ){
                buttons_enemy[en] = new JButton(icon);
                panel.add(buttons_enemy[en]);
                buttons_enemy[en].addActionListener(this);
                en++;
            }
            else {
                buttons_empty[em] = new JButton(icon);
                panel.add(buttons_empty[em]);
                buttons_empty[em].addActionListener(this);
                em++;
            }
        }


        button_finish = new JButton(icon);
        panel.add(button_finish);
        button_finish.addActionListener(this);
        buttons_empty[0].setIcon(icon1);

        frame.add(info);
        frame.add(message);
        frame.add(panel);
        frame.add(story);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Ready to play !");
        frame.setSize(1000, 600);
        frame.setVisible(true);

    }

    public void setIcon (){

        if (character.getClass().getSimpleName().equalsIgnoreCase("warrior")) {
            icon1 = new ImageIcon("src/GAME/warrior.png");
        }
        else if (character.getClass().getSimpleName().equalsIgnoreCase("rogue")) {
            icon1 = new ImageIcon("src/GAME/rogue.png");
        }
        else {
            icon1 = new ImageIcon("src/GAME/mage.png");
        }
    }


    public boolean ok (int c , int i){
        if(c == 0) {
            if (i == 1 || i == 5)
            return true;
        }
        else if(c == 4){
            if(i == 3 || i == 9 )
                return true;
        }
        else if(c == 20){
            if(i == 21 || i ==15 )
                return true;
        }

        else if( (c > 0) && (c <  4)) {
            if ((i - c == 1) || (i - c == 5) || (i - c == -1))
                return true;
        }
        else if( (c > 0) && (c % 5 == 0 ) && (c < 20)) {
            if ((i - c == 1) || (i - c == 5) || (i - c == -5))
                return true;
        }
        else if( (c > 20) && (c < 24)) {
            if ((i - c == 1) || (i - c == -1) || (i - c == -5))
                return true;
        }
        else if( (c > 4) && ((c + 1) % 5 == 0 ) ) {
            if ((i - c == -1) || (i - c == 5) || (i - c == -5))
                return true;
        }
        else{
            if((i - c == 1) || (i - c == -1) || (i - c == -5) || (i - c == 5))
                return true;
        }

            return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Icon icon_finish = new ImageIcon("src/GAME/trofeu.png");
        Icon icon_shop = new ImageIcon("src/GAME/shop.png");
        Icon icon_enemy = new ImageIcon("src/GAME/enemy.png");
        Game game = Game.getInstance();
        game.readstories();
        int nrenemys=0;


        if( ae.getSource().equals(button_finish) && (curent == 19 || curent ==23)) {
            button_finish.setIcon(icon_finish);
            story.setText(game.dictionar.get(Cell.Type.FINISH).get(0));
            FinishGUI finishGUI = new FinishGUI(character, nrenemys);

        }
        int em =0, en =0, s= 0;

        for(int i =0; i< 24; i++) {
            if(enemys.contains(i)) {
                if (ae.getSource().equals(buttons_enemy[en]) && ok(curent, i)) {
                    buttons_enemy[en].setIcon(icon_enemy);
                    curent = i;
                    story.setText(game.dictionar.get(Cell.Type.ENEMY).get(en));
                    EnemyGUI enemyGUI = new EnemyGUI(character);
                    nrenemys++;
                    info.setText(" Nr monede:"+ character.inventory.nrmonede);
                }
                en++;
            }
            else  if(shops.contains(i)) {
                if (ae.getSource().equals(buttons_shop[s]) && ok(curent, i)) {
                    buttons_shop[s].setIcon(icon_shop);
                    curent = i;
                    story.setText(game.dictionar.get(Cell.Type.SHOP).get(s));
                    ShopGUI shop = new ShopGUI(character);
                    info.setText(" Nr monede:"+ character.inventory.nrmonede);
                }
                s++;
            }
            else {
                if (ae.getSource().equals(buttons_empty[em]) && ok(curent, i)) {
                    buttons_empty[em].setIcon(icon1);
                    curent = i;
                    story.setText(game.dictionar.get(Cell.Type.EMPTY).get(em));
                    Random rand =new Random();
                    int x = rand.nextInt(1,6);  // sansa de 20% sa se gaseasca monede
                    if (x == 2 ){
                        int monedegasite = rand.nextInt(3,10);
                        character.inventory.setNrmonede(character.inventory.getNrmonede() + monedegasite);
                        message.setText("Ai gasit "+ monedegasite+" monede !! :)");
                        info.setText(" Nr monede:"+ character.inventory.nrmonede);
                    }else
                        message.setText("Nu ai gasit monede :( ");
                }
                em++;
            }
        }

    }
}
