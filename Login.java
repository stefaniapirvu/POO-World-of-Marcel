package GAME.player;


import GAME.info.Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Login  implements ActionListener {
    JFrame frame;
    JLabel user, password, message;
    JTextField user_text;
    JPasswordField password_text;
    JButton button, button2;
    ArrayList<Account> Accounts ;

    public Login(ArrayList<Account> Accounts) {
        this.Accounts = Accounts;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/GAME/login.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel pic = new JLabel(new ImageIcon(img));
        pic.setBounds(200, 40, 500, 253);

        frame = new JFrame();
        user = new JLabel();
        user.setBounds(325, 350, 100, 35);
        user.setText("User Name :");
        user_text = new JTextField();
        user_text.setBounds(430, 350, 250, 35);

        password = new JLabel();
        password.setBounds(325, 400, 100, 35);
        password.setText("Password :");
        password_text = new JPasswordField();
        password_text.setBounds(430, 400, 250, 35);

        button = new JButton("LOGIN");
        button.setBounds(450, 460, 90, 25);

        button2 = new JButton("New Account");
        button2.setBounds(550, 460, 150, 25);

        message = new JLabel();
        message.setBounds(400, 500, 140, 30);

        frame.add(pic);
        frame.add(user);
        frame.add(user_text);
        frame.add(password);
        frame.add(password_text);
        frame.add(message);
        frame.add(button);
        frame.add(button2);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
        button2.addActionListener(this);
        frame.setLayout(null);
        frame.setTitle("Please Login Here !");
        frame.setSize(1000, 600);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource().equals(button)) {
            String userName = user_text.getText();
            String password = password_text.getText();
            int i;
            int index = -1;
            for (i = 0; i < Accounts.size(); i++)
                if (userName.trim().equals(Accounts.get(i).getInfo().getCredentials().getEmail())) {
                    index = i;
                }
            if (index >= 0) {
                if (password.trim().equals(Accounts.get(index).getInfo().getCredentials().getParola())) {
                    message.setText(" Hello " + Accounts.get(index).getInfo().getName() + " ");
                    ChoosePlayer ch = new ChoosePlayer(Accounts.get(index));
                } else {
                    message.setText(" Invalid password ");
                }
            } else {
                message.setText(" Invalid username ");
            }
        }
        else {
            NewAccount newAccount = new NewAccount(Accounts);
        }

    }
}