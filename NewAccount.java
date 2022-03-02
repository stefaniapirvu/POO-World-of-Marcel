package GAME.player;

import GAME.info.Account;
import GAME.info.Credentials;
import GAME.info.Information;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewAccount implements ActionListener {

    Account newAcc;
    ArrayList<Account> Accounts;
    JFrame frame ;
    JLabel emailL, passwL, nameL, countryL,  WnameL, RnameL, MnameL, gamesL;
    JTextField emailT, passwT, nameT, countryT, WnameT, RnameT, MnameT, games1, games2, games3;
    JButton button ;

    public NewAccount(ArrayList Accounts){
        this.Accounts= Accounts;
        frame= new JFrame("NewAccount");

        emailL = new JLabel("email");
        emailT = new JTextField();
        emailL.setBounds(50,50,100,30);
        emailT.setBounds(160,50,200,30);

        passwL = new JLabel("password");
        passwT = new JTextField();
        passwL.setBounds(50,90,100,30);
        passwT.setBounds(160,90,200,30);

        countryL = new JLabel("country");
        countryT = new JTextField();
        countryL.setBounds(50,130,100,30);
        countryT.setBounds(160,130,200,30);

        nameL = new JLabel("nume");
        nameT = new JTextField();
        nameL.setBounds(50,170,100,30);
        nameT.setBounds(160,170,200,30);

        WnameL = new JLabel("Warrior Name");
        WnameT = new JTextField();
        WnameL.setBounds(50,210,100,30);
        WnameT.setBounds(160,210,200,30);

        RnameL = new JLabel("Rogue Name");
        RnameT = new JTextField();
        RnameL.setBounds(50,250,100,30);
        RnameT.setBounds(160,250,200,30);

        MnameL = new JLabel("Mage Name");
        MnameT = new JTextField();
        MnameL.setBounds(50,290,100,30);
        MnameT.setBounds(160,290,200,30);

        gamesL = new JLabel("Top Fav Games");
        gamesL.setBounds(50,330,100,30);
        games1 = new JTextField();
        games1.setBounds(160,330,100,30);
        games2 = new JTextField();
        games2.setBounds(270,330,100,30);
        games3 = new JTextField();
        games3.setBounds(380,330,100,30);
        ArrayList<String> jocuri =new ArrayList<>();
        jocuri.add(games1.getText());
        jocuri.add(games2.getText());
        jocuri.add(games3.getText());


        String email =emailT.getText();
        String password = passwT.getText();
        String  country = countryT.getText();
        String name = nameT.getText();
        int maps_completed =0;
        String Wname = WnameT.getText();
        String Rname = RnameT.getText();
        String Mname = MnameT.getText();

        ArrayList<Character> personaje =new ArrayList<>();
        Character character1 = new CharacterFactory().makeCharacter("WARRIOR", Wname, 0, 1);
        personaje.add(character1);
        Character character2= new CharacterFactory().makeCharacter("ROGUE", Rname, 0, 1);
        personaje.add(character2);
        Character character3 = new CharacterFactory().makeCharacter("MAGE", Mname, 0, 1);
        personaje.add(character3);



        Credentials credentials= new Credentials(email, password);
        Information info =new Information.InfoBuilder()
                .nume(name)
                .tara(country)
                .credentials(credentials)
                .jocuri(jocuri)
                .build();

         newAcc =new Account(info , personaje, maps_completed);
        Accounts.add(newAcc);

        button = new JButton("Start");
        button.setBounds(350,370,200,50);

        frame.add(button);
        frame.add(emailT);
        frame.add(emailL);
        frame.add(passwT);
        frame.add(passwL);
        frame.add(countryT);
        frame.add(countryL);
        frame.add(nameT);
        frame.add(nameL);
        frame.add(WnameT);
        frame.add(WnameL);
        frame.add(RnameL);
        frame.add(RnameT);
        frame.add(MnameT);
        frame.add(MnameL);
        frame.add(gamesL);
        frame.add(games1);
        frame.add(games2);
        frame.add(games3);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
        frame.setLayout(null);
        frame.setTitle("Please Login Here !");
        frame.setSize(800, 600);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {
       ChoosePlayer choosePlayer = new ChoosePlayer(newAcc);


    }

}
