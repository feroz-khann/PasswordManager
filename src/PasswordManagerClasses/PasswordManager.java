package PasswordManagerClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;


public class PasswordManager extends JFrame implements ActionListener {
    JButton add, retrieve, update, delete, generate, exit;
    JLabel messageLabel;
    String mail;
    PasswordManager(String email){
        setTitle("Password Manager");
        setLayout(null);
        mail=email;

        JLabel text=new JLabel("Password Manager");
        text.setFont(new Font("Osward",Font.BOLD,34));
        text.setBounds(90,40,600,40);
        text.setForeground(Color.white);
        add(text);

        add =new JButton("ADD");
        add.setBounds(120,150,230,30);
        add.setBackground(Color.WHITE);
        add.setForeground(Color.BLACK);
        add.addActionListener(this);
        add(add);

        retrieve =new JButton("RETRIEVE");
        retrieve.setBounds(120,220,230,30);
        retrieve.setBackground(Color.WHITE);
        retrieve.setForeground(Color.BLACK);
        retrieve.addActionListener(this);
        add(retrieve);

        update =new JButton("UPDATE");
        update.setBounds(120,290,230,30);
        update.setBackground(Color.WHITE);
        update.setForeground(Color.BLACK);
        update.addActionListener(this);
        add(update);

        delete =new JButton("DELETE");
        delete.setBounds(120,360,230,30);
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.BLACK);
        delete.addActionListener(this);
        add(delete);

        generate =new JButton("GENERATE");
        generate.setBounds(120,430,230,30);
        generate.setBackground(Color.WHITE);
        generate.setForeground(Color.BLACK);
        generate.addActionListener(this);
        add(generate);

        exit =new JButton("EXIT");
        exit.setBounds(120,500,230,30);
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.BLACK);
        exit.addActionListener(this);
        add(exit);



        getContentPane().setBackground(Color.black);

        setSize(500,640);
        setVisible(true);
        setLocation(480,100);

    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== add){
            Operations.addPassword(mail);
        }
        else if(ae.getSource()== retrieve){
            Operations.retrievePassword(mail);
        }
        else if(ae.getSource()== update){
            Operations.updatePassword(mail);
        }
        else if(ae.getSource()== delete){
            Operations.deletePassword(mail);
        }
        else if(ae.getSource()== generate){
            Operations.generatePassword();
        }
        else if(ae.getSource()==exit){
//            setVisible(false);
            exit(0);
        }
    }
}
