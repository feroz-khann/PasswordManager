package PasswordManagerClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.*;
public class Operations {

    public static void addPassword(String email) {
        String dummyLabel=null;
        int flag=0;
        String label = JOptionPane.showInputDialog( "Enter label for the password:");
        try {
            Conn c=new Conn();
            String query="select label from passwords where email='"+email+"'";
            ResultSet r=c.s.executeQuery(query);
            while(r.next()){
                dummyLabel = r.getString("label");
                if(label.equals(dummyLabel)){
                    flag=1;
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while fetching the labels");
            System.out.println(e);
        }
        if(flag==0){
            String password = JOptionPane.showInputDialog( "Enter password:");
//        System.out.println(label);
//        System.out.println(password);
            if (label == null || password == null || label.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Label and password cannot be empty");
                return;
            }

            String encryptedPassword=AES.encrypt(password);
//        System.out.println(encryptedPassword);

            try {
                Conn c=new Conn();
                String query="insert into passwords values('"+email+"','"+label+"','"+encryptedPassword+"')";
                c.s.executeUpdate(query);
                System.out.println();
                JOptionPane.showMessageDialog(null, "Password added successfully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error while adding password");
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Label already exists");
        }

    }

    public static void retrievePassword(String email){
        String label,pass,decryptedPassword,display="";
        Conn c=new Conn();
        try {
            String query="select label,password from passwords where email='"+email+"'";
            ResultSet rs=c.s.executeQuery(query);
            while(rs.next()){
                label=rs.getString("label");
                pass=rs.getString("password");
                if(pass!=null){
                    decryptedPassword=AES.decrypt(pass);
                    display+=label+": "+decryptedPassword+"\n";
                }
            }
            JOptionPane.showMessageDialog( null,display, "Stored Passwords", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog( null,"Error retrieving passwords", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updatePassword(String email){
        String dummyLabel=null;
        int flag=0;
        String label = JOptionPane.showInputDialog( "Enter label for the password update:");

        try {
            Conn c=new Conn();
            String query="select label from passwords where email='"+email+"'";
            ResultSet r=c.s.executeQuery(query);
            while(r.next()){
                dummyLabel = r.getString("label");
                if(label.equals(dummyLabel)){
                    flag=1;
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while fetching the labels");
            System.out.println(e);
        }
        if(flag==1){
            String password = JOptionPane.showInputDialog( "Enter updated password:");
//        System.out.println(label);
//        System.out.println(password);
            if (label == null || password == null || label.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Label and password cannot be empty");
                return;
            }
            String encryptedPassword=AES.encrypt(password);
//        System.out.println(encryptedPassword);
            try {
                Conn c=new Conn();
                String query="update passwords set password='"+encryptedPassword+"' where email='"+email+"' and label='"+label+"'";
                c.s.executeUpdate(query);
                System.out.println();
                JOptionPane.showMessageDialog(null, "Password updated successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error while updating the password");
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Label does not exist");
//            System.out.println("label doesnt exist");
        }
    }

    public static void deletePassword(String email){
        int flag=0;
        String dummyLabel=null;
        String label = JOptionPane.showInputDialog( "Enter label to delete the password:");
//        System.out.println(label);
        if (label == null || label.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Label cannot be empty");
            return;
        }
        try {
            Conn c=new Conn();
            String query="select label from passwords";
            ResultSet r=c.s.executeQuery(query);
            while(r.next()){
                dummyLabel=r.getString("label");
//                System.out.println(dummyLabel+","+label);
                if(label.equals(dummyLabel)){
                    flag=1;
                    System.out.println(flag);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while fetching the labels");
            System.out.println(e);
        }

        if(flag==1){
            try {
                Conn c=new Conn();
                String query="delete from passwords where label='"+label+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Password deleted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error while deleting the password");
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Label does not exist");
            System.out.println("label does not exist to delete");
        }
    }

    public static void generatePassword() {
        String generatedPassword = PasswordGenerator.generateRandomPassword(12);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel passwordLabel = new JLabel("Generated Password: " + generatedPassword);
        JButton copyButton = new JButton("Copy to Clipboard");

        copyButton.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(generatedPassword);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            JOptionPane.showMessageDialog(null, "Password copied to clipboard", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(passwordLabel, BorderLayout.CENTER);
        panel.add(copyButton, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, panel, "Password Generator", JOptionPane.INFORMATION_MESSAGE);
    }
}
