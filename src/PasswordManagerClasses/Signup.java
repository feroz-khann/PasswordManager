package PasswordManagerClasses;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener {
    JButton login,clear, submit;
    JTextField nameTextField, phoneTextField, emailTextField;
    JPasswordField newPasswordTextField, confirmPasswordTextField;

    Signup(){
        setTitle("Password Manager");
        setLayout(null);

        JLabel text=new JLabel("User Registration");
        text.setFont(new Font("Osward",Font.BOLD,34));
        text.setBounds(180,40,600,40);
        text.setForeground(Color.white);
        add(text);

        JLabel name=new JLabel("Name:");
        name.setFont(new Font("Raleway",Font.BOLD,28));
        name.setBounds(120,150,150,30);
        name.setForeground(Color.white);
        add(name);

        nameTextField =new JTextField();
        nameTextField.setBounds(300,150,230,30);
        nameTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(nameTextField);

        JLabel email=new JLabel("Email:");
        email.setFont(new Font("Raleway",Font.BOLD,28));
        email.setBounds(120,220,250,30);
        email.setForeground(Color.white);
        add(email);

        emailTextField =new JTextField();
        emailTextField.setBounds(300,220,230,30);
        emailTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(emailTextField);

        JLabel phone=new JLabel("Phone No:");
        phone.setFont(new Font("Raleway",Font.BOLD,28));
        phone.setBounds(120,290,250,30);
        phone.setForeground(Color.white);
        add(phone);

        phoneTextField =new JTextField();
        phoneTextField.setBounds(300,290,230,30);
        phoneTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(phoneTextField);

        JLabel newPassword=new JLabel("New");
        newPassword.setFont(new Font("Raleway",Font.BOLD,28));
        newPassword.setBounds(120,360,230,30);
        newPassword.setForeground(Color.white);
        add(newPassword);

        JLabel newPassword2=new JLabel("Password:");
        newPassword2.setFont(new Font("Raleway",Font.BOLD,28));
        newPassword2.setBounds(120,390,230,30);
        newPassword2.setForeground(Color.white);
        add(newPassword2);

        newPasswordTextField =new JPasswordField();
        newPasswordTextField.setBounds(300,373,230,30);
        newPasswordTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(newPasswordTextField);

        JLabel confirmPassword=new JLabel("Confirm");
        confirmPassword.setFont(new Font("Raleway",Font.BOLD,28));
        confirmPassword.setBounds(120,460,230,30);
        confirmPassword.setForeground(Color.white);
        add(confirmPassword);

        JLabel confirmPassword2=new JLabel("Password:");
        confirmPassword2.setFont(new Font("Raleway",Font.BOLD,28));
        confirmPassword2.setBounds(120,490,230,30);
        confirmPassword2.setForeground(Color.white);
        add(confirmPassword2);

        confirmPasswordTextField =new JPasswordField();
        confirmPasswordTextField.setBounds(300,473,230,30);
        confirmPasswordTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(confirmPasswordTextField);

        submit =new JButton("SUBMIT");
        submit.setBounds(300,560,230,30);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);

        getContentPane().setBackground(Color.black);

        setSize(720,700);
        setVisible(true);
        setLocation(350,100);



    }
    @Override
    public void actionPerformed(ActionEvent ae){
        String name=nameTextField.getText();//setText
        String mail=emailTextField.getText();
        String phone=phoneTextField.getText();
//        int phoneNo=Integer.parseInt(phone);
        String newPassword=newPasswordTextField.getText();
        String confirmPassword=confirmPasswordTextField.getText();
        try{
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name is Required");
            }
            else if(mail.equals("")){
                JOptionPane.showMessageDialog(null, "Email is Required");
            }
            else if(!(Validator.isValidGmail(mail))){
                JOptionPane.showMessageDialog(null, "Please enter a valid email");
            }
            else if(mail.equals("")){
                JOptionPane.showMessageDialog(null, "Phone No. is Required");
            }
            else if(!(Validator.isValidPhoneNo(phone))){
                JOptionPane.showMessageDialog(null, "Please enter a valid Phone No.");
            }
            else if(phone.length()!=10){
                JOptionPane.showMessageDialog(null, "Phone No. should be 10 digits");
            }
            else if(!(newPassword.length()>=8 && newPassword.length()<=20)){
                JOptionPane.showMessageDialog(null, "Password should contain 8-20 characters");
            }
            else if(!(Validator.isValidPassword(newPassword))){
                JOptionPane.showMessageDialog(null, "Password should contain both case letters, atleast one number and a special character");

            }
            else if(!(newPassword.equals(confirmPassword))){
                JOptionPane.showMessageDialog(null, "Confirm Password should be same as New Password");
            }
            else{
                Conn c=new Conn();
                String query="insert into userdetails values('"+name+"','"+mail+"','"+phone+"','"+newPassword+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "User Registered Successfully!");
                setVisible(false);
                new Login().setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}

