package PasswordManagerClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login,clear,signup;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    Login(){
        setTitle("Password Manager");
        setLayout(null);

        JLabel text=new JLabel("Welcome to Password Manager");
        text.setFont(new Font("Osward",Font.BOLD,34));
        text.setBounds(120,40,600,40);
        text.setForeground(Color.white);
        add(text);

        JLabel username=new JLabel("Username:");
        username.setFont(new Font("Raleway",Font.BOLD,28));
        username.setBounds(120,150,150,30);
        username.setForeground(Color.white);
        add(username);

        usernameTextField =new JTextField();
        usernameTextField.setBounds(300,150,230,30);
        usernameTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(usernameTextField);

        JLabel password=new JLabel("Password:");
        password.setFont(new Font("Raleway",Font.BOLD,28));
        password.setBounds(120,220,250,30);
        password.setForeground(Color.white);
        add(password);

        passwordTextField =new JPasswordField();
        passwordTextField.setBounds(300,220,230,30);
        passwordTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(passwordTextField);

        login=new JButton("LOGIN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        clear=new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup=new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.black);

        setSize(800,480);
        setVisible(true);
        setLocation(350,200);



    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            Conn conn=new Conn();
            String username=usernameTextField.getText();
            String password=passwordTextField.getText();
            String query="select * from userdetails where email ='"+username+"' and password='"+password+"'";
            try{
                ResultSet rs=conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new PasswordManager(username).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else if(ae.getSource()==clear){
            usernameTextField.setText("");
            passwordTextField.setText("");
        }
        else if(ae.getSource()==signup){
            setVisible(false);
            new Signup().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}

