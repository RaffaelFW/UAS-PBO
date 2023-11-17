package view;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginScreen {
    public LoginScreen(){
        Controller controller = new Controller();
        JFrame frame = new JFrame();
        frame.setTitle("Login");
        frame.setSize(400,200);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JLabel emailLabel = new JLabel("Email : ");
        JLabel passwordLabel = new JLabel("Password :");
        JTextField email = new JTextField(1);
        JTextField password = new JTextField(1);
        JButton button = new JButton("Login");

        //email
        panel.add(emailLabel);
        panel.add(email);
        //password
        panel.add(passwordLabel);
        panel.add(password);

        panel.add(new JLabel());
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailuser = email.getText();
                String passuser = password.getText();
                if(controller.login(emailuser,passuser)==true){
                    JOptionPane.showMessageDialog(null, "Login successful");
                    controller.getUser(emailuser);
                    new GameList(emailuser);
                    frame.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Login Failed!");
                }
            }
        });

    }


}
